package toolview;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import controller.IControllerPanit;

public class Shopstickers extends JDialog implements ActionListener ,InterfaceTool{
	String path = null ;
	JButton  ok;
	JTabbedPane t = new JTabbedPane();
	private String  icon  = "Do an1/";
	private String  duongdan2  = "Do an/";
	public Shopstickers(IControllerPanit iControllerPanit) {
		setLayout(new BorderLayout());
		setSize(500, 500);
		setTitle("Mypicture");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JLabel l = new JLabel("Bang chon icon");
		l.setFont(new Font("Tahoma", Font.BOLD, 15));
		l.setForeground(Color.RED);
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(l);
		add(p1, BorderLayout.NORTH);
		autoaddTab();
		add(t, BorderLayout.CENTER);
		JPanel pok = new JPanel(new FlowLayout());
		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == ok) {
					 iControllerPanit.setStatus("6");	
					 iControllerPanit.setPathImage(duongdan2+path);
						iControllerPanit.viewPaint().setEnabled(true);
						iControllerPanit.viewPaint().setVisible(true);
						setVisible();
				
				}
			}
		});
		pok.add(ok);
		add(pok, BorderLayout.SOUTH);
		Image image = new ImageIcon("icon/icon1.png").getImage();
		setIconImage(image);
		setVisible(true);
		this.requestFocus();
	}
	public void autoaddTab(){
		File file = new File(icon);
		if(file!=null){
			String st[] = file.list();
			int count  = 0 ;
			for(int i = 0 ; i< st.length ; i++){
				if(i%12==0&&i!=0){
					t.addTab("Picture "+i, image( st,i-12, i));
					count = i ;
				}
			}
			t.addTab( "Picture" +count ,image(st,count,st.length));
		}
	}
	public JScrollPane image(String list[], int src ,int dest  ) {
		JButton button[];
		JScrollPane pane = new JScrollPane();
		JPanel l1 = new JPanel(new GridLayout(0, 3));
		button = new JButton[dest-src];
		JButton b ;
		for (int i = src; i < dest; i++) {
			b = new JButton();
			b.setIcon(new ImageIcon(icon+list[i]));
			b.addActionListener(this);
			b.setActionCommand(list[i]);
			l1.add(b);
		}
		pane.setViewportView(l1);
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		path = e.getActionCommand() ;
//		System.out.println(icon+path);
	}
	@Override
	public Component getTool() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public void setVisible() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
