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
		
		private var head1:TextField;
		private var head2:TextField;
		private var head3:TextField;
		
		private var body1:TextField;
		private var body2:TextField;
		
		private var button:Button;
		
		public function ClientPanel() {
			initUI();
		}
		
		private function initUI():void{
			clientLabel = Utils.createLabel(clientLabel, "客户端发送", 0, 2);
			clientLabel.autoSize = TextFieldAutoSize.CENTER;
			clientLabel.width = 400;
			clientLabel.background = true;
			clientLabel.backgroundColor = 0x66CCCC;
			
			label1 = Utils.createLabel(label1, "Head-1", 20, 30);
			label2 = Utils.createLabel(label2, "Head-2", 20, 60);
			label3 = Utils.createLabel(label3, "Head-3", 20, 90);
			label4 = Utils.createLabel(label4, "Body-1", 20, 120);
			label5 = Utils.createLabel(label5, "Body-2", 20, 150);
			
			head1 = Utils.createTextField(head1, 90, 30);
			head2 = Utils.createTextField(head2, 90, 60);
			head3 = Utils.createTextField(head3, 90, 90);
			body1 = Utils.createTextField(body1, 90, 120, 200);
			body2 = Utils.createTextField(body2, 90, 150, 200);
			
			button = new Button("发送");
			button.x = 300;
			button.y = 150;
			button.addEventListener(MouseEvent.CLICK, clickHandler);
			this.
			
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
			
		}
	}
}