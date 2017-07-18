package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
public class Desktop extends JDesktopPane implements Interfaceview {
	int nframes = 0;
	HashMap<String, MyJpanel> hashMap = new HashMap<>();
	public Desktop() {
		addNewTab1();
		this.setBackground(Color.WHITE);
	}
	@Override
	public void addNewTab1() { // Giao dien tab k dc xoa
		MyJpanel myJpanel = new MyJpanel();
		createInternalFrame(myJpanel);
	}
	@Override
	public JDesktopPane getJTabbendPnane() {
		return this;
	}
	@Override
	public MyJpanel Chup() {
		MyJpanel a = getTab();
		return a.sendData().CropImg();
	}
	@Override
	public MyJpanel getTab() {	
		if(this.getAllFrames().length ==0){
	      nframes = 0 ;		
		  this.addNewTab1();
		  JInternalFrame[] iframes = this.getAllFrames();
		  return hashMap.get(iframes[0].getTitle());
		}else{
	    JInternalFrame[] iframes = this.getAllFrames();
		return hashMap.get(iframes[0].getTitle());
	}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon ic = new ImageIcon("A/it400X400.png");
		ImageIcon avt = new ImageIcon("A/avt.png");
		g.drawImage(new ImageIcon("A/bg.png").getImage(), 0, 0, null);
		g.drawImage(ic.getImage(), this.getWidth() - ic.getIconWidth(), 0, null);
		g.drawImage(avt.getImage(), this.getWidth() - avt.getIconWidth(), this.getHeight() - avt.getIconHeight(), null);
	}
	public void createInternalFrame(MyJpanel myJpanel ) {
		nframes++;
		String title = "#" + nframes;
		JInternalFrame frame = new JInternalFrame(title, true,true,true,true); 
		frame.setVisible(true);
	    frame.setBounds(25, 25, 200, 100);
		frame.addVetoableChangeListener(new VetoableChangeListener() {
			@Override
			public void vetoableChange(PropertyChangeEvent pce) throws PropertyVetoException {
				if (pce.getPropertyName().equals("closed")) {
					boolean changed = ((Boolean) pce.getNewValue()).booleanValue();
					if (changed) {
						int option = JOptionPane.showOptionDialog(frame, "Close " + title + "?", "Close Confirmation",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (option != JOptionPane.YES_OPTION) {
							throw new PropertyVetoException("Cancelled", null);
						} else {
						}
					}
				}
			}
		});
		myJpanel.setBackground(Color.WHITE);
		JScrollPane a = new JScrollPane();
		a.setViewportView(myJpanel);
		frame.add(a, BorderLayout.CENTER);
		hashMap.put(title, myJpanel);
		this.add(frame);
		frame.setSize(500, 400);
		frame.setLocation(30 * nframes, 30 * nframes);
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
		}
	}
}
