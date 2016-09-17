package com.sfteam.wxzysh.util;

/**
 * @ClassName: UrlConstants.java
 * @Description: 请求地址
 * @author: knight
 * @date: 2016年7月14日 下午1:27:54
 * @company: sfteam
 */
public final class UrlConstants {

	/**
	 * 
	 * @ClassName: UserCtrl
	 * @Description: 用户模块
	 * @author: knight
	 * @date: 2016年7月14日 下午1:41:32
	 * @company: sfteam
	 */
	public final class UserCtrl {

		/**
		 * 登录
		 */
		public final static String LOGIN = "/usercontro/login.do";

		/**
		 * 注册
		 */
		public final static String REGISTER = "/usercontro/register.do";

		/**
		 * 修改个人信息
		 */
		public final static String UPDATE = "/usercontro/uptuser.do";

		/**
		 * 修改密码
		 */
		public final static String RESET = "/usercontro/uptpwd/{0}.do";

		/**
		 * 查询个人信息
		 */
		public final static String QUERY = "/usercontro/queryuserinfo/{0}.do";
	}

	/**
	 * 
	 * @ClassName: BrandCtrl
	 * @Description: 品牌模块
	 * @author: knight
	 * @date: 2016年7月14日 下午1:42:32
	 * @company: sfteam
	 */
	public final class BrandCtrl {

		/**
		 * 查询一级类别
		 */
		public final static String TOP_CATE = "/brandcontro/querytopcategory.do";

		/**
		 * 查询二级类别
		 */
		public final static String SUB_CATE = "/brandcontro/querysecondctegory/{0}.do";

		/**
		 * 查询标签列表
		 */
		public final static String TAG_LIST = "/brandcontro/querytaglist/{0}.do";

		/**
		 * 查询品牌的标签列表
		 */
		public final static String BRAND_TAG_LIST = "/brandcontro/querytaginfobybrandid/{0}.do";

		/**
		 * 查询品牌详情
		 */
		public final static String QUERY = "/brandcontro/querybrandinfo/{0}.do";

		/**
		 * 查询品牌列表
		 */
		public final static String BRAND_LIST = "/brandcontro/querybrandlist/{0}/{1}.do";

		/**
		 * 搜索品牌列表
		 */
		public final static String SRH_BRAND = "/brandcontro/searchbrandinfo/{0}.do";

		/**
		 * 查询首页品牌列表
		 */
		public final static String INDEX_LIST = "/brandcontro/queryHomebrandlist/{0}/{1}.do";
	}

	/**
	 * 
	 * @ClassName: CommentCtrl
	 * @Description: 评价模块
	 * @author: knight
	 * @date: 2016年7月14日 下午2:02:47
	 * @company: sfteam
	 */
	public final class CommentCtrl {

		/**
		 * 添加评论&回复评论
		 */
		public final static String ADD = "/commentContro/addbrandcomment.do";

		/**
		 * 查询评论列表 && 二级评论列表
		 */
		public final static String QUERY = "/commentContro/querybrandcommetlist/{0}/{1}/{2}.do";

		/**
		 * 查询我的评论列表
		 */
		public final static String QRY_M = "/commentContro/querymybracommentlist/{0}/{1}.do";

		/**
		 * 查询某品牌下我的评论列表
		 */
		public final static String QRY_B_M = "/commentContro/querymycommentbybrand/{0}/{1}/{2}.do";

		/**
		 * 查询最新评论
		 */
		public final static String QRY_RECENT = "/commentContro/querylastbracommentlist.do";

		/**
		 * 回复我的
		 */
		public final static String RLY_M = "/commentContro/queryreplymycomment/{0}/{1}.do";

		/**
		 * 我的回复
		 */
		public final static String M_RLY = "/commentContro/querymyreplycomment/{0}/{1}.do";

		/**
		 * 评论详情
		 */
		public final static String VIEW = "/commentContro/querycommentinfo/{0}.do";

		/**
		 * 点赞
		 */
		public final static String ZAN = "/commentContro/increasecommentzan/{0}.do";
	}

	/**
	 * 
	 * @ClassName: SystemCtrl
	 * @Description: 系统参数
	 * @author: knight
	 * @date: 2016年7月14日 下午2:18:16
	 * @company: sfteam
	 */
	public final class SystemCtrl {

		/**
		 * 查询系统参数
		 */
		public final static String PARAM = "/syscontro/querysysparamInfo/{0}.do";

		/**
		 * 添加反馈
		 */
		public final static String FEED = "/syscontro/addfeedinfo.do";

		/**
		 * 查询网站公告&关于我们
		 */
		public final static String CONTENT = "/syscontro/querycontentinfo/{0}.do";

		/**
		 * 查询系统消息列表
		 */
		public final static String SYSINFO_LIST = "/syscontro/querysysinfolist/{0}/{1}.do";

		/**
		 * 查询系统消息
		 */
		public final static String SYSINFO = "/syscontro/querysysinfo/{0}/{1}.do";

		/**
		 * 查询轮播
		 */
		public final static String CAROUSEL = "/syscontro/queryparamlist.do";

		/**
		 * 获取验证码
		 */
		public final static String SMS_CODE = "/syscontro/sendcode/{0}/{1}.do";

		/**
		 * 上传文件
		 */
		public final static String UPLOAD = "/syscontro/upload/1.do";
	}
}
