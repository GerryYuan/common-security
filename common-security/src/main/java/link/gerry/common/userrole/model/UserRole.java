package link.gerry.common.userrole.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.gerry.common.framework.model.BaseModel;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserRole extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8693756685931784695L;

	private Integer id;
	
	private Long userId;
	
	private Integer roleId;
	
	private Date createTime;
	
}
