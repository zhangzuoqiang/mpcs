package test
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;

	/**
	 * Description: 
	 * <br/>Author: zhangzuoqiang
	 * <br/>Date: 2011-3-10
	 **/
	public class ServerPanel extends Sprite {
		
		private var serverLabel:TextField;
		
		private var label1:TextField;
		private var label2:TextField;
		private var label3:TextField;
		private var label4:TextField;
		
		private var head1:TextField;
		private var head2:TextField;
		private var head3:TextField;
		private var body:TextField;
		
		private var button:Button;
		
		public function ServerPanel(){
			initUI();
			setData();
		}
		
		public function setData(head1:int=0, head2:int=0, head3:int=0, body:String=""):void{
			this.head1.text = head1.toString();
			this.head2.text = head2.toString();
			this.head3.text = head3.toString();
			if(body != "" || body != null){
				this.body.text = body.toString();
			}
		}
		
		private function initUI():void{
			serverLabel = MoreUtils.createLabel(serverLabel, "服务端返回", 0, 2);
			serverLabel.autoSize = TextFieldAutoSize.CENTER;
			serverLabel.width = 400;
			serverLabel.background = true;
			serverLabel.backgroundColor = 0x66CCCC;
			
			label1 = MoreUtils.createLabel(label1, "Head-1", 20, 30);
			label2 = MoreUtils.createLabel(label2, "Head-2", 20, 60);
			label3 = MoreUtils.createLabel(label3, "Head-3", 20, 90);
			label4 = MoreUtils.createLabel(label4, "Body", 20, 120);
			
			head1 = MoreUtils.createTextField(head1, 90, 30, 120, false, false);
			head2 = MoreUtils.createTextField(head2, 90, 60, 120, false, false);
			head3 = MoreUtils.createTextField(head3, 90, 90, 120, false, false);
			body = MoreUtils.createTextField(body, 90, 120, 240, false, false);
			body.height = 160;
			body.wordWrap = true;
			
			button = new Button("清空");
			button.x = 300;
			button.y = 0;
			button.addEventListener(MouseEvent.CLICK, clickHandler);
			
			addChild(serverLabel);
			
			addChild(label1);
			addChild(label2);
			addChild(label3);
			addChild(label4);
			
			addChild(head1);
			addChild(head2);
			addChild(head3);
			addChild(body);
			
			addChild(button);
		}
		
		private function clickHandler(evt:MouseEvent):void{
			setData();
		}
	}
}