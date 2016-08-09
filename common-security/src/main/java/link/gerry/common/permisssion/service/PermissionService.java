package link.gerry.common.permisssion.service;

import java.util.List;

import link.gerry.common.permisssion.model.Permission;
import com.gerry.common.framework.service.interfaces.IBaseService;

public interface PermissionService extends IBaseService<Permission> {

	List<Permission> findCommonPermissionByParentId(Integer parentId);
	
}
