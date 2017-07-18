package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.ControllerPaint;
import decoretor.Decorator;
import decoretor.SquareDecorator;
import decoretor.TwoColorDecorator;

public class ViewPaint extends JFrame implements ActionListener, ChangeListener {
	int status = 1;
	int red, green, blue;
	private JLabel blLabel, blLabel2;
    private Mypanel panel2;
	// -----start option -------------
	private int trongsuot = 255;
	private boolean dashed = true;
	private boolean filled = false;
	private boolean gradient = false;
	private boolean zoom;
	// ---- end OPTION-----------------------
	private ControllerPaint controller;
//	private Mypanel m1;
	private Decorator s , m2 ,m1;
	private JSlider jSlider3;

	public ViewPaint(ControllerPaint c) {
		controller = c;
		controller.setView(this);
		setIconImage(null);
		setLayout(new BorderLayout());
		setTitle("Phan mem Paint");
		creatMenu();
		// ------Tool ------Color
		creatJpanelNorth();
		creatJpanelWest();
		creatJpanelCenter();
		creatJpanelSouth();

		Image image = new ImageIcon("images/gd.png").getImage();
		setIconImage(image);

		getContentPane().setBackground(Color.yellow);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void creatMenu() {
		MenuBar bar = new MenuBar();
		setMenuBar(bar);
		Menu fileMenu = new Menu("File");
		bar.add(fileMenu);
		fileMenu.addActionListener(this);
		MenuItem newItem = new MenuItem("New");
		fileMenu.add(newItem);
		MenuItem openItem = new MenuItem("Open");
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		MenuItem saveItem = new MenuItem("Save Data");
		saveItem.setActionCommand("SaveAll");
		fileMenu.add(saveItem);
		MenuItem saveAsItem = new MenuItem("Save Image");
		saveAsItem.setActionCommand("Save Image");
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		MenuItem exitItem = new MenuItem("Exit");
		fileMenu.add(exitItem);
		saveAsItem.addActionListener(this);
		Menu editMenu = new Menu("Edit");
		bar.add(editMenu);
		editMenu.addActionListener(this);
		MenuItem resize = new MenuItem("Resize");
		editMenu.add(resize);

		Menu helpMenu = new Menu("Help");

		bar.add(helpMenu);

		Menu theme = new Menu("Theme");
		theme.addActionListener(this);

		helpMenu.add(theme);

		MenuItem tabTheme = new MenuItem("Theme Tab");
		theme.add(tabTheme);
		MenuItem desktopTheme = new MenuItem("Theme Desktop");
		theme.add(desktopTheme);
	}

	private void creatJpanelNorth() {
		jSlider5 = new JSlider();
		jSlider5.setFont(new Font("Tahoma", 0, 10)); // NOI18N
		jSlider5.setMajorTickSpacing(25);
		jSlider5.setPaintLabels(true);
		jSlider5.setPaintTicks(true);
		jSlider5.setValue(100);
		jSlider5.setPreferredSize(new Dimension(100, 45));
		jSlider5.addChangeListener(this);
		// ----end tool color

		// -- tool Size ----
		jSlider4 = new JSlider();
		jSlider4.setFont(new Font("Tahoma", 0, 10)); // NOI18N
		jSlider4.setMajorTickSpacing(5);
		jSlider4.setMaximum(20);
		jSlider4.setPaintLabels(true);
		jSlider4.setPaintTicks(true);
		jSlider4.setValue(2);
		jSlider4.setMaximumSize(new Dimension(32767, 45));
		jSlider4.setMinimumSize(new Dimension(100, 45));
		jSlider4.setPreferredSize(new Dimension(100, 45));
		jSlider4.addChangeListener(this);
      
		panel2 = new Mypanel();
		panel2.setBackGound(new ImageIcon("24.png").getImage());
		
		panel2.setBackground(Color.black);
		panel2.setLayout(new FlowLayout());
		panel2.add(createTabbedPane());
		JToolBar too1 = new JToolBar("Menu", JToolBar.CENTER);
		too1.add(panel2);
		add(too1, BorderLayout.NORTH);
	}

	private void creatJpanelWest() {
		Border[] dockBorder = { BorderFactory.createTitledBorder("Tool"),
				BorderFactory.createTitledBorder("Palette"),
				BorderFactory.createLineBorder(Color.black),
				BorderFactory.createRaisedBevelBorder() };
		JButton button3 = new JButton(new ImageIcon("images/line-tool.png"));
		button3.setActionCommand("1");
		JButton button4 = new JButton(new ImageIcon("images/oval.png"));
		button4.setActionCommand("2");
		JButton button5 = new JButton(new ImageIcon("images/polygon.png"));
		button5.setActionCommand("3");
		JButton button6 = new JButton(new ImageIcon("images/pen.png"));
		button6.setActionCommand("4");
		JButton button7 = new JButton(new ImageIcon("images/rectangle.png"));
		button7.setActionCommand("5");
		// ----------------------------------
		JButton button8 = new JButton(new ImageIcon("images/zoom.png"));
		button8.setActionCommand("zoom");
		JButton button31 = new JButton(new ImageIcon("images/bgcolor.png"));
		button31.setActionCommand("ceo");
		JButton button41 = new JButton(new ImageIcon("images/black.png"));
		button41.setActionCommand("black");
		JButton button51 = new JButton(new ImageIcon("images/text.png"));
		button51.setActionCommand("Text");
		JButton button61 = new JButton(new ImageIcon("images/lammo.png"));
		button61.setActionCommand("lammo");
		JButton button71 = new JButton(new ImageIcon("images/move.png"));
		button71.setActionCommand("move");
		JButton button81 = new JButton(new ImageIcon("images/cucgom.png"));
		button81.setActionCommand("7");
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(6, 2));

		Mypanel panel4 = new Mypanel(null);
		panel4.setLayout(new FlowLayout());
		panel4.setBackGound(new ImageIcon("24.jpg").getImage());
		panel4.setBackground(Color.black);

		JButton button32 = new JButton(new ImageIcon("images/90Left.png"));
		button32.setActionCommand("Xoay90Left");
		JButton button42 = new JButton(new ImageIcon("images/90Right.png"));
		button42.setActionCommand("Xoay90Right");
		JButton button52 = new JButton(new ImageIcon("images/180.png"));
		button52.setActionCommand("Xoay180");
		JButton button62 = new JButton(new ImageIcon("images/group.png"));
		button62.setActionCommand("group");
		JButton button72 = new JButton(new ImageIcon("images/flip2.png"));
		button72.setActionCommand("LatAnh2");
		JButton button82 = new JButton(new ImageIcon("images/flip.png"));
		button82.setActionCommand("LatAnh");

		JPanel jPanel3 = new JPanel(new GridLayout(1, 2));
		jPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	m1 = new Mypanel(null);
	
		blLabel = new JLabel();
		blLabel.setPreferredSize(new Dimension(40, 40));
		blLabel.addMouseListener(mouse);
		blLabel2 = new JLabel();
		blLabel2.setPreferredSize(new Dimension(40, 40));
		blLabel2.addMouseListener(mouse);
		m1 = new TwoColorDecorator(blLabel);
		
		m1.setColor(Color.BLACK, Color.BLACK);
		m2 = new TwoColorDecorator(blLabel2);
//		m2.add(blLabel2);
		m2.setColor(Color.WHITE, Color.WHITE);
		jPanel3.add(m1);
		jPanel3.add(m2);
        s = new SquareDecorator(new JPanel());
		//s = new Mypanel(null);
		s.setX(20);

		jSlider3 = new JSlider();
		jSlider3.setFont(new Font("Tahoma", 0, 10)); // NOI18N
		jSlider3.setMajorTickSpacing(2);
		jSlider3.setMaximum(10);
		jSlider3.setDoubleBuffered(true);
		jSlider3.setPaintLabels(true);
		jSlider3.setPaintTicks(true);
		jSlider3.setValue(5);
		jSlider3.setPreferredSize(new Dimension(100, 45));
		jSlider3.addChangeListener(this);

		JToolBar too = new JToolBar("Nut ve", JToolBar.NORTH);
		too.setLayout(new BoxLayout(too, BoxLayout.Y_AXIS));
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new GridLayout(0, 2));
		JButton[] tooba = { button3, button4, button5, button6, button7,
				button8, button31, button41, button51, button61, button71,
				button81 };
		for (int i = 0; i < tooba.length; i++) {
			toolBar.add(tooba[i]);
			tooba[i].addActionListener(this);
		}
		JToolBar toolBar2 = new JToolBar();
		toolBar2.setLayout(new GridLayout(0, 2));
		toolBar2.setBorder(dockBorder[1]);
		JButton[] b1 = { button32, button42, button52, button62, button72,
				button82 };
		for (int i = 0; i < b1.length; i++) {
			b1[i].addActionListener(this);
			toolBar2.add(b1[i]);
		}

