package model;
import java.awt.Color;
import java.awt.TexturePaint;

import javax.swing.ImageIcon;

import shape.Eraser;
import shape.Freehand;
import shape.Line;
import shape.MyImage;
import shape.Oval;
import shape.Rectangle;
import shape.RoundRectangle;
import shape.Shape;
import shape.Text;
public class SimpleFactoryShape {
	  Shape temp ;
  public Shape createShape(String tybe){
	      temp = null;
		if (tybe.equals("1")) {
			temp = new Line();
		} else if (tybe.equals("2")) {
			temp = new Oval();
		} else if (tybe.equals("3")) {
			temp = new RoundRectangle();
		} else if (tybe.equals("4")) {
			temp = new Freehand();
		} else if (tybe.equals("5")) {
			temp = new Rectangle();
		} else if (tybe.equals("6")){
			temp = new MyImage();
		} else if(tybe.equals("7")){
			temp = new Eraser() ;
		} else if(tybe.equals("8")){
			temp = new Text() ;
		}
	  return temp ;
  }
}
