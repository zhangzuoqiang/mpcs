package test
{
	import flash.events.FocusEvent;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import flash.text.TextFieldType;

	/**
	 * Description: 测试 工具类
	 * <br/>Author: zhangzuoqiang
	 * <br/>Date: 2011-3-10
	 **/
	public class MoreUtils {
		
		private static var btnNum:int = 0; // 记录测试用按钮数目
		
		/**
		 * 生成测试按钮
		 * @param btn
		 */		
		public static function createBtn(btn:Button, label:String, x:int = 5, y:int = 30):Button{
			btn = new Button(label, 80);
			btn.x = x ;
			btn.y = y * btnNum;
			btnNum++;
			return btn;
		}
		
		/**
		 * 生成带格式的TextField  
		 * @param text
		 * @param x
		 * @param y
		 * @param width
		 * @param inputAble
		 * @param numRestrict
		 * @return 
		 */			
		public static function createTextField(text:TextField, x:int, y:int, width:int=120, 
											   numRestrict:Boolean = false, inputAble:Boolean = true):TextField{
			text = new TextField();
			text.x = x;
			text.y = y;
			text.border = true;
			if(inputAble == true){
				text.type = TextFieldType.INPUT;
			}else{
				text.type = TextFieldType.DYNAMIC;
			}
			if(numRestrict == true){
				text.restrict = "0-9";
			}
			text.width = width;
			text.height = 20;
			text.addEventListener(FocusEvent.FOCUS_IN, focusInHandler);
			text.addEventListener(FocusEvent.FOCUS_OUT,focusOutHandler)
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
		 * 生成带格式的Label 
		 * @param label
		 * @param text
		 * @param x
		 * @param y
		 * @return 
		 */		
		public static function createLabel(label:TextField, text:String, x:int, y:int):TextField{
			label = new TextField();
			label.x = x;
			label.y = y;
			label.htmlText = "<b>" + text + "</b>";
			label.width = 60;
			label.height = 100;
			label.selectable = false;
			label.autoSize = TextFieldAutoSize.RIGHT;
			return label;
		}
	}
}