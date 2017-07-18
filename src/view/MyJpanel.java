package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import model.PaintModel;
public class MyJpanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	Object model;
	Dimension d = new Dimension(600, 300);
	PopupMenu p;
	// private KeyAdapter apiFlash = new ModelFlashBir() ; // flash bir
	public MyJpanel() {
		super(new BorderLayout());
/*
*/    setFocusable(true); // flash bir

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setPreferredSize(d);
		model = new PaintModel();
	}

	public Dimension getD() {
		return d;
	}

	public void setD(Dimension d) {
		this.d = d;
	}

	public Image createImg(String data) {
		return sendData().createImg(data);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		
	/*	  if(apiFlash!=null){ // flash bir 
			  apiFlash.paint(g); }*/
		  
		sendData().draw(g);

	}

	@Override
	public void mouseClicked(MouseEvent e) { // click chuot
		if (e.getModifiers() == 4) {
			option().show(this, e.getX(), e.getY());
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) { // De chuot trai
		sendData().setBacdau(new Point(e.getX(), e.getY()));
	}
	@Override
	public void mouseReleased(MouseEvent e) { // Nha chuot trai
		sendData().setKetthuc(new Point(e.getX(), e.getY()));
		repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e) { // keo le chuot
		sendData().setRePaint(new Point(e.getX(), e.getY()));
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if (sendData().cusor) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			repaint();
		}
	}
	public PaintModel sendData() {
		return (PaintModel) model;
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		sendData().optionMenu(a.getActionCommand());
	}
	public PopupMenu option() {
		PopupMenu a = new PopupMenu();
		MenuItem b3 = new MenuItem("Delete");
		b3.setActionCommand("remove");
		MenuItem b4 = new MenuItem("Select");
		b4.setActionCommand("select");
		b4.addActionListener(this);
		b3.addActionListener(this);
		a.add(b3);
		a.addSeparator();
		a.add(b4);
		this.add(a);
		return a;
	}
}
