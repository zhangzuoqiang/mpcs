package {
	import flash.display.Sprite;

	public class Button extends Sprite {
		public function Button(w:Number=30,h:Number=20) {
			this.graphics.beginFill(0x000000, 1);
			this.graphics.drawRect(0, 0, w, h);
			this.graphics.endFill();
		}
	}
}
