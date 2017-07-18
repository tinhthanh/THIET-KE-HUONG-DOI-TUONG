package shape;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.accessibility.AccessibleContext;
public class MyImage extends Shape {
	public MyImage(){
		this.shapevisitor = new Yescontain() ;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) trongsuot/100));
		if(x!=0 && y!=0&& path!=null){
    		g2.drawImage(path.getImage() ,x, y,null);
    	}
	}
}
