package  
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	
	public class RegistPanel extends Sprite
	{
		private var tf:TextField;
		private var label:TextField;
		private var bt:Button;
		public function RegistPanel() 
		{
			label = new TextField();
			label.x = 50;
			label.y = 20;
			label.text = "在右侧输入一串数字命令：";
			label.width = 145;
			label.height = 20;
			label.selectable = false;
			addChild(label);
			
			tf = new TextField();
			tf.border = true;
			tf.type = TextFieldType.INPUT;
			tf.x = 200;
			tf.y = 20;
			tf.width = 100;
			tf.height = 20;
			addChild(tf);
			
			bt = new Button(40, 20);
			bt.x = tf.width + tf.x + 10;
			bt.y = 20;
			addChild(bt);
			
			bt.addEventListener(MouseEvent.CLICK, onClickHandler);
		}
		private function onClickHandler(e:MouseEvent):void {
//			if (!int(tf.text)) {
				var event:NetEvent = new NetEvent(NetEvent.NET_CONNECT);
				event.playerID = (int)(tf.text);
				dispatchEvent(event);
//			}
		}
	}
}