package decoretor;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
public abstract class Decorator extends JComponent {
	ImageIcon  bg ;
	int x, y  ;
	Color col1 , col2 ;
   public Decorator(JComponent c) {
      setLayout(new BorderLayout());
      add( c,BorderLayout.CENTER);
   }
public void setBg(ImageIcon bg) {
	this.bg = bg;
}
public void setColor(Color col1 ,Color col2) {
	this.col1 = col1;
	this.col2 = col2 ;
}
public void setX(int x) {
	this.x = x;
}
public void setY(int y) {
	this.y = y;
}

   
}
