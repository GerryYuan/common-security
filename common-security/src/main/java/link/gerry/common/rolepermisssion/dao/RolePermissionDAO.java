package link.gerry.common.rolepermisssion.dao;

import link.gerry.common.rolepermisssion.model.RolePermission;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

@DB(
        table = "common_role_permission"
)
public interface RolePermissionDAO {
    String COLUMNS = "role_id, permission_id, create_time";

    String ALL_COLUMNS = "id" + "," + COLUMNS;

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ") values(:id, :roleId, :permissionId, :createTime)")
    long addRolePermission(RolePermission rolePermission);

    @SQL("select " + ALL_COLUMNS + " from #table where id = :1")
    RolePermission getRolePermission(long id);

    @SQL("update #table set role_id=:roleId, permission_id=:permissionId, create_time=:createTime where id = :id")
    boolean updateRolePermission(RolePermission rolePermission);

    @SQL("delete from #table where id = :1")
    boolean deleteRolePermission(long id);
}

