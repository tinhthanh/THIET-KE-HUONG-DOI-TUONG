package view;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
public class Mypanel extends JPanel {
	Image backgound;
	   public Mypanel() {
		// TODO Auto-generated constructor stub		   
	}
	public Mypanel(FlowLayout fl){
		   this.setLayout(fl);
	}   
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		if (backgound != null) {
			g.drawImage(backgound, 0, 0, null);
		}
}
	public void setBackGound(Image img) {
		backgound = img;
		repaint();
	}
}