package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.Serializable;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.ImageIcon;

public abstract class Shape implements Serializable, Accessible {
	public Shapevisitor shapevisitor;
	public Color color, col1, col2;
	public int thickness, gom;
	public int trongsuot;
	public boolean dashed, filled, gradient;
	public int x, y, width, height;
	public int newx, newy;
	public ImageIcon path;
	public String[] listText;
	public abstract void draw(Graphics g);
	public int contains(int x, int y) {
		return shapevisitor.contains(x, y, this);
	}
	public Shape getBoudingbox() {
       return  new Boundingbox(x, y, width, height);
	}
	public void setBoudingbox(Shape shape) {
		x = shape.x;y = shape.y;
		width = shape.width;
		height = shape.height;
	}
	public void setShapevisitor(Shapevisitor shapevisitor) {
		this.shapevisitor = shapevisitor;
	};
	@Override
	public AccessibleContext getAccessibleContext() {return null;}
	public void setPont(int x, int y) {};
	public Point2D[] getPoints() {return null;}
}