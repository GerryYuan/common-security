package link.gerry.common.permisssion.service.impl;

import java.util.Date;
import java.util.List;

import link.gerry.common.permisssion.dao.PermissionDAO;
import link.gerry.common.permisssion.model.Permission;
import link.gerry.common.permisssion.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;

import com.gerry.common.framework.utils.recursive.AbstractRecursive;

public class PermissionServiceImpl extends AbstractRecursive<Permission> implements PermissionService {

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

	@Override
	public List<Permission> findCommonPermissionByParentId(Integer parentId) {
		return permissionDAO.getPermissionsByParentId(parentId);
	}

	@Override
	public List<Permission> parentNodes(Permission recursiveNode) {
		return findCommonPermissionByParentId(recursiveNode.getParentId());
	}

	public static void main(String[] args) {
		PermissionServiceImpl aImpl = new PermissionServiceImpl();
		aImpl.recursive(null);
		System.out.println("dd");
	}
}
