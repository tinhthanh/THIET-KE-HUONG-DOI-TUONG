package toolview;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IControllerPanit;

public class MyToolGIF extends JPanel implements InterfaceTool {
	int bacdauX;
	int bacdauY;
	private Rectangle selectionBounds;
	private Point xog;
	private Point clickPoint;
	private boolean flag = true;
	private ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
	public MyToolGIF(IControllerPanit iControllerPanit) {
		iControllerPanit.viewPaint().setVisible(false);
		setOpaque(false); // tao trong suot cho JPANEL
		MouseAdapter mouseHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
					iControllerPanit.getFactoryView().setVisible();
					flag = false;
					String path = "temp/view.gif";
					try {
						JFileChooser fc = new JFileChooser();
						generateFromBI(list, "temp/view.gif");		
						fc.setDialogTitle("Save Gif");
						FileNameExtensionFilter filter = new FileNameExtensionFilter("gjf", "gif");
						fc.setFileFilter(filter);
						fc.setMultiSelectionEnabled(false);
						int select = fc.showSaveDialog(new JFrame());
						if (select == 0) {
							path = fc.getSelectedFile().getPath() + "." + "gif";
						}
						File f = new File("temp/view.gif");
						File f2 = new File(path);
						Files.copy(f.toPath(), f2.toPath());

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				iControllerPanit.viewPaint().setVisible(true);
			}
			@Override
			public void mousePressed(MouseEvent e) { // bac dau click chuot
				flag = true;
				clickPoint = e.getPoint();
				selectionBounds = null;
				xog = null;
     			bacdauX = (int) e.getPoint().getX();
				bacdauY = (int) e.getPoint().getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) { // Buon chuot
				clickPoint = null;
				int x = Math.min(bacdauX, (int) e.getPoint().getX());
				int y = Math.min(bacdauY, (int) e.getPoint().getY());
				int width = Math.max(bacdauX - (int) e.getPoint().getX(), (int) e.getPoint().getX() - bacdauX);
				int height = Math.max(bacdauY - (int) e.getPoint().getY(), (int) e.getPoint().getY() - bacdauY);
				if (width > 0 && height > 0) {
					Timer timer = new Timer(100, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (flag) {
								try {
									Robot robot = new Robot();
									BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
									list.add(bufferedImage);
									// System.out.println("HUYnh tinh thyanh");
								} catch (AWTException ex) {
									ex.printStackTrace();
								}
							}
						}
					});
					timer.start();
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) { // keo le chuot
				Point dragPoint = e.getPoint();
				int x = Math.min(clickPoint.x, dragPoint.x);
				int y = Math.min(clickPoint.y, dragPoint.y);
				int width = Math.max(clickPoint.x - dragPoint.x, dragPoint.x - clickPoint.x);
				int height = Math.max(clickPoint.y - dragPoint.y, dragPoint.y - clickPoint.y);
				selectionBounds = new Rectangle(x, y, width, height); // ve ra
				xog = new Point(x + width - 70, y - 35); // ve ra nut Xong
				repaint();
			}
		};
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(new Color(0, 0, 0, 150));
		Area fill = new Area(new Rectangle(new Point(0, 0), getSize()));
		if (selectionBounds != null) {
			fill.subtract(new Area(selectionBounds));
		}
		g2d.fill(fill);
		if (selectionBounds != null) {
			g2d.setColor(Color.BLACK);
			g2d.draw(selectionBounds);
			g2d.drawImage(new ImageIcon("sample-images/xong.png").getImage(), (int) xog.getX(), (int) xog.getY(), null);
		}
		// end
		g2d.dispose();
	}
	@Override
	public Component getTool() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public void setVisible() {
		// TODO Auto-generated method stub
		setVisible(false);
	}
	public static void generateFromBI(ArrayList<BufferedImage> images,
			String output) throws IOException {
		Iterator<ImageWriter> itr = ImageIO.getImageWritersByFormatName("gif");
		ImageWriter gifWriter = itr.next();
		
		File outfile = new File(output);
		ImageOutputStream ios = ImageIO.createImageOutputStream(outfile);
		IIOMetadata metadata = getMetadata(gifWriter);
		gifWriter.setOutput(ios);
		gifWriter.prepareWriteSequence(null);
		for (int i = 0; i < images.size(); i++) {
			IIOImage temp = new IIOImage(images.get(i), null, metadata);
			gifWriter.writeToSequence(temp, null);
		}
	}
	private static IIOMetadata getMetadata(ImageWriter writer) {
		ImageTypeSpecifier img_type = ImageTypeSpecifier
				.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
		IIOMetadata metadata = writer.getDefaultImageMetadata(img_type, null);
		return metadata;
	}
}
