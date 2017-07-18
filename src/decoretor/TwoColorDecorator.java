package decoretor;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
public class TwoColorDecorator extends Decorator {
   public TwoColorDecorator(JComponent c) {
      super(c);
   }
   @Override
   public void paint(Graphics g) {
      super.paint(g); // first draw the parent button
  	if(col1!=null && col2 != null){
		Graphics2D g2d = (Graphics2D)g;
		int w = getWidth();
		int h = getWidth();
		GradientPaint gp = new GradientPaint(0, 0, col1, 0, h, col2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}
    }
 }
