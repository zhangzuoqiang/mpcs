package test {
	import flash.display.Sprite;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import flash.text.TextFieldType;

	public class Button extends Sprite {
		
		private var lbl:TextField;
		
		public function Button(label:String = "按钮", w:Number=50, h:Number=20) {
			lbl = new TextField();
			lbl.width = w;
			lbl.height = h;
			lbl.x = 0;
			lbl.y = 0;
			lbl.text = label;
			lbl.type = TextFieldType.DYNAMIC;
			lbl.selectable = false;
			lbl.wordWrap = false;
			lbl.autoSize = TextFieldAutoSize.CENTER;
			addChild(lbl);
			
			this.graphics.beginFill(0x66FF00, 1);
			this.graphics.drawRect(0, 0, w, h);
			this.graphics.endFill();
		}
	}
}
