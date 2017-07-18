package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import magic.Lammo;
import magic.Magicpictures;
import magic.Trangden;
import magic.Zoom;
import magic.InterfaceMagic;
import shape.Eraser;
import shape.Freehand;
import shape.Line;
import shape.MyImage;
import shape.Mypicture;
import shape.Shape;
import shape.Text;
import view.MyJpanel;
import controller.IControllerPanit;

public class PaintModel implements IModelPaint {
	// -----------option ------------------
	private int thickness = 2; // do day
	private Color color = new Color(0, 0, 0, 255);
	private Color col1 = new Color(0, 0, 0, 255);
	private Color col2 = new Color(0, 0, 0, 255);
	private boolean dashed ;
	private boolean filled;
	private boolean gradient;
	private int trongsuot = 100;
	// ------------option ---------------------
	private Point bacdau, rePaint;
	private Image backgound;
	private BufferedImage imgroot;
	private Shape temp, boundingbox;
	private String status = "1";
	private String pahtimage = "ico.png";
	private IControllerPanit iControllerPanit;
	private Picture listAshape = new Mypicture();
	private boolean doMau = false, ismove = false;
	private SimpleFactoryShape simpleFactoryShape = new SimpleFactoryShape();
	private boolean isRelease = true; // kt dk de chon tip 1
	private int vitriShape = -1, containsBoud = -1; // index hinh dc chon
	// ------------------
	private InterfaceMagic InterfaceMagic = new Magicpictures();;
	// ----------------
	public boolean cusor;
	private String listText[] = new String[2];
	public PaintModel() {
		temp = new Mypicture();
		backgound = new BufferedImage(500, 400, BufferedImage.TYPE_INT_ARGB);
		temp.width = backgound.getWidth(null);
		temp.height = backgound.getHeight(null);
		temp.path = new ImageIcon(backgound);
		listAshape.add(temp);
		temp = null;
	}
	public Image createImg(String data) { // chu y
		setInterfaceMagic(new Magicpictures());
		backgound =magic(null, data);
		return backgound;
	}
	public Image magic(Image image, String type) {
		return InterfaceMagic.magicPture(image, type);
	}

