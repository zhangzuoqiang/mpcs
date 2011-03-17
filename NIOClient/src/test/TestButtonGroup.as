package test
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;

	/**
	 * <b>Description: </b>测试按钮组
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-13
	 **/
	public class TestButtonGroup extends Sprite {
		
		private var time:Button;                       // 测试时间
		private var register:Button;                  // 测试用户注册
		private var login:Button;                      // 测试登录
		private var basicInfo:Button;               // 测试请求用户基本信息
		private var saveBasicInfo:Button;        // 测试保存用户基本信息
		private var contactInfo:Button;           // 测试请求用户联系信息
		private var saveContactInfo:Button;    // 测试保存用户联系信息
		private var modPwd:Button;                // 测试修改密码
		private var bindMobile:Button;            // 测试绑定手机
		private var bindMobileList:Button;       // 测试绑定手机
		private var setLocation:Button;           // 测试设定位置
		private var tips:Button;		                // 测试开启手机提醒
		
		public function TestButtonGroup(){
			initUI();
		}
		
		private function initUI():void{
			time = MoreUtils.createBtn(time, "时间");
			time.addEventListener(MouseEvent.CLICK, timeHandler);
			
			register = MoreUtils.createBtn(register, "注册");
			register.addEventListener(MouseEvent.CLICK, registerHandler);
			
			login = MoreUtils.createBtn(login, "登录");
			login.addEventListener(MouseEvent.CLICK, loginHandler);
			
			basicInfo = MoreUtils.createBtn(basicInfo, "基本信息");
			basicInfo.addEventListener(MouseEvent.CLICK, basicInfoHandler);
			
			saveBasicInfo = MoreUtils.createBtn(saveBasicInfo, "保存基本信息");
			saveBasicInfo.addEventListener(MouseEvent.CLICK, saveBasicInfoHandler);
			
			contactInfo = MoreUtils.createBtn(contactInfo, "联系信息");
			contactInfo.addEventListener(MouseEvent.CLICK, contactInfoHandler);
			
			saveContactInfo = MoreUtils.createBtn(saveContactInfo, "保存联系信息");
			saveContactInfo.addEventListener(MouseEvent.CLICK, saveContactInfoHandler);
			
			modPwd = MoreUtils.createBtn(modPwd, "修改密码");
			modPwd.addEventListener(MouseEvent.CLICK, modPwdHandler);
			
			bindMobile = MoreUtils.createBtn(bindMobile, "绑定手机");
			bindMobile.addEventListener(MouseEvent.CLICK, bindMobileHandler);
			
			bindMobileList = MoreUtils.createBtn(bindMobileList, "绑定手机列表");
			bindMobileList.addEventListener(MouseEvent.CLICK, bindMobileListHandler);
			
			setLocation = MoreUtils.createBtn(setLocation, "设定位置");
			setLocation.addEventListener(MouseEvent.CLICK, setLocationHandler);
			
			tips = MoreUtils.createBtn(tips, "开启手机提醒");
			tips.addEventListener(MouseEvent.CLICK, tipsHandler);
			
			
			addChild(time);
			addChild(register);
			addChild(login);
			addChild(basicInfo);
			addChild(saveBasicInfo);
			addChild(contactInfo);
			addChild(saveContactInfo);
			addChild(modPwd);
			addChild(bindMobile);
			addChild(bindMobileList);
			addChild(setLocation);
			addChild(tips);
		}
		
		/**
		 * 时间
		 * @param evt
		 */		
		private function timeHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100000";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "";
			Test.getInstance().client.body2.text = "";
		}
		
		/**
		 * 注册
		 * @param evt
		 */	
		private function registerHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100101";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "123";
		}
		
		/**
		 * 登录
		 * @param evt
		 */	
		private function loginHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100102";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "123";
		}
		
		/**
		 * 基本信息
		 * @param evt
		 */	
		private function basicInfoHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100201";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "";
		}
		
		/**
		 * 保存基本信息
		 * @param evt
		 */	
		private function saveBasicInfoHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100202";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn_eric@hotmail.com";
			Test.getInstance().client.body2.text = "作强";
			Test.getInstance().client.body3.text = "男";
			Test.getInstance().client.body4.text = "19880314";
			Test.getInstance().client.body5.text = "O";
			Test.getInstance().client.body6.text = "未婚";
			Test.getInstance().client.body7.text = "学生";
			Test.getInstance().client.body8.text = "大学本科";
			Test.getInstance().client.body9.text = "东莞松山湖";
			Test.getInstance().client.body10.text = "山东";
			Test.getInstance().client.body11.text = "370782198803142495";
		}
		
		/**
		 * 联系信息
		 * @param evt
		 */	
		private function contactInfoHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100203";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "";
		}
		
		/**
		 * 保存联系信息
		 * @param evt
		 */	
		private function saveContactInfoHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100204";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@ymail.com";
			Test.getInstance().client.body2.text = "200844306";
			Test.getInstance().client.body3.text = "csdn_eric@hotmail.com";
			Test.getInstance().client.body4.text = "13609698135";
			Test.getInstance().client.body5.text = "0";
			Test.getInstance().client.body6.text = "523808";
		}
		
		/**
		 * 修改密码
		 * @param evt
		 */	
		private function modPwdHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100205";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "12345";
		}
		
		/**
		 * 绑定手机
		 * @param evt
		 */	
		private function bindMobileHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100206";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "15876995100";
			Test.getInstance().client.body3.text = "父母";
			Test.getInstance().client.body4.text = "服务中";
			Test.getInstance().client.body5.text = "山东";
			Test.getInstance().client.body6.text = "20110317";
			Test.getInstance().client.body7.text = "200001";
			Test.getInstance().client.body8.text = "23";
			Test.getInstance().client.body9.text = "112";
			Test.getInstance().client.body10.text = "500";
		}
		
		/**
		 * 请求绑定手机列表
		 * @param evt
		 */	
		private function bindMobileListHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100209";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "";
		}
		
		/**
		 * 设定位置
		 * @param evt
		 */	
		private function setLocationHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100207";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "";
			Test.getInstance().client.body2.text = "";
		}
		
		/**
		 * 开启手机提醒
		 * @param evt
		 */	
		private function tipsHandler(evt:MouseEvent):void{
			Test.getInstance().client.head1.text = "100208";
			Test.getInstance().client.head2.text = "0";
			Test.getInstance().client.head3.text = "0";
			Test.getInstance().client.body1.text = "csdn.eric@gmail.com";
			Test.getInstance().client.body2.text = "0";
		}
	}
}