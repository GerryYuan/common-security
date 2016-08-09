package link.gerry.common.permisssion.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import link.gerry.common.permisssion.dao.PermissionDAO;
import link.gerry.common.permisssion.model.Permission;
import link.gerry.common.permisssion.service.PermissionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.gerry.common.framework.redis.RedisConstant;
import com.gerry.common.framework.redis.RedisManager;
import com.gerry.common.framework.utils.EmptyUtils;
import com.gerry.common.framework.utils.recursive.AbstractRecursive;

@Slf4j
public class PermissionServiceImpl extends AbstractRecursive<Permission> implements PermissionService, InitializingBean {

	@Autowired
	private PermissionDAO permissionDAO;

	@Autowired
	private RedisManager<String, String> redisManager;

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
		// 加载
		log.info("加载权限");
		loadResources();
	}

	private void loadResources() {
		List<Permission> permissions = permissionDAO.allPermissions();
		List<Permission> parentPermission = new ArrayList<Permission>();
		for (Permission permission : permissions) {
			if (EmptyUtils.isEmpty(permission.getParentId())) {
				parentPermission.add(permission);
			}
			List<Permission> permissionChild = findCommonPermissionByParentId(permission.getId());
			if (EmptyUtils.isNotEmpty(permissionChild)) {
				redisManager.saveObjectBySeconds("key", JSON.toJSONString(permissionChild), RedisConstant.DEFAULT_WEEK_SECONDS);// 添加权限到redis
			}
		}
	}
}
