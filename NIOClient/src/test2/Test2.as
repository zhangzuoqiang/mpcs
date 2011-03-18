package test2
{
	import flash.display.Sprite;
	
	import org.aswing.AsWingManager;
	import org.aswing.EmptyLayout;
	import org.aswing.JFrame;
	
	[SWF(width=750,height=550)]
	/**
	 * <b>Description: </b>测试程序主面板
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class Test2 extends Sprite {
		
		private static var instance:Test2;
		
		private var frame:JFrame;
		public var client:ClientContainer;
		private var server:ServerContainer;
		private var btnGroup:ButtonGroupContainer;
		
		public function Test2() {
			super();
			instance = this;	
			initUI();
		}
		
		private function initUI():void{
			AsWingManager.initAsStandard(this);
			frame = new JFrame(this, "NIOClient");
			frame.getContentPane().setLayout(new EmptyLayout());
			
			client = new ClientContainer();
			client.setLocationXY(0,5);
			client.setSizeWH(300, 500);
			
			server = new ServerContainer();
			server.setLocationXY(420,5);
			server.setSizeWH(300, 500);
			
			btnGroup = new ButtonGroupContainer();
			btnGroup.setLocationXY(320,55);
			btnGroup.setSizeWH(120, 500);
			
			frame.getContentPane().appendAll(client, server, btnGroup);
			
			frame.setSizeWH(750, 500);
			frame.setResizable(false);
			frame.show();
		}
		
		/**获取Test单例**/
		public static function getInstance():Test2 {
			return instance;
		}
	}
}