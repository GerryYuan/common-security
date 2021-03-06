package link.gerry.common.permisssion.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.gerry.common.framework.utils.recursive.RecursiveNode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Permission extends RecursiveNode {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7472003519195438092L;

	private Integer id;

	private String name;

	private String url;

	private String method;

	private Integer parentId;

	private Date createTime;

	private Date updateTime;
}
