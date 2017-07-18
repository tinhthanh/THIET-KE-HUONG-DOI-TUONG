package shape;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.accessibility.AccessibleContext;
public class Text extends Shape{
	public Text(){
		this.shapevisitor =new Yescontain();
	}
    public void draw(Graphics g){
    	Graphics2D g2 = (Graphics2D)g ;
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) trongsuot/100));
      	g2.setColor(color);    
        if(listText != null){
        	FontMetrics fm = g.getFontMetrics();
        	int Ilength = fm.stringWidth(listText[0]);
        	int Iheight = fm.getHeight();
        	  if(gradient){
             	 GradientPaint redtowhite = new GradientPaint(x, y,col1, x+Ilength, y+Iheight,col2);
                g2.setPaint(redtowhite);
             }
        g2.setFont(new Font(listText[1], Integer.valueOf(listText[3]) ,Integer.valueOf(listText[2])));
        g2.drawString(listText[0], x+10, y+15);
    	this.width = Ilength +10;
    	this.height = Iheight +5;
        }
    }
}
