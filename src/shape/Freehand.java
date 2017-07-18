package shape;
import java.awt.AlphaComposite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Freehand extends Shape{
	public Freehand(){
		this.shapevisitor = new Notcontain() ;
	}
    public void draw(Graphics g){
    	Graphics2D g2 =(Graphics2D) g ;
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) trongsuot/100));
        g2.fillOval(x, y, thickness, thickness); 
		 g2.setColor(color);
	     if(gradient){
	    	 GradientPaint redtowhite = new GradientPaint(x, y,col1, x+width, y+height,col2);
	        g2.setPaint(redtowhite);
	     }
    }
}
