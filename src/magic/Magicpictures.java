package magic;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Magicpictures implements InterfaceMagic {
	private Image temp;
	public Magicpictures() {
	}
	@Override
	public Image magicPture(Image image, String type) {
		// TODO Auto-generated method stub
		String list[] = type.split("/");
		type = list[0];
		BufferedImage bf = null;
		if (type.equals("90Left")) {
			bf = xoay90Left(image);
		} else if (type.equals("90Right")) {
			bf = xoay90Right(image);
		} else if (type.equals("180")) {
			bf = xoay180(image);
		}  else if (type.equals("group")) {
			bf = (BufferedImage) image;
		} else if (type.equals("latAnh")) {
			bf = (BufferedImage) latAnh(image, list[1]);
		} else if (type.equals("reSize")) {
			String size[] = list[1].split("-");
			int w = Integer.valueOf(size[0]);
			int h = Integer.valueOf(size[1]);
			bf = (BufferedImage) resize(w, h, image);
		} else if (type.equals("createImg")) {
			String data[] = list[1].split("-");
			int x = Integer.valueOf(data[0]);
			int y = Integer.valueOf(data[1]);
			int w = Integer.valueOf(data[2]);
			int h = Integer.valueOf(data[3]);
			bf = (BufferedImage) createImg(x, y, w, h);
		} 
		return bf;
	}

	public BufferedImage xoay180(Image backgound) {
		BufferedImage img = (BufferedImage) backgound;
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage newImage = new BufferedImage(w, h, img.getType());
		Graphics2D g2 = newImage.createGraphics();
		g2.rotate(Math.toRadians(180), w / 2, h / 2);
		g2.drawImage(img, null, 0, 0);
		return newImage;
	}

	public BufferedImage xoay90Left(Image backgound) {
		final BufferedImage img = (BufferedImage) backgound;
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage newImage = new BufferedImage(height, width, img.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				newImage.setRGB(y, width - x - 1, img.getRGB(x, y));
			}
		}
		return newImage;

	}

	public BufferedImage xoay90Right(Image backgound) {
		final BufferedImage img = (BufferedImage) backgound;
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage newImage = new BufferedImage(height, width, img.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				newImage.setRGB(height - 1 - j, i, img.getRGB(i, j));

		return newImage;
	}

	public Image latAnh(Image backgound, String s) {
		BufferedImage mshi = (BufferedImage) backgound;
		BufferedImage bufimg = new BufferedImage(mshi.getWidth(null), mshi.getHeight(null), mshi.getType());
		Graphics gb = bufimg.getGraphics();
		gb.drawImage(mshi, 0, 0, null);
		gb.dispose();
		AffineTransformOp op = null;
		AffineTransform tx = null;
		if (s.equals("latdoc")) {
			tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-mshi.getWidth(null), 0);
		} else if (s.equals("latngang")) {
			tx = AffineTransform.getScaleInstance(1, -1);
			tx.translate(0, -mshi.getHeight(null));
		}
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		bufimg = op.filter(bufimg, null);
		Graphics2D g2d = (Graphics2D) gb;
		g2d.drawImage(bufimg, null, bufimg.getWidth(), 10);
		return bufimg;
	}
	public Image createImg(int x, int y, int width, int height) {
		try {
			Robot robot = new Robot();
			temp = robot.createScreenCapture(new Rectangle(x, y, width, height));
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
		return temp;
	}

	public Image resize(int w, int h, Image backgound) {
		BufferedImage bg = (BufferedImage) backgound;
		int type = (bg.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage tmp = new BufferedImage(w, h, type);
		Graphics2D g2 = tmp.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(bg, 0, 0, w, h, null);
		g2.dispose();
		return tmp;
	}

	

}