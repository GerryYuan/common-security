package link.gerry.common.role.service.impl;

import java.util.Date;

import link.gerry.common.role.dao.RoleDAO;
import link.gerry.common.role.model.Role;
import link.gerry.common.role.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

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

}
