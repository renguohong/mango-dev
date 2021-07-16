package admin.pojo;

import lombok.Data;

/* * @ClassName : LoginBean
 * @Description :登录接口封装对象
 * @Author: gino
 * @Date: 2021/7/003
 * @Time: 13:03

 */
@Data
public class LoginBean {
	private String account;
	private String password;
	private  String captcha;
}
