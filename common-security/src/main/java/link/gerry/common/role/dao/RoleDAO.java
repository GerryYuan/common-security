package link.gerry.common.role.dao;

import link.gerry.common.role.model.Role;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

@DB(table = "common_role")
public interface RoleDAO {
	String COLUMNS = "name, status, create_time, update_time";

	String ALL_COLUMNS = "id" + "," + COLUMNS;

	@ReturnGeneratedId
	@SQL("insert into #table(" + COLUMNS + ") values(:id, :name, :status, :createTime, :updateTime)")
	long addRole(Role role);

	@SQL("select " + ALL_COLUMNS + " from #table where id = :1")
	Role getRole(long id);

	@SQL("update #table set name=:name, status=:status, create_time=:createTime, update_time=:updateTime where id = :id")
	boolean updateRole(Role role);

	@SQL("delete from #table where id = :1")
	boolean deleteRole(long id);
}
