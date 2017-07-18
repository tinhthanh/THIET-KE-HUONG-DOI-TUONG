package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import model.Picture;
public class Mypicture extends Shape  implements Picture{
	private ArrayList<Shape> listAshape  = new ArrayList<>();
	private Stack<Shape> stack = new Stack<>();
	 public Mypicture() {
		// TODO Auto-generated constructor stub
		 this.shapevisitor = new Notcontain();
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(path!=null){
			g.drawImage(path.getImage(), 0,0,null);
		}
	}
	@Override
	public int from(){      
		     int rt = 0 ;    	
		for(int i = listAshape.size()-1 ; i>=0 ; i--){
			if(listAshape.get(i) instanceof Mypicture){
				rt = i ;
				break ;
			}
		}
		return  rt ;
	}
	@Override
	 public BufferedImage picture(){
			int w = listAshape.get(from()).width;
			int h =  listAshape.get(from()).height;
			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D ig2 = bi.createGraphics();
			for (int i = from(); i < listAshape.size(); i++) {
			   listAshape.get(i).draw(ig2);
			}
			return bi ;
}
	@Override
	public void add(Shape shape) {
		// TODO Auto-generated method stub
	  listAshape.add(shape);	
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return listAshape.size();
	}
	@Override
	public Shape getShape(int i) {
		// TODO Auto-generated method stub
		return listAshape.get(i);
	}
	@Override
	public int Checkcontains(int x, int y) {
		for (int i = from(); i < listAshape.size(); i++)
			if (listAshape.get(i).contains(x, y) != -1)
				return i;
		return -1;
	}
	@Override
	public ArrayList<Shape> getListShape() {
		return listAshape;
	}
	@Override
	public void setListShape(ArrayList<Shape> listShape) {
		this.listAshape = listShape ;
	}
   @Override 
   public void remove(int i){
	   listAshape.remove(i);
   }
@Override
public void undo() {
	if (listAshape.size() > 1) {
		stack.push(listAshape.get(listAshape.size() - 1));
		listAshape.remove(listAshape.size() - 1);
	}
}
@Override
public void restore() {
	if ( stack.size() > 0) {
		listAshape.add(stack.pop());
	}
}
}
