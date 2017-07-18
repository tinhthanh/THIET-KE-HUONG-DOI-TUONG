package shape;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Line extends Shape{
	public Line(){
		this.shapevisitor = new Yescontain();
	}
	java.awt.Image image ;
    public void draw(Graphics g){
    	Graphics2D g2 = (Graphics2D)g;   
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) trongsuot/100));
    	 g2.setStroke(new BasicStroke(thickness));
    	float dash[] = { 10 };
    	if(dashed){
        g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dash, 0));
    	} 		
    	g2.setColor(color);
        if(gradient){
        	 GradientPaint redtowhite = new GradientPaint(x, y,col1, x+width, y+height,col2);
           g2.setPaint(redtowhite);
        }
           //------------------End ----------    	
        g2.drawLine(x, y, newx, newy);
    }
    @Override
    public Shape getBoudingbox(){
    	Shape rt = new Boundingbox(x, y, width, height) ;
    	x = (int) rt.getPoints()[0].getX() ;
		y =(int) rt.getPoints()[0].getY() ;
		newx = (int)rt.getPoints()[1].getX() ;
		newy =(int) rt.getPoints()[1].getY() ; ;
    	return rt;
    }
   @Override
   public void setBoudingbox(Shape shape){
	   x = (int) shape.getPoints()[0].getX() ;
		y =(int) shape.getPoints()[0].getY() ;
		newx = (int)shape.getPoints()[1].getX() ;
		newy =(int) shape.getPoints()[1].getY() ; 
   }
}
