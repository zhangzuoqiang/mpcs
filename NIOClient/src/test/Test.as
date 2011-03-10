package test
{
	import flash.display.Sprite;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;

	/**
	 * Description: 用来测试与服务端的交互
	 * <br/>Author: zhangzuoqiang
	 * <br/>Date: 2011-3-10
	 **/
	[SWF(width=400,height=550)]
	public class Test extends Sprite {
		
		private static var instance:Test;
		private var client:ClientPanel;
		private var line:Sprite;
		private var server:ServerPanel;
		
		public function Test() {
			instance = this;
			stage.scaleMode = "noScale";
			// 初始化界面组件
			initUI();
		}
		
		private function initUI():void{
			client = new ClientPanel();
			client.x = 0;
			client.y = 5;
			
			line = new Sprite();
			line.graphics.beginFill(0x0099FF);
			line.graphics.drawRect(0, client.height + 20, 400, 2);
			
			server = new ServerPanel();
			server.x = 0;
			server.y = 200;
			
			addChild(client);
			addChild(line);
			addChild(server);
		}
		
		/**获取Test单例**/
		public static function getInstance():Test {
			return instance;
		}
	}
}