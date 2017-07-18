package shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
public class Boundingbox extends Shape {
	float dash[] = { 10 };
	public Boundingbox(){
		this.shapevisitor = new Notcontain();
	}
	  private Point2D[] points;
	    private final int SIZE = 12;
	    public Boundingbox(int x1, int y2 , int w , int h){
	    	x = x1 ; y = y2 ;width = w ; height = h ;
	    	   points = new Point2D[2];
	           points[0] = new Point2D.Double(x, y);
	           points[1] = new Point2D.Double(x+w, y+h);
	    }
	@Override
	public Point2D[] getPoints() {
		update();
		return points;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		  Graphics2D g2 = (Graphics2D) g;
		  g2.setColor(Color.BLACK);
		  g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dash, 0));
		     for (Point2D point : points) {
	            double x = point.getX() - SIZE / 2;
	            double y = point.getY() - SIZE / 2;
	            g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
	        }
	        Rectangle2D r = new Rectangle2D.Double();
	        r.setFrameFromDiagonal(points[0], points[1]);
	        g2.draw(r);    
	}
	@Override
	public int contains(int x3, int y3) {
		// TODO Auto-generated method stub
		int pos = -1 ;
		 for (int i = 0; i < points.length; i++) {
           double x1 = points[i].getX() - 8 / 2;
           double y1 = points[i].getY() - 8 / 2;
           Rectangle2D r = new Rectangle2D.Double(x1, y1, 8, 8);
           if (r.contains(new Point(x3,y3))) {
               pos = i;
              update();
           }
		 }
           return pos ;
	}
	public void update(){
		 x = Math.min((int)points[0].getX(),(int) points[1].getX());
         y = Math.min((int)points[0].getY(),(int) points[1].getY());
         width =Math.abs((int)points[0].getX() -(int) points[1].getX());
         height = Math.abs((int)points[0].getY() - (int)points[1].getY()) ;
	}
}
