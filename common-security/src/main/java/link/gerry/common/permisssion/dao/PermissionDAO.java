package link.gerry.common.permisssion.dao;

import java.util.List;

import link.gerry.common.permisssion.model.Permission;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

@DB(table = "common_permission")
public interface PermissionDAO {
	String COLUMNS = "name, url, method, parent_id, create_time, update_time";

	String ALL_COLUMNS = "id" + "," + COLUMNS;

	@ReturnGeneratedId
	@SQL("insert into #table(" + COLUMNS + ") values(:id, :name, :url, :method, :parentId, :createTime, :updateTime)")
	long addPermission(Permission permission);

	@SQL("select " + ALL_COLUMNS + " from #table where id = :1")
	Permission getPermission(long id);

	@SQL("select " + ALL_COLUMNS + " from #table where parent_id = :1")
	List<Permission> getPermissionsByParentId(Integer parentId);
	
	@SQL("update #table set name=:name, url=:url, method=:method, parent_id=:parentId, create_time=:createTime, update_time=:updateTime where id = :id")
	boolean updatePermission(Permission permission);

	@SQL("delete from #table where id = :1")
	boolean deletePermission(long id);
}
