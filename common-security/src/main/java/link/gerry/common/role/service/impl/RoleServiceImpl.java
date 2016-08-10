package link.gerry.common.role.service.impl;

import java.util.Date;
import java.util.List;

import link.gerry.common.redis.RedisHelper;
import link.gerry.common.role.dao.RoleDAO;
import link.gerry.common.role.model.Role;
import link.gerry.common.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gerry.common.framework.redis.RedisConstant;
import com.gerry.common.framework.redis.RedisManager;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService, InitializingBean {

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private RedisManager<String, String> redisManager;

	@Override
	public Role getById(Integer id) {
		return roleDAO.getRole(id);
	}

	@Override
	public int save(Role t) {
		t.setCreateTime(new Date());
		return (int) roleDAO.addRole(t);
	}

	@Override
	public int delete(Integer id) {
		return roleDAO.deleteRole(id) ? 1 : 0;
	}

	@Override
	public int update(Role t) {
		t.setUpdateTime(new Date());
		return roleDAO.updateRole(t) ? 1 : 0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("开始加载角色资源...");
		List<Role> roles = roleDAO.allRoles();
		redisManager.saveObjectBySeconds(RedisHelper.getRoleKey(), JSON.toJSONString(roles), RedisConstant.DEFAULT_WEEK_SECONDS);
		log.info("角色资源加载结束，总共加载{}个角色资源...", roles.size());
	}

}
