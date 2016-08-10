package link.gerry.common.permisssion.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import link.gerry.common.permisssion.dao.PermissionDAO;
import link.gerry.common.permisssion.model.Permission;
import link.gerry.common.permisssion.service.PermissionService;
import link.gerry.common.redis.RedisHelper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gerry.common.framework.redis.RedisConstant;
import com.gerry.common.framework.redis.RedisKVCache;
import com.gerry.common.framework.utils.EmptyUtils;
import com.gerry.common.framework.utils.recursive.AbstractRecursive;

@Slf4j
@Service
public class PermissionServiceImpl extends AbstractRecursive<Permission> implements PermissionService, InitializingBean {

	@Autowired
	private PermissionDAO permissionDAO;

	@Autowired
	private RedisKVCache<String, String> redisKVCache;

	@Override
	public Permission getById(Integer id) {
		return permissionDAO.getPermission(id);
	}

	@Override
	public int save(Permission t) {
		t.setCreateTime(new Date());
		return (int) permissionDAO.addPermission(t);
	}

	@Override
	public int delete(Integer id) {
		return permissionDAO.deletePermission(id) ? 1 : 0;
	}

	@Override
	public int update(Permission t) {
		t.setUpdateTime(new Date());
		return permissionDAO.updatePermission(t) ? 1 : 0;
	}

	@Override
	public List<Permission> findCommonPermissionByParentId(Integer parentId) {
		return permissionDAO.getPermissionsByParentId(parentId);
	}

	@Override
	public List<Permission> parentNodes(Permission recursiveNode) {
		return findCommonPermissionByParentId(recursiveNode.getParentId());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadResources();
	}

	private void loadResources() {
		log.info("开始加载权限资源...");
		List<Permission> permissions = permissionDAO.allPermissions();
		List<Permission> parentPermission = new ArrayList<Permission>();
		for (Permission permission : permissions) {
			if (EmptyUtils.isEmpty(permission.getParentId())) {
				parentPermission.add(permission);
			}
			redisKVCache.saveObjectBySeconds(RedisHelper.getPermissionKey(permission.getId()), JSON.toJSONString(permission), RedisConstant.DEFAULT_WEEK_SECONDS);
		}
		redisKVCache.saveObjectBySeconds(RedisHelper.getPermissionKey(), JSON.toJSONString(parentPermission), RedisConstant.DEFAULT_WEEK_SECONDS);// 添加权限到redis
		log.info("权限资源加载结束，总共加载{}个权限资源，{}个父权限资源...", permissions.size(), parentPermission.size());
	}

}