	@Override
	public void zoom(String e) {
		setInterfaceMagic(new Zoom());
		temp = new Mypicture();
		temp.path = new ImageIcon(listAshape.picture());
		temp.path = new ImageIcon(magic(backgound, "zoom/" + e));
		temp.width = temp.path.getIconWidth();
		temp.height = temp.path.getIconHeight();
		listAshape.remove(listAshape.size() - 1);
		listAshape.add(temp);
		temp = null;
	}
	public void draw(Graphics g) {
		for (int i = listAshape.from(); i < listAshape.size(); i++) 
			listAshape.getShape(i).draw(g);
		if (temp != null)
			temp.draw(g);
		if (boundingbox != null)
			boundingbox.draw(g);
	}
	public void setCusor(boolean cusor) {
		this.cusor = cusor;
	}
	public void updata() {
		iControllerPanit.updata();
	}
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	@Override
	public void setTtrongSuot(int trongsuot) {
		// TODO Auto-generated method stub
		this.trongsuot = trongsuot;
	}
	@Override
	public void setCol(Color col1, Color col2) {
		this.col1 = col1;
		this.col2 = col2;
	}
	@Override
	public void setStatus(String status) { // khi goi ham set Status no se tao
	     vitriShape = -1;
		newShape(status);
		this.status = status;
	}
	@Override
	public void setPathImage(String path) {
		this.pahtimage = path;
		try {
			imgroot = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Point getRePaint() {
		return rePaint;
	}
	public void setBacdau(Point bacdau) {
		if (!ismove) {
			this.bacdau = bacdau;
		} else {
			vitriShape =(listAshape.Checkcontains((int) bacdau.getX(), (int) bacdau.getY()));
			if (vitriShape >= 0) {
				temp = listAshape.getShape(vitriShape);
				if (temp instanceof MyImage) {
					imgroot = (BufferedImage) temp.path.getImage();
				}
			}
		}
	}
	public void setRePaint(Point rePaint) {
		this.rePaint = rePaint;
		if (!ismove) 
			prepare();
			move();
	}
	public void setKetthuc(Point ketthuc) { 
		if (!ismove) {
			if (bacdau != null && temp != null) {
				listAshape.add(temp);
				temp = null;
			}
		} else {
			isRelease = true;	
		}
	}
	public MyJpanel CropImg() {
		MyJpanel a = iControllerPanit.getMyJpanel();
		a.setBackground(Color.DARK_GRAY);
		a.setPreferredSize(new Dimension(1000, 1000));
		return a;
	}
	public void setBackGound(Image img) {
		backgound = img;
		temp = new Mypicture();
		temp.width = backgound.getWidth(null);
		temp.height = backgound.getHeight(null);
		temp.path = new ImageIcon(backgound);
		listAshape.add(temp);
		temp = null; // fix lon undo k dc xoa
		iControllerPanit.viewPaint().setVisible(true);
		iControllerPanit.getFactoryView().setVisible();
	}
	public Shape newShape(String tybe) { 
		setBoolean("move", false); 
		setCusor(true);
		boundingbox = null;
		temp = simpleFactoryShape.createShape(tybe);
		return temp;
	}
	@Override
	public void setController(IControllerPanit iControllerPanit) {
		this.iControllerPanit = iControllerPanit;
	}

	@Override
	public Image getBackgound() {
		return backgound;
	}
	@Override
	public void setText(String[] list) {
		this.listText = list;
	}
	public void move() {
		if (vitriShape >= 0) {
			temp.x = (int) rePaint.getX() - temp.width / 2;
			temp.y = (int) rePaint.getY() - temp.height / 2;
			boundingbox = temp.getBoudingbox(); 
		if(doMau){
				optionMenu("color");
		   }
		}
		if(isRelease && boundingbox != null) {
			containsBoud = boundingbox.contains((int) rePaint.getX(), (int) rePaint.getY());
		}
	     	isRelease = false ;
		if (boundingbox != null && containsBoud != -1 ) { 
			boundingbox.getPoints()[containsBoud] = rePaint.getLocation();
			temp.setBoudingbox(boundingbox);
			if (temp instanceof MyImage && temp.width > 0 && temp.height > 0) {
				String size = "reSize/" + temp.width + "-" + temp.height;
				setInterfaceMagic(new Magicpictures());
				ImageIcon tep = new ImageIcon(magic(imgroot, size));
				temp.path = tep;
			}
		}
	}
	public void prepare() {
		setStatus(status);
		if (bacdau != null) {
			temp.dashed = dashed;
			temp.gradient = gradient;
			temp.thickness = thickness;
			temp.trongsuot = trongsuot;
			temp.filled = filled;
			temp.color = color;
			temp.col1 = col1;
			temp.col2 = col2;

			temp.x = Math.min((int) bacdau.getX(), (int) rePaint.getX());
			temp.y = Math.min((int) bacdau.getY(), (int) rePaint.getY());
			temp.width = Math.abs((int) bacdau.getX() - (int) rePaint.getX());
			temp.height = Math.abs((int) bacdau.getY() - (int) rePaint.getY());
			if (temp instanceof Line) {
				temp.x = (int) bacdau.getX();
				temp.y = (int) bacdau.getY();
				temp.newx = (int) rePaint.getX();
				temp.newy = (int) rePaint.getY();
			}
		}
		if (temp instanceof Freehand || temp instanceof Eraser) {
			temp.x = (int) rePaint.getX();
			temp.y = (int) rePaint.getY();
			listAshape.add(temp);
		}
		if (temp instanceof MyImage) {
			temp.path = new ImageIcon(imgroot);
			temp.width = imgroot.getWidth(null);
			temp.height = imgroot.getHeight(null);
		}
		if (temp instanceof Text) {
			temp.listText = listText;
		}
	}
	public void undo() {
		listAshape.undo();
	}
	public void restore() {
		listAshape.restore();
	}
	public void actionSave() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save graph");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg", "gif", "bmp", "png");
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("png", "jpg", "gif", "bmp", "png");
		fc.setFileFilter(filter);
		fc.setFileFilter(filter1);
		fc.setMultiSelectionEnabled(false);
		int select = fc.showSaveDialog(new JFrame());
		if (select == 0) {
			String path = fc.getSelectedFile().getPath() + "." + fc.getFileFilter().getDescription();
			save(path);
		}
	}
	//------
	public void save(String path) {
		try {
			int w = listAshape.getShape(listAshape.from()).width;
			int h = listAshape.getShape(listAshape.from()).height;
			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D ig2 = bi.createGraphics();
			temp = new Mypicture();
			temp.path = new ImageIcon(listAshape.picture());
			temp.draw(ig2);
			ImageIO.write(bi, "PNG", new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//---data
	public void actionOpen() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open Paint");
		int select = fc.showOpenDialog(null);
		if (select == 0) {
			String path = fc.getSelectedFile().getPath();
			System.out.println(path);
			readFile(path);
		}
	}
	public void readFile(String path) {
		ArrayList<Shape> data = null;
		FileInputStream fi;
		try {
			fi = new FileInputStream(path);
			ObjectInputStream oiStream = new ObjectInputStream(fi);
			data = (ArrayList<Shape>) oiStream.readObject();
			oiStream.close();
			this.listAshape.setListShape(data);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error read file\nFile open must is *.dat", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error read class", "Error", JOptionPane.ERROR_MESSAGE);
		}
		//updata();
	}
	public void actionSaveALL() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save graph");
		int select = fc.showSaveDialog(null);
		if (select == 0) {
			String path = fc.getSelectedFile().getPath();
			System.out.println(path);
			saveAll(path);
		}
	}
	public void saveAll(String path) {
		try {
			path += ".dat";
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream oStream = new ObjectOutputStream(f);
			oStream.writeObject(listAshape.getListShape());
			oStream.close();
			JOptionPane.showMessageDialog(null, "Save success", "Save success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Save", "Error save file", JOptionPane.OK_OPTION);
			System.out.println("Error save file\n" + e.toString());
		}
	}
  //--pictur
	public void Openfile() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter loc = new FileNameExtensionFilter("Image", "jpg", "png");
		fc.setFileFilter(loc);
		fc.setDialogTitle("Open graph");
		int select = fc.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				BufferedImage image = ImageIO.read(file);
				backgound = new ImageIcon(image).getImage(); // of tao
				setBackGound(backgound);
			} catch (Exception s) {
			}
		}
	}
	@Override
	public void optionMenu(String actionCommand) {
		// TODO Auto-generated method stub
		if (actionCommand.equals("color")) {
			if (vitriShape > 0) {
				temp = listAshape.getShape(vitriShape);
				temp.color = color;
			}
		} else if (actionCommand.equals("remove")) {
			if (vitriShape > 0) {
				listAshape.remove(vitriShape);
				setStatus(status);
			}
		} else if (actionCommand.equals("select")) {
			setBoolean("move", true);
		}else if(actionCommand.equals("New")){
			
		}
	}
	public void setInterfaceMagic(InterfaceMagic InterfaceMagic) {
		this.InterfaceMagic = InterfaceMagic;
	}
	@Override
	public void optionModel(String type) {
		// TODO Auto-generated method stub
		if (type.equals("undo")) {
			undo();
		} else if (type.equals("restore")) {
			restore();
		} else if (type.equals("actionSaveALL")) {
			actionSaveALL();
		} else if (type.equals("actionOpen")) {
			actionOpen();
		} else if (type.equals("actionSave")) {
			actionSave();
		}
		if (type.equals("Openfile")) {
			Openfile();  
		}
	}
	@Override
	public void setBoolean(String s, boolean a) {
		if (s.equals("domau")) {
			this.doMau = a;
		}
		if (s.equals("move")) {
			this.ismove = a;
		}
		if (s.equals("gradient")) {
			this.gradient = a;
		}
		if (s.equals("filled")) {
			this.filled = a;
		}
		if (s.equals("dashed")) {
			this.dashed = a;
		}	
	}
	@Override
	public void optionMagic(String type) {
		  setInterfaceMagic(new Magicpictures());
		if(type.equals("lamMo/")){
			setInterfaceMagic(new Lammo());
		}else if(type.equals("denTrang/")){
			setInterfaceMagic(new Trangden());
		}
		temp = new Mypicture();
		temp.path = new ImageIcon(listAshape.picture());
		backgound = magic(temp.path.getImage(), type);
		temp.width = backgound.getWidth(null);
		temp.height = backgound.getHeight(null);
		temp.path = new ImageIcon(backgound);
		listAshape.add(temp);
		temp = null;
	}
	
}