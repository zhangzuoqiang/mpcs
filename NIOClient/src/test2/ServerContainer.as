package test2
{
	import org.aswing.EmptyLayout;
	import org.aswing.JLabel;
	import org.aswing.JPanel;
	import org.aswing.JTextField;
	import org.aswing.LayoutManager;
	
	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ServerContainer extends JPanel {
		
		private var serverLabel:JLabel;
		
		private var label1:JLabel;
		private var label2:JLabel;
		private var label3:JLabel;
		private var label4:JLabel;
		
		private var head1:JTextField;
		private var head2:JTextField;
		private var head3:JTextField;
		private var body:JTextField;
		
		public function ServerContainer() {
			super(new EmptyLayout());
			initUI();
		}
		
		private function initUI():void{
			serverLabel = TestUtils.createLabel(serverLabel, "服务端返回", 0, 2);
			serverLabel.setSizeWH(280, 20);
			
			label1 = TestUtils.createLabel(label1, "Head-1", 20, 30);
			label2 = TestUtils.createLabel(label2, "Head-2", 20, 60);
			label3 = TestUtils.createLabel(label3, "Head-3", 20, 90);
			label4 = TestUtils.createLabel(label4, "Body", 20, 120);
			
			head1 = TestUtils.createTextField(head1, 90, 30, 120, true);
			head2 = TestUtils.createTextField(head2, 90, 60, 120, true);
			head3 = TestUtils.createTextField(head3, 90, 90, 120, true);
			
			body = TestUtils.createTextField(body, 90, 120, 200);
			body.setHeight(320);
			
			appendAll(serverLabel, label1, label2, label3, label4);
			appendAll(head1, head2, head3, body);
		}
		
	}
}