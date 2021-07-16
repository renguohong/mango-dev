package admin.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SysUser implements Serializable {

    private Long id;


    private String name;

    private String nickName;


    private String avatar;

    private String password;


    private String salt;


    private String email;

    private String mobile;

    private Byte status;


    private Long deptId;


    private String createBy;


    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Byte delFlag;
	// 非数据库字段
	private String deptName;
	// 非数据库字段
	private String roleNames;
	// 非数据库字段
	private List<SysUserRole> userRoles = new ArrayList<>();

    private static final long serialVersionUID = 1L;





}