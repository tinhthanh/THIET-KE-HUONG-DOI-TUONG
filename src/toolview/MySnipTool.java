package toolview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.IControllerPanit;

public class MySnipTool extends JPanel implements InterfaceTool {
	int bacdauX;
	int bacdauY;
	private Rectangle selectionBounds;
	private Point clickPoint;
	public MySnipTool(IControllerPanit jp) {
		  jp.getFactoryView().getInterfaceview().Chup();
		setOpaque(false); // tao trong suot cho JPANEL
		MouseAdapter mouseHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // bac dau click chuot
				clickPoint = e.getPoint();
				selectionBounds = null;
//				System.out.println("Bac dau" + e.getPoint().toString());
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
					String data ="createImg/"+ x+"-"+y+"-"+width+"-"+height ;
					Image icon = jp.getMyJpanel().createImg(data);
					jp.getMyJpanel().sendData().setBackGound(icon);
					setVisible();	
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) { // keo le chuot
				Point dragPoint = e.getPoint();
				int x = Math.min(clickPoint.x, dragPoint.x);
				int y = Math.min(clickPoint.y, dragPoint.y);
				int width = Math.max(clickPoint.x - dragPoint.x, dragPoint.x - clickPoint.x);
				int height = Math.max(clickPoint.y - dragPoint.y, dragPoint.y - clickPoint.y);
				selectionBounds = new Rectangle(x, y, width, height);
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent me) { 
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
		}
		g2d.dispose();
	}
	@Override
	public Component getTool() {
		return this;
	}
	@Override
	public void setVisible() {
		this.setVisible(false);
	}
}
