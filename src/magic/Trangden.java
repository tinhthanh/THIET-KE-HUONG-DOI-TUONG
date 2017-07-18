package magic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Trangden implements InterfaceMagic{
	@Override
	public Image magicPture(Image image, String type) {
		BufferedImage bf = null;
		if (type.equals("denTrang/")) {
			bf = (BufferedImage) denTrang(image);
		} 
	       return bf ;	
	}
	public Image denTrang(Image backgound) {
		BufferedImage c = (BufferedImage) backgound;
		BufferedImage bufimg = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = bufimg.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(c, 0, 0, c.getWidth(), c.getHeight(), null);
		return bufimg;
	}

}
