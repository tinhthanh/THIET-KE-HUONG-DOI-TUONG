package toolview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class JFrameTool extends JFrame implements InterfaceTool {
	private InterfaceTool interfaceTool;
	public JFrameTool(InterfaceTool interfaceTool) {
		setTitle("Moa");
		this.interfaceTool = interfaceTool;
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(interfaceTool.getTool());
		Rectangle bounds = getVirtualBounds();
		setLocation(bounds.getLocation());
		setSize(bounds.getSize());
		setVisible(true);
	}
	
	@Override
	public Component getTool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVisible() {
		// TODO Auto-generated method stub
		setVisible(false);

	}
	public static Rectangle getVirtualBounds() {
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice lstGDs[] = ge.getScreenDevices();
		for (GraphicsDevice gd : lstGDs) {
			bounds.add(gd.getDefaultConfiguration().getBounds());
		}
		return bounds;
	}

}
