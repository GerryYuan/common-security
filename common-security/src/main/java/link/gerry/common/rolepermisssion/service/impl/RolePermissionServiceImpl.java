package link.gerry.common.rolepermisssion.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import link.gerry.common.rolepermisssion.dao.RolePermissionDAO;
import link.gerry.common.rolepermisssion.model.RolePermission;
import link.gerry.common.rolepermisssion.service.RolePermissionService;

public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionDAO rolePermissionDAO;

	@Override
	public RolePermission getById(Integer id) {
		return rolePermissionDAO.getRolePermission(id);
	}

	@Override
	public int save(RolePermission t) {
		t.setCreateTime(new Date());
		return (int) rolePermissionDAO.addRolePermission(t);
	}

	@Override
	public int delete(Integer id) {
		return rolePermissionDAO.deleteRolePermission(id) ? 1 : 0;
	}

	@Override
	public int update(RolePermission t) {
		return rolePermissionDAO.updateRolePermission(t) ? 1 : 0;
	}

}
