package test2
{
	import flash.events.MouseEvent;
	
	import org.aswing.EmptyLayout;
	import org.aswing.JButton;
	import org.aswing.JLabel;
	import org.aswing.JPanel;
	import org.aswing.LayoutManager;
	
	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ButtonGroupContainer extends JPanel {
		
		private var btnLabel:JLabel;
		
		private var time:JButton;                       // 测试时间
		private var register:JButton;                  // 测试用户注册
		private var login:JButton;                      // 测试登录
		private var basicInfo:JButton;               // 测试请求用户基本信息
		private var saveBasicInfo:JButton;        // 测试保存用户基本信息
		private var contactInfo:JButton;           // 测试请求用户联系信息
		private var saveContactInfo:JButton;   // 测试保存用户联系信息
		private var modPwd:JButton;               // 测试修改密码
		private var bindMobile:JButton;           // 测试绑定手机
		private var bindMobileList:JButton;      // 测试绑定手机
		private var location:JButton;                // 测试设定位置
		private var tips:JButton;		                 // 测试开启手机提醒
		
		public function ButtonGroupContainer() {
			super(new EmptyLayout());
			initUI();
		}
		
		private function initUI():void{
			
			btnLabel = TestUtils.createLabel(btnLabel, "测试按钮", 25, 5);
			
			time = TestUtils.createBtn(time, "时间");
			time.addEventListener(MouseEvent.CLICK, timeHandler);
			
			register = TestUtils.createBtn(register, "注册");
			register.addEventListener(MouseEvent.CLICK, registerHandler);
			
			login = TestUtils.createBtn(login, "登录");
			login.addEventListener(MouseEvent.CLICK, loginHandler);
			
			basicInfo = TestUtils.createBtn(basicInfo, "基本信息");
			basicInfo.addEventListener(MouseEvent.CLICK, basicInfoHandler);
			
			saveBasicInfo = TestUtils.createBtn(saveBasicInfo, "保存基本信息");
			saveBasicInfo.addEventListener(MouseEvent.CLICK, saveBasicInfoHandler);
			
			contactInfo = TestUtils.createBtn(contactInfo, "联系信息");
			contactInfo.addEventListener(MouseEvent.CLICK, contactInfoHandler);
			
			saveContactInfo = TestUtils.createBtn(saveContactInfo, "保存联系信息");
			saveContactInfo.addEventListener(MouseEvent.CLICK, saveContactInfoHandler);
			
			modPwd = TestUtils.createBtn(modPwd, "修改密码");
			modPwd.addEventListener(MouseEvent.CLICK, modPwdHandler);
			
			bindMobile = TestUtils.createBtn(bindMobile, "绑定手机");
			bindMobile.addEventListener(MouseEvent.CLICK, bindMobileHandler);
			
			bindMobileList = TestUtils.createBtn(bindMobileList, "手机列表");
			bindMobileList.addEventListener(MouseEvent.CLICK, bindMobileListHandler);
			
			location = TestUtils.createBtn(location, "设定位置");
			location.addEventListener(MouseEvent.CLICK, setLocationHandler);
			
			tips = TestUtils.createBtn(tips, "开启手机提醒");
			tips.addEventListener(MouseEvent.CLICK, tipsHandler);
			
			appendAll(btnLabel, time, register, login, basicInfo, saveBasicInfo, contactInfo, 
				saveContactInfo, modPwd, bindMobile, bindMobileList, location, tips);
		}
		
		/**
		 * 时间
		 * @param evt
		 */		
		private function timeHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100000");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.body1.setText("");
			Test2.getInstance().client.body2.setText("");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 注册
		 * @param evt
		 */	
		private function registerHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100101");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.label5.setText("密码");
			Test2.getInstance().client.body2.setText("123");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 登录
		 * @param evt
		 */	
		private function loginHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100102");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.label5.setText("密码");
			Test2.getInstance().client.body2.setText("123");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 基本信息
		 * @param evt
		 */	
		private function basicInfoHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100201");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.body2.setText("");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 保存基本信息
		 * @param evt
		 */	
		private function saveBasicInfoHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100202");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn_eric@hotmail.com");
			Test2.getInstance().client.label5.setText("姓名");
			Test2.getInstance().client.body2.setText("作强");
			Test2.getInstance().client.label6.setText("性别");
			Test2.getInstance().client.body3.setText("男");
			Test2.getInstance().client.label7.setText("出生日期");
			Test2.getInstance().client.body4.setText("19880314");
			Test2.getInstance().client.label8.setText("血型");
			Test2.getInstance().client.body5.setText("O");
			Test2.getInstance().client.label9.setText("婚姻状况");
			Test2.getInstance().client.body6.setText("未婚");
			Test2.getInstance().client.label10.setText("职业");
			Test2.getInstance().client.body7.setText("学生");
			Test2.getInstance().client.label11.setText("教育程度");
			Test2.getInstance().client.body8.setText("大学本科");
			Test2.getInstance().client.label12.setText("居住地");
			Test2.getInstance().client.body9.setText("东莞松山湖");
			Test2.getInstance().client.label13.setText("家乡");
			Test2.getInstance().client.body10.setText("山东");
			Test2.getInstance().client.label14.setText("身份证号");
			Test2.getInstance().client.body11.setText("370782198803142495");
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 联系信息
		 * @param evt
		 */	
		private function contactInfoHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100203");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.body2.setText("");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 保存联系信息
		 * @param evt
		 */	
		private function saveContactInfoHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100204");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@ymail.com");
			Test2.getInstance().client.label5.setText("QQ");
			Test2.getInstance().client.body2.setText("200844306");
			Test2.getInstance().client.label6.setText("MSN");
			Test2.getInstance().client.body3.setText("csdn_eric@hotmail.com");
			Test2.getInstance().client.label7.setText("手机");
			Test2.getInstance().client.body4.setText("13609698135");
			Test2.getInstance().client.label8.setText("固定电话");
			Test2.getInstance().client.body5.setText("0");
			Test2.getInstance().client.label9.setText("邮编");
			Test2.getInstance().client.body6.setText("523808");
			Test2.getInstance().client.body7.setText("");
			Test2.getInstance().client.body8.setText("");
			Test2.getInstance().client.body9.setText("");
			Test2.getInstance().client.body10.setText("");
			Test2.getInstance().client.body11.setText("");
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 修改密码
		 * @param evt
		 */	
		private function modPwdHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100205");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.label5.setText("新密码");
			Test2.getInstance().client.body2.setText("12345");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 绑定手机
		 * @param evt
		 */	
		private function bindMobileHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100206");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.label5.setText("绑定的手机号");
			Test2.getInstance().client.body2.setText("15876995100");
			Test2.getInstance().client.label6.setText("关系");
			Test2.getInstance().client.body3.setText("父母");
			Test2.getInstance().client.label7.setText("状态");
			Test2.getInstance().client.body4.setText("服务中");
			Test2.getInstance().client.label8.setText("号码归属地");
			Test2.getInstance().client.body5.setText("山东");
			Test2.getInstance().client.label9.setText("服务开启时间");
			Test2.getInstance().client.body6.setText("20110317");
			Test2.getInstance().client.label10.setText("会员类型");
			Test2.getInstance().client.body7.setText("200001");
			Test2.getInstance().client.label11.setText("经度");
			Test2.getInstance().client.body8.setText("23");
			Test2.getInstance().client.label12.setText("纬度");
			Test2.getInstance().client.body9.setText("112");
			Test2.getInstance().client.label13.setText("最大偏移距离");
			Test2.getInstance().client.body10.setText("500");
			Test2.getInstance().client.body11.setText("");
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 请求绑定手机列表
		 * @param evt
		 */	
		private function bindMobileListHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100209");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.body2.setText("");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 设定位置
		 * @param evt
		 */	
		private function setLocationHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100207");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.body1.setText("");
			Test2.getInstance().client.body2.setText("");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		/**
		 * 开启手机提醒
		 * @param evt
		 */	
		private function tipsHandler(evt:MouseEvent):void{
			Test2.getInstance().client.head1.setText("100208");
			Test2.getInstance().client.head2.setText("0");
			Test2.getInstance().client.head3.setText("0");
			Test2.getInstance().client.label4.setText("Email");
			Test2.getInstance().client.body1.setText("csdn.eric@gmail.com");
			Test2.getInstance().client.label5.setText("开启/关闭");
			Test2.getInstance().client.body2.setText("0");
			clearOthers();
			setclientLabel((evt.target as JButton).getName());
		}
		
		private function setclientLabel(label:String):void{
			Test2.getInstance().client.clientLabel.setText(label);
		}
		
		private function clearOthers():void{
			Test2.getInstance().client.body3.setText("");
			Test2.getInstance().client.body4.setText("");
			Test2.getInstance().client.body5.setText("");
			Test2.getInstance().client.body6.setText("");
			Test2.getInstance().client.body7.setText("");
			Test2.getInstance().client.body8.setText("");
			Test2.getInstance().client.body9.setText("");
			Test2.getInstance().client.body10.setText("");
			Test2.getInstance().client.body11.setText("");
		}
	}
}