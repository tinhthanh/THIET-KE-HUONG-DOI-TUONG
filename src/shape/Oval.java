package shape;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.accessibility.AccessibleContext;
public class Oval extends Shape{
	public Oval(){
		this.shapevisitor = new Yescontain();
	}
	public void draw(Graphics g){  
    	Graphics2D g2 = (Graphics2D)g ;
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) trongsuot/100));
    	g2.setStroke(new BasicStroke(thickness));
    	float dash[] = { 10 };
    	if(dashed){
        g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dash, 0));
    	}
         g2.setColor(color);    
    	
    	// ------------ Them phan nay la no doi mau	
     if(gradient){
        GradientPaint redtowhite = new GradientPaint(x, y,col1, x+width, y+height,col2);
        g2.setPaint(redtowhite);
     }
        //------------------End ----------
        if(filled){
            g2.fillOval(x,y, width,height); //draws Oval
        }
        else{		  	
            g2.drawOval(x, y, width,height); //draws Oval
        }
      
    }

}

