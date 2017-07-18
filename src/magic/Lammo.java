package magic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Lammo implements InterfaceMagic{
	@Override
	public Image magicPture(Image image, String type) {
		BufferedImage bf = null ;
		  if (type.equals("lamMo/")) {
				bf = (BufferedImage) lamMo(image);
			}
		  return bf ;	  
	}
	public Image lamMo(Image backgound) {
		BufferedImage mshi = (BufferedImage) backgound;
		float[] blurKernel = { 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f };
		BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
		BufferedImage bluri = blur.filter(mshi, new BufferedImage(mshi.getWidth(), mshi.getHeight(), mshi.getType()));
		return bluri;
	}			
}
