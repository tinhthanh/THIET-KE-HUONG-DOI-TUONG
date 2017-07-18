package toolview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.IControllerPanit;

public class TextAdd extends JDialog implements InterfaceTool {
	private JLabel lb1, lb2, lb3, lb4;
	JComboBox<String> cbFontNames =new JComboBox<>();
	JComboBox<String> cbFontSizes =new JComboBox<String>();
	JComboBox<String> cbFont = new JComboBox<String>();
	JTextArea txtText = new JTextArea(1, 30);
	int count = 0 ;
	String list[] = new String[4];
	int[] fontStyles = { Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD,
			Font.ITALIC };

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

	public TextAdd(IControllerPanit iControllerPanit) {
		// TODO Auto-generated constructor stub
		setTitle("Add text to the image");
		setPreferredSize(new Dimension(400, 150));

		JButton btOK = new JButton("OK");
		btOK.setBackground(Color.BLACK);
		btOK.setForeground(Color.BLUE);
		btOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(count > 0){
				iControllerPanit.setStatus("8");
				iControllerPanit.setText(list);
				iControllerPanit.viewPaint().setEnabled(true);
				iControllerPanit.viewPaint().setVisible(true);
				setVisible();
				}else{
					JOptionPane.showConfirmDialog(new JFrame(), " Erro : Ban chua add Text");
				}

			}
		});

		JButton btSetColor = new JButton("Add Text");
		btSetColor.setBackground(Color.BLACK);
		btSetColor.setForeground(Color.BLUE);
		btSetColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				count++;
				String name = (String) cbFontNames.getSelectedItem();
				String f = (String) cbFontSizes.getSelectedItem();
				lb1.setText("Tex t: " + txtText.getText());
				lb2.setText("Font Name:" + cbFontNames.getSelectedItem());
				lb3.setText("Font Size:" + cbFontSizes.getSelectedItem());
				lb4.setText("From : " + cbFont.getSelectedItem());
				list[0] = txtText.getText();
				list[1] = name;
				list[2] = f;
				list[3] = cbFont.getSelectedIndex() + "";

				System.out.println();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		panel.add(lb1 = new JLabel("Text:"));
		panel.add(txtText);
		panel.add(lb2 = new JLabel("Font Name:"));
		panel.add(cbFontNames);
		panel.add(lb3 = new JLabel("Font Size:"));
		panel.add(cbFontSizes);
		panel.add(lb4 = new JLabel("Font : "));
		panel.add(cbFont);
		panel.add(btSetColor);
		panel.add(btOK);
		panel.setBackground(Color.GRAY);
		add(panel, BorderLayout.CENTER);
		setLocation(new Point(100, 200));
		setVisible(true);
		pack();
		listFonts();
	}

	public void listFonts() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		String fontStylesName[] = { "PLAIN", "BOLD", "ITALIC", "BOLDITALIC",
				"BOLD", "ITALIC" };
		for (int i = 0; i < fontStylesName.length; i++) {
			cbFont.addItem(fontStylesName[i]);
		}
		for (String f : fonts)
			cbFontNames.addItem(f);
		for (int i = 8; i < 50; i++)
			cbFontSizes.addItem(i + "");

	}

}
