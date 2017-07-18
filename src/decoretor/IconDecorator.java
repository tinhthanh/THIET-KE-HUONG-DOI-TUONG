package decoretor;

import java.awt.Graphics;

import javax.swing.JComponent;

public class IconDecorator extends Decorator {
   public IconDecorator(JComponent c) {
      super(c);
  }
   public void paint(Graphics g) {
      super.paint(g);
      if(bg!=null)
      g.drawImage(bg.getImage(),x,y,null);
   }

}
