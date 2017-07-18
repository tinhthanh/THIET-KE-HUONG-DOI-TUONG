package magic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Zoom implements InterfaceMagic {

	@Override
	public Image magicPture(Image image, String type) {
		// TODO Auto-generated method stub
		BufferedImage bf =  null ;
		String list[] = type.split("/");
		type = list[0];
		if (type.equals("zoom")) {
			bf = (BufferedImage) zoom(list[1], image);
		}
		return bf;
	}	
	public Image zoom(String e, Image backgound) {
		double e1 = Double.valueOf(e);
		BufferedImage bg = (BufferedImage) backgound;
		int type = (bg.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage tmp = new BufferedImage((int) (backgound.getWidth(null) * e1), (int) (backgound.getHeight(null) * e1), type);
		Graphics2D g2 = tmp.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(bg, 0, 0, (int) (backgound.getWidth(null) * e1), (int) (backgound.getHeight(null) * e1), null);
		g2.dispose();
		return tmp;
	}
	
}
