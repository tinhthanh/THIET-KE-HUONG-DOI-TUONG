package decoretor;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class SquareDecorator  extends Decorator{

	   public SquareDecorator(JComponent c) {
		      super(c);
		  }
		   public void paint(Graphics g) {
		      super.paint(g);  
      		Graphics2D g2 = (Graphics2D) g;	
				g2.draw3DRect(30, 10, x, x, true);
			

}
}

