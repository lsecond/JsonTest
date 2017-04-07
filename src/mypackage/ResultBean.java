package mypackage;

import java.util.List;

public class ResultBean {
	public int code;
	public String msg;
	public List<UserBean> muser;
	
	   public class UserBean{
		   public String name ;
		   public String age;
		   public String phone;
		   public String email;
	    }
}
