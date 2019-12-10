package com.activiti.process.constast;

public interface SYSConstast {

	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_SUPER=0;//超级用户
	public static final Integer USER_TYPE_NORMAL=1;//系统用户
	
	/**
	 * 可用类型
	 */
	public static final Integer SYS_AVAILABLE_TRUE = 1;
	public static final Integer SYS_AVAILABLE_FALSE = 0;
	
	/**
	 * 是否展开
	 */
	public static final Integer SYS_OPEN_TRUE = 1;
	public static final Integer SYS_OPEN_FALSE = 0;
	
	/**
	 * 是否父节点
	 */
	public static final Integer SYS_PARENT_TRUE = 1;
	public static final Integer SYS_PARENT_FALSE = 0;
	
	/**
	 * 权限类型
	 */
	public static final String PERMISSION_TYPE_MEUN = "menu";
	public static final String PERMISSION_TYPE_PERMISSION = "permission";
	
	/**
	 * 用户默认密码
	 */
	public static final String USER_PWD_DEFAULT = "123456";
	
	/**
	 * 用户默认头像地址
	 */
	public static final String USER_DEFALUT_IMGTITLE = "../resources/images/defaultusertitle.jpg";
	/**
	 * 请假单的状态
	 */
	public static final String STATE_LEAVEBILL_ZORO = "0";//未提交
	public static final String STATE_LEAVEBILL_ONE = "1";//审批中
	public static final String STATE_LEAVEBILL_TOW = "2";//审批完成
	public static final String STATE_LEAVEBILL_THREE = "3";//已放弃

}
