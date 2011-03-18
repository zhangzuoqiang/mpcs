package test
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import flash.text.TextFieldType;

	/**
	 * Description: 客户端发送数据界面
	 * <br/>Author: zhangzuoqiang
	 * <br/>Date: 2011-3-10
	 **/
	public class ClientPanel extends Sprite {
		
		private var clientLabel:TextField;
		
		private var label1:TextField;
		private var label2:TextField;
		private var label3:TextField;
		private var label4:TextField;
		private var label5:TextField;
		
		public var head1:TextField;
		public var head2:TextField;
		public var head3:TextField;
		
		public var body1:TextField;
		public var body2:TextField;
		public var body3:TextField;
		public var body4:TextField;
		public var body5:TextField;
		public var body6:TextField;
		public var body7:TextField;
		public var body8:TextField;
		public var body9:TextField;
		public var body10:TextField;
		public var body11:TextField;
		
		private var button:Button;
		
		public function ClientPanel() {
			initUI();
		}
		
		private function initUI():void{
			clientLabel = MoreUtils.createLabel(clientLabel, "客户端发送", 0, 2);
			clientLabel.autoSize = TextFieldAutoSize.CENTER;
			clientLabel.width = 400;
			clientLabel.background = true;
			clientLabel.backgroundColor = 0x66CCCC;
			
			label1 = MoreUtils.createLabel(label1, "Head-1", 20, 30);
			label2 = MoreUtils.createLabel(label2, "Head-2", 20, 60);
			label3 = MoreUtils.createLabel(label3, "Head-3", 20, 90);
			label4 = MoreUtils.createLabel(label4, "Body-1", 20, 120);
			label5 = MoreUtils.createLabel(label5, "Body-2", 20, 150);
			
			head1 = MoreUtils.createTextField(head1, 90, 30, 120, true);
			head1.text = "100000";
			head2 = MoreUtils.createTextField(head2, 90, 60, 120, true);
			head2.text = "0";
			head3 = MoreUtils.createTextField(head3, 90, 90, 120, true);
			head3.text = "0";
			body1 = MoreUtils.createTextField(body1, 90, 120, 200);
			body2 = MoreUtils.createTextField(body2, 90, 150, 200);
			body3 = MoreUtils.createTextField(body3, 90, 120, 200);
			body4 = MoreUtils.createTextField(body4, 90, 150, 200);
			body5 = MoreUtils.createTextField(body5, 90, 120, 200);
			body6 = MoreUtils.createTextField(body6, 90, 150, 200);
			body7 = MoreUtils.createTextField(body7, 90, 120, 200);
			body8 = MoreUtils.createTextField(body8, 90, 150, 200);
			body9 = MoreUtils.createTextField(body9, 90, 150, 200);
			body10 = MoreUtils.createTextField(body10, 90, 120, 200);
			body11 = MoreUtils.createTextField(body11, 90, 150, 200);
			
			button = new Button("发送");
			button.x = 300;
			button.y = 150;
			button.addEventListener(MouseEvent.CLICK, clickHandler);
			
			addChild(clientLabel);
			
			addChild(label1);
			addChild(label2);
			addChild(label3);
			addChild(label4);
			addChild(label5);
			
			addChild(head1);
			addChild(head2);
			addChild(head3);
			addChild(body1);
			addChild(body2);
			
			addChild(button);
		}
		
		/**
		 *  鼠标点击事件处理
		 * @param evt
		 */
		private function clickHandler(evt:MouseEvent):void{
//			if(this.head1.text =="" && this.head2.text == "" && this.head3.text == ""){
//				return;
//			}
//			Test.getInstance().server.setData();
			var h1:int = int(this.head1.text);
			var h2:int = int(this.head2.text);
			var h3:int = int(this.head3.text);
			if(this.body1.text == null){
				this.body1.text = "";
			}
			if(this.body2.text == null){
				this.body2.text = "";
			}
			if(this.body3.text == null){
				this.body3.text = "";
			}
			if(this.body4.text == null){
				this.body4.text = "";
			}
			if(this.body5.text == null){
				this.body5.text = "";
			}
			if(this.body6.text == null){
				this.body6.text = "";
			}
			if(this.body7.text == null){
				this.body7.text = "";
			}
			if(this.body8.text == null){
				this.body8.text = "";
			}
			if(this.body9.text == null){
				this.body9.text = "";
			}
			if(this.body10.text == null){
				this.body10.text = "";
			}
			if(this.body11.text == null){
				this.body11.text = "";
			}
			var body1:String = this.body1.text;
			var body2:String = this.body2.text;
			var body3:String = this.body3.text;
			var body4:String = this.body4.text;
			var body5:String = this.body5.text;
			var body6:String = this.body6.text;
			var body7:String = this.body7.text;
			var body8:String = this.body8.text;
			var body9:String = this.body9.text;
			var body10:String = this.body10.text;
			var body11:String = this.body11.text;
			
			Test.getInstance().requestConnectServer(h1, h2, h3, body1, body2, body3, body4, body5, body6, body7, body8, body9, body10, body11);
		}
	}
}