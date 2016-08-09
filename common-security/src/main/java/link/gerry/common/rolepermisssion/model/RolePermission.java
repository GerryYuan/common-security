package link.gerry.common.rolepermisssion.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.gerry.common.framework.model.BaseModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class RolePermission extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 699772212789622206L;

	private Integer id;
	
	private Integer roleId;
	
	private Integer permissionId;
	
	private Date createTime;
	
}
