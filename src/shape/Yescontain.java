package shape;
import java.io.Serializable;
public class Yescontain implements Shapevisitor ,Serializable{
	@Override
	public int contains(int x , int y , Shape shape) {
		 if(x >= shape.x && x <= shape.x + shape.width
		            && y >= shape.y && y <= shape.y + shape.height){
		    	return 1 ;
		    }else{
		    	return -1 ;
		    }
	}
}