		toolBar.setBorder(dockBorder[0]);
		JPanel em = new JPanel();
		too.setBackground(new Color(0, 0, 0, 0));
		too.add(toolBar);
		too.add(toolBar2);
		too.add(jPanel3);
		s.setPreferredSize(new Dimension(70, 70));
		too.add(s);
		too.add(jSlider3);
		too.add(em);

		toolBar.setFloatable(false);
		toolBar2.setFloatable(false);
		add(too, BorderLayout.WEST);
	}

	private void creatJpanelCenter() {
		controller.createTool("Desktop", "theme");
		add(controller.getComponent(), BorderLayout.CENTER);
	}

	private void creatJpanelSouth() {
		JLabel label = new JLabel("Author: Nguyen Van Trong & Huynh Tinh Thanh");
		label.setForeground(Color.RED);
		label.setFont(new Font("Times new roman", 2, 13));
		add(label, BorderLayout.SOUTH);
	}

	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panel1 = new JPanel(new FlowLayout());
		JButton bgif = new JButton(new ImageIcon("images/gif.png"));
		JButton button = new JButton(new ImageIcon("images/cam.png"));
		JButton button2 = new JButton(new ImageIcon("images/folder_32.png"));
		JButton button1 = new JButton(new ImageIcon("images/save_32.png"));
		JButton button9 = new JButton(new ImageIcon("images/saveall.png"));
		JButton button10 = new JButton(new ImageIcon("images/import.png"));
		JButton bicon = new JButton(new ImageIcon("images/picture.png"));
		JButton[] m1 = { bgif, button, button2, button1, button9, button10,
				bicon };
		for (JButton e : m1) {
			e.addActionListener(this);
			panel1.add(e);
		}
		bgif.setActionCommand("gif");
		button.setActionCommand("cam");
		button2.setActionCommand("folder");
		button1.setActionCommand("save");
		button9.setActionCommand("saveall");
		button10.setActionCommand("import");
		bicon.setActionCommand("picture");

		JPanel panel2 = new JPanel();

		JButton undo = new JButton(new ImageIcon("images/undo.png"));
		undo.setActionCommand("undo");
		undo.addActionListener(this);
		JButton restore = new JButton(new ImageIcon("images/restore.png"));
		restore.setActionCommand("restore");
		restore.addActionListener(this);
		rb1 = new JRadioButton("Nét đứt", false);
		rb2 = new JRadioButton("Nét liền", true);
		rb3 = new JRadioButton("Vẻ hình", false);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);

		// panel2.add(button10);
		JScrollPane a = new JScrollPane();
		a.setViewportView(panel2);
		panel2.add(undo);
		panel2.add(restore);
		panel2.add(rb1);
		panel2.add(rb2);
		panel2.add(rb3);
		panel2.add(new Label("Do rong"));
		panel2.add(jSlider4);
		panel2.add(new Label("Do dam"));
		panel2.add(jSlider5);

		tabbedPane.addTab("Home", null, panel1);
		tabbedPane.addTab("Option", null, a);
		return tabbedPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getModel();
		controller.setStatus(status + "");
		if (e.getActionCommand().equals("Xoay90Right")) {
			controller.optionMagic("90Right/");
		}
		if (e.getActionCommand().equals("Xoay90Left")) {
			controller.optionMagic("90Left/");
		}
		if (e.getActionCommand().equals("LatAnh")) {
			controller.optionMagic("latAnh/latdoc");
		}
		if (e.getActionCommand().equals("LatAnh2")) {
			controller.optionMagic("latAnh/latngang");
		}
		if (e.getActionCommand().equals("Xoay180")) {
			controller.optionMagic("180/");
		}
		if (e.getActionCommand().equals("Text")) {
			controller.createTool("Text", "Tool");
			this.setEnabled(false);
		}
		if (e.getActionCommand().equals("Theme Tab")) {
			// controller.setlist(controller.getComponent());
			this.remove(controller.getComponent());
			controller.createTool("Tab", "theme");
			add(controller.getComponent(), BorderLayout.CENTER);
			repaint();
			validate();
		}
		if (e.getActionCommand().equals("Theme Desktop")) {
			this.remove(controller.getComponent());
			controller.createTool("Desktop", "theme");
			add(controller.getComponent(), BorderLayout.CENTER);
			repaint();
			validate();
		}
		if (e.getActionCommand().equals("lammo")) {
			controller.optionMagic("lamMo/");

		}
		if (e.getActionCommand().equals("black")) {
			controller.optionMagic("denTrang/");
		}
		if (e.getActionCommand().equals("group")) {
			controller.optionMagic("group/");
		}

		if (e.getActionCommand().equals("zoom")) {
			zoom = true;
			controller.zoom(0.5 + "");
		} else {
			zoom = false;
		}
		if (e.getActionCommand().equals("Resize")) {
			controller.createTool("resize", "Tool");
			repaint();
		}
		if (e.getActionCommand().equals("gif")) {
			controller.createTool("GIF", "Tool");
		}
		if (e.getActionCommand().equals("picture")) {
			controller.createTool("picture", "Tool");
			status = 6;
			this.setEnabled(false);
		}
		if (e.getActionCommand().equals("folder")) {
			controller.optionModel("Openfile");
			this.pack();
		}
		if (e.getActionCommand().equals("save")) {
			controller.optionModel("actionSave");
		}
		if (e.getActionCommand().equals("cam")) {
			controller.createTool("Chup", "Tool");
			setVisible(false);
		}
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("Open")) {
			// controller.setGiaoDien(this);
			controller.optionModel("Openfile");

			pack();
		}
		if (e.getActionCommand().equals("Save Image")) {
			controller.optionModel("actionSave");
		}
		if (e.getActionCommand().equals("New")) {
			controller.addNewTab();
			// repaint();
		}
		if (e.getActionCommand().equals("saveall")
				|| e.getActionCommand().equals("SaveAll")) {
			controller.optionModel("actionSaveALL");
		}
		if (e.getActionCommand().equals("import")) {
			controller.optionModel("actionOpen");
		}
		if (e.getActionCommand().equals("restore")) {
			controller.optionModel("restore");

		}
		if (e.getActionCommand().equals("undo")) {
			controller.optionModel("undo");
		}
		if (e.getActionCommand().equals("move")) {
			controller.setBoolean("move", true);
		}
		if (e.getActionCommand().equals("1")) {
			controller.setStatus("1");
			status = 1;
		}
		if (e.getActionCommand().equals("2")) {
			controller.setStatus("2");
			status = 2;
		}
		if (e.getActionCommand().equals("3")) {
			controller.setStatus("3");
			status = 3;
		}
		if (e.getActionCommand().equals("4")) {
			controller.setStatus("4");
			status = 4;
		}
		if (e.getActionCommand().equals("5")) {
			controller.setStatus("5");
			status = 5;
		}
		if (e.getActionCommand().equals("7")) {
			controller.setStatus("7");
			status = 7;
		}
		if (e.getActionCommand().equals("ceo")) {
			controller.setBoolean("domau", true);
			controller.setBoolean("move", true);
		} else {
			controller.setBoolean("domau", false);
		}

		if (rb1.isSelected()) {
			dashed = true;
			filled = false;
			controller.setBoolean("dashed", dashed);
			controller.setBoolean("filled", filled);
		} else {
			dashed = false;
			filled = false;
			controller.setBoolean("dashed", dashed);
			controller.setBoolean("filled", filled);
		}
		if (rb3.isSelected()) {
			controller.setBoolean("filled", rb3.isSelected());
		}
	}

	JRadioButton rb1, rb2, rb3;
	private ButtonGroup g = new ButtonGroup();
	private JSlider jSlider4;
	private JSlider jSlider5;

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		controller.getModel();
		if (e.getSource() == jSlider5) {
			trongsuot = jSlider5.getValue();
			controller.setTrongSuot(trongsuot);

			trongsuot = (int) (255 * (trongsuot / 100.0));
		}
		if (e.getSource() == jSlider4) {
			int thickness = jSlider4.getValue();
			controller.setThickness(thickness);
		}
		if (e.getSource() == jSlider3) {
			int thickness = jSlider3.getValue() * 3;
			controller.setThickness(thickness);
			if (jSlider3.getValue() >= 2) {
				s.setX(jSlider3.getValue() * 5);
			}
			if (jSlider3.getValue() >= 5 && zoom) {
				// this.setZ(jSlider3.getValue() / 2);
				controller.zoom(jSlider3.getValue() / 2 + "");
			}
			if (jSlider3.getValue() < 5 && jSlider3.getValue() > 0 && zoom) {
				int s = jSlider3.getValue();
				// this.setZ(;
				controller.zoom((double) s / 5 + "");
			} else {

			}
			repaint();
		}

	}

	public Color colo() {
		Color color = JColorChooser.showDialog(this, "Color Selector",
				Color.WHITE);
		return color;
	}

	MouseAdapter mouse = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			controller.getModel();
			if (e.getSource() == blLabel2) {
				gradient = false;
				controller.setBoolean("gradient", gradient);
				Color color = colo();
				controller.setColor(color);
				m2.setColor(color, color);
				m2.repaint();
			}
			if (e.getSource() == blLabel) {
				gradient = true;
				controller.setBoolean("gradient", gradient);
				Color col1 = colo();
				Color col2 = colo();
				controller.setCol(col1, col2);
				m1.setColor(col1, col2);
				m1.repaint();
			}

		}
	};

}