package link.gerry.common.role.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.gerry.common.framework.model.BaseModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8726689303613996672L;

	private Integer id;

	private String name;

	private Integer status;

	private Date createTime;

	private Date updateTime;
}
