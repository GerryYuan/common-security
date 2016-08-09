package link.gerry.common.userrole.dao;

import link.gerry.common.userrole.model.UserRole;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

@DB(
        table = "common_user_role"
)
public interface UserRoleDAO {
    String COLUMNS = "user_id, role_id, create_time";

    String ALL_COLUMNS = "id" + "," + COLUMNS;

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ") values(:id, :userId, :roleId, :createTime)")
    long addUserRole(UserRole userRole);

    @SQL("select " + ALL_COLUMNS + " from #table where id = :1")
    UserRole getUserRole(long id);

    @SQL("update #table set user_id=:userId, role_id=:roleId, create_time=:createTime where id = :id")
    boolean updateUserRole(UserRole userRole);

    @SQL("delete from #table where id = :1")
    boolean deleteUserRole(long id);
}

