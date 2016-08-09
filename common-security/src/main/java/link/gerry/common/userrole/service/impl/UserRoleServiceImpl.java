package link.gerry.common.userrole.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import link.gerry.common.userrole.dao.UserRoleDAO;
import link.gerry.common.userrole.model.UserRole;
import link.gerry.common.userrole.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public UserRole getById(Integer id) {
		return userRoleDAO.getUserRole(id);
	}

	@Override
	public int save(UserRole t) {
		t.setCreateTime(new Date());
		return (int) userRoleDAO.addUserRole(t);
	}

	@Override
	public int delete(Integer id) {
		return userRoleDAO.deleteUserRole(id) ? 1 : 0;
	}

	@Override
	public int update(UserRole t) {
		return userRoleDAO.updateUserRole(t) ? 1 : 0;
	}

}
