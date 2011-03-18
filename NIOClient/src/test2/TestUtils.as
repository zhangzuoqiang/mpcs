package test2
{
	import flash.events.FocusEvent;
	
	import org.aswing.AsWingConstants;
	import org.aswing.JButton;
	import org.aswing.JLabel;
	import org.aswing.JTextField;

	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class TestUtils {
		
		private static var btnNum:int = 0; // 记录测试用按钮数目
		
		public function TestUtils() {
		}
		
		/**
		 *  创建测试按钮JButton的方法
		 * @param btn
		 * @param label
		 * @param x
		 * @param y
		 * @return 
		 */		
		public static function createBtn(btn:JButton, label:String, x:int = 5, y:int = 30):JButton{
			btnNum++;
			btn = new JButton(label);
			btn.name = btnNum.toString();
			btn.setLocationXY(x, y * btnNum);
			btn.setSizeWH(100, 30);
			return btn;
		}
		
		/**
		 *  创建JTextField的方法
		 * @param text 
		 * @param x 
		 * @param y 
		 * @param width 
		 * @param numRestrict 
		 * @param inputAble 
		 * @return 
		 */		
		public static function createTextField(text:JTextField, x:int, y:int, width:int=120, numRestrict:Boolean = false):JTextField{
			text = new JTextField();
			text.setLocationXY(x, y);
			text.setSizeWH(width, 20);
			if(numRestrict){
				text.setRestrict("0-9");
			}
			text.addEventListener(FocusEvent.FOCUS_IN, focusInHandler);
			text.addEventListener(FocusEvent.FOCUS_OUT,focusOutHandler);
			return text;
		}
		
		/**
		 * 获得焦点事件处理
		 * @param evt
		 */		
		private static function focusInHandler(evt:FocusEvent):void{
			evt.target.borderColor = 0xCC00CC;
		}
		
		/**
		 * 失去焦点事件处理
		 * @param evt
		 */		
		private static function focusOutHandler(evt:FocusEvent):void{
			evt.target.borderColor = 0x000000;
		}
		
		/**
		 *  创建Label方法
		 * @param label
		 * @param text
		 * @param x
		 * @param y
		 * @return 
		 */		
		public static function createLabel(label:JLabel, text:String, x:int, y:int):JLabel{
			label = new JLabel(text);
			label.setLocationXY(x, y);
			label.setSizeWH(60,20);
			label.setSelectable(true);
			label.setHorizontalAlignment(AsWingConstants.CENTER);
			return label;			
		}
		
	}
}