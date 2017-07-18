package toolview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.IControllerPanit;

public class Resize extends JDialog implements InterfaceTool {
	JTextField txtWidth, txtHeight;
	JButton btOKre;
	private int w, h;
	public Resize(IControllerPanit c) {
		setTitle("Resize");
		btOKre = new JButton("OK");
		btOKre.setBackground(Color.BLACK);
		btOKre.setForeground(Color.BLUE);
		txtWidth = new JTextField(4);
		txtWidth.addKeyListener(key());
		txtHeight = new JTextField(4);
		btOKre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == btOKre) {
					setW(Integer.parseInt(txtHeight.getText()));
					setH(Integer.parseInt(txtWidth.getText()));
					c.optionMagic("reSize/"+w+"-"+h);
					c.getMyJpanel().repaint();
					setVisible();

				}
			}
		});
		txtHeight.addKeyListener(key());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Width:"));
		panel.add(txtWidth);
		panel.add(new JLabel("Height:"));

		panel.add(txtHeight);
		panel.add(btOKre);
		panel.setBackground(Color.GRAY);
		add(panel, BorderLayout.CENTER);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);

	}
	public KeyAdapter key() {
		KeyAdapter key = new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				int intkey = (int) c;
				if (!(intkey >= 48 && intkey <= 57 || intkey == 8 || intkey == 127)) {
					ke.consume(); // hide the unwanted key
				}
			}
		};
		return key;
	}
	public void setW(int w) {
		this.w = w;
	}
	public void setH(int h) {
		this.h = h;
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

}
