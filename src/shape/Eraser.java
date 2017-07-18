package shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Eraser extends Shape{
	public Eraser() {
		this.shapevisitor = new Notcontain() ;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 =(Graphics2D) g ;
		  g2.setColor(Color.WHITE);
        g2.fillRect(x, y, thickness, thickness);        
	}


}
