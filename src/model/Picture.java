package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import shape.Shape;

public interface Picture {
	public void add(Shape shape);
    public int  from();
    public int  size();
    public Shape getShape(int i);
    public int Checkcontains(int x, int y);
    public ArrayList<Shape> getListShape() ;
    public void setListShape(ArrayList<Shape> listShape); 
    public BufferedImage picture();
    public void remove(int i);
    public void undo() ;
    public void restore();
}
