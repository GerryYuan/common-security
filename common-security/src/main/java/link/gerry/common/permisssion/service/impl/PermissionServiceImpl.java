package link.gerry.common.permisssion.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import link.gerry.common.permisssion.dao.PermissionDAO;
import link.gerry.common.permisssion.model.Permission;
import link.gerry.common.permisssion.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDAO permissionDAO;

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

}
