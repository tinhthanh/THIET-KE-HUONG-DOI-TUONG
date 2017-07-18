package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CustomTab extends JPanel {
	MyTabbedPane customJTabbedPane;

	public CustomTab(MyTabbedPane customJTabbedPane) {
		this.customJTabbedPane = customJTabbedPane;
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setBorder(new EmptyBorder(5, 2, 2, 2));
		setOpaque(false);
		addLabel();
		add(nutX());
	}

	private void addLabel() {
		JLabel label = new JLabel() {
			public String getText() {
				int index = customJTabbedPane.tabbedPane
						.indexOfTabComponent(CustomTab.this);
				if (index != -1) {
					return customJTabbedPane.tabbedPane.getTitleAt(index);
				}
				return null;
			}
		};
		label.setBorder(new EmptyBorder(0, 0, 0, 10));
		add(label);
	}

	public JButton nutX() {
		JButton x = new JButton("x");
		int size = 15;
		x.setPreferredSize(new Dimension(size, size));
		x.setToolTipText("close the Tab");
		x.setContentAreaFilled(false);
		x.setBorder(new EtchedBorder());
		x.setBorderPainted(false);
		x.setFocusable(false);
		x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				x.setBorderPainted(false);
				x.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				x.setBorderPainted(true);
				x.setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int index = customJTabbedPane.tabbedPane
						.indexOfTabComponent(CustomTab.this);
				if (index != -1) {
					customJTabbedPane.removeTab(index);
					System.out.println("vi tri " + index);
				}
			}
		});
		return x;
	}
}
