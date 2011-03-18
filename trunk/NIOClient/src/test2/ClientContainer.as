package test2
{
	import flash.events.MouseEvent;
	
	import mylibs.data.BaseCmd;
	import mylibs.data.SimpleCmd;
	import mylibs.data.SimpleMsg;
	import mylibs.events.GenericEvent;
	import mylibs.interfaces.IConnection;
	import mylibs.utils.ServerConfig;
	
	import org.aswing.EmptyLayout;
	import org.aswing.JButton;
	import org.aswing.JLabel;
	import org.aswing.JPanel;
	import org.aswing.JTextField;
	
	import test2.proxy.TimeProxy;
	
	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ClientContainer extends JPanel {
		
		public var clientLabel:JLabel;
		private var label1:JLabel;
		private var label2:JLabel;
		private var label3:JLabel;
		public var label4:JLabel;
		public var label5:JLabel;
		public var label6:JLabel;
		public var label7:JLabel;
		public var label8:JLabel;
		public var label9:JLabel;
		public var label10:JLabel;
		public var label11:JLabel;
		public var label12:JLabel;
		public var label13:JLabel;
		public var label14:JLabel;
		
		public var head1:JTextField;
		public var head2:JTextField;
		public var head3:JTextField;
		
		public var body1:JTextField;
		public var body2:JTextField;
		public var body3:JTextField;
		public var body4:JTextField;
		public var body5:JTextField;
		public var body6:JTextField;
		public var body7:JTextField;
		public var body8:JTextField;
		public var body9:JTextField;
		public var body10:JTextField;
		public var body11:JTextField;	
		
		private var sendBtn:JButton;
		
		public function ClientContainer() {
			super(new EmptyLayout());
			
			initUI();
		}
		
		private function initUI():void{
			
			clientLabel = TestUtils.createLabel(clientLabel, "客户端请求", 0, 2);
			clientLabel.setSizeWH(280, 20);
			label1 = TestUtils.createLabel(label1, "Head-1", 20, 30);
			label2 = TestUtils.createLabel(label2, "Head-2", 20, 60);
			label3 = TestUtils.createLabel(label3, "Head-3", 20, 90);
			label4 = TestUtils.createLabel(label4, "Body-1", 20, 120);
			label5 = TestUtils.createLabel(label5, "Body-2", 20, 150);
			label6 = TestUtils.createLabel(label6, "Body-3", 20, 180);
			label7 = TestUtils.createLabel(label7, "Body-4", 20, 210);
			label8 = TestUtils.createLabel(label8, "Body-5", 20, 240);
			label9 = TestUtils.createLabel(label9, "Body-6", 20, 270);
			label10 = TestUtils.createLabel(label10, "Body-7", 20, 300);
			label11 = TestUtils.createLabel(label11, "Body-8", 20, 330);
			label12 = TestUtils.createLabel(label12, "Body-9", 20, 360);
			label13 = TestUtils.createLabel(label13, "Body-10", 20, 390);
			label14 = TestUtils.createLabel(label14, "Body-11", 20, 420);
			
			head1 = TestUtils.createTextField(head1, 90, 30, 120, true);
			head1.setText("0");
			head2 = TestUtils.createTextField(head2, 90, 60, 120, true);
			head2.setText("0");
			head3 = TestUtils.createTextField(head3, 90, 90, 120, true);
			head3.setText("0");
			body1 = TestUtils.createTextField(body1, 90, 120, 200);
			body2 = TestUtils.createTextField(body2, 90, 150, 200);
			body3 = TestUtils.createTextField(body3, 90, 180, 200);
			body4 = TestUtils.createTextField(body4, 90, 210, 200);
			body5 = TestUtils.createTextField(body5, 90, 240, 200);
			body6 = TestUtils.createTextField(body6, 90, 270, 200);
			body7 = TestUtils.createTextField(body7, 90, 300, 200);
			body8 = TestUtils.createTextField(body8, 90, 330, 200);
			body9 = TestUtils.createTextField(body9, 90, 360, 200);
			body10 = TestUtils.createTextField(body10, 90, 390, 200);
			body11 = TestUtils.createTextField(body11, 90, 420, 200);
			
			sendBtn = new JButton("发送");
			sendBtn.setSizeWH(70,80);
			sendBtn.setLocationXY(220, 30);
			sendBtn.addEventListener(MouseEvent.CLICK, clickHandler);
			
			appendAll(clientLabel, label1, label2, label3, label4, label5);
			appendAll(label6, label7, label8, label9, label10, label11, label12, label13, label14);
			appendAll(head1, head2, head3, body1, body2, body3, body4, body5, body6, body7, body8, body9, body10, body11);
			append(sendBtn);
		}
		
		private function clickHandler(evt:MouseEvent):void{
			var command:int = int(clientLabel.getText());
			switch(command){
				case 1:
					var time:TimeProxy = new TimeProxy();
					time.sServerTime();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
			}
		}
		
	}
}