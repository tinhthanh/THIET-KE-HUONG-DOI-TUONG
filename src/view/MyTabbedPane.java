package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyTabbedPane implements Interfaceview {
	static int indexTab = 0;
	JTabbedPane tabbedPane;
	int numTabs;
	ArrayList<MyJpanel> listPanel = new ArrayList<MyJpanel>();      											// JTabbedPan
	public MyTabbedPane() {
		createJTabbedPane();
	}
	public JTabbedPane getJTabbendPnane() {
		return this.tabbedPane;
	}
	public static int getIndexTab() {
		return indexTab;
	}
	public MyJpanel Chup() {
		MyJpanel a = listPanel.get(indexTab);
		return a.sendData().CropImg();	
	}
	public ArrayList<MyJpanel> getListPanel() {
		return listPanel;
	}
	public MyJpanel getTab() {
		return listPanel.get(getIndexTab());
	}
	public void createJTabbedPane() {
		JScrollPane s = new JScrollPane();
		s.getVerticalScrollBar().setUnitIncrement(20);
		JPanel a = createJPanel();
		s.setViewportView(a);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.add(s, "Hinh " + String.valueOf(numTabs), numTabs++);
		tabbedPane.setTabComponentAt(0, new CustomTab(this));
		JPanel n = new JPanel();
		n.setBackground(Color.WHITE);
		tabbedPane.add(n, "+", numTabs++);
		tabbedPane.addChangeListener(changeListener);
	}


	public JPanel createJPanel() {
		MyJpanel panel = new MyJpanel();
		panel.setBackground(Color.WHITE);
		listPanel.add(panel);
		return panel;
	}

	ChangeListener changeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			addNewTab();
			JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
			int index = sourceTabbedPane.getSelectedIndex();
			indexTab = index;
		}
	};

	public void addNewTab() { //Them tab bang nut "x"
		int index = numTabs - 1;
		if (tabbedPane.getSelectedIndex() == index) { 
			tabbedPane.add(new JScrollPane(createJPanel()), "Hinh " + String.valueOf(index), index);
			tabbedPane.setTabComponentAt(index, new CustomTab(this));
			tabbedPane.removeChangeListener(changeListener);
			tabbedPane.setSelectedIndex(index);
			tabbedPane.addChangeListener(changeListener);
			numTabs++;
		}
	}
     @Override
	public void addNewTab1() { //Nut tab bang nut "New"
		int index = listPanel.size();
		tabbedPane.add(createJPanel(), "Hinh " + String.valueOf(index), index);
		tabbedPane.setTabComponentAt(index, new CustomTab(this));
		tabbedPane.removeChangeListener(changeListener);
		tabbedPane.setSelectedIndex(index);
		tabbedPane.addChangeListener(changeListener);
		numTabs++;
	}
	public MyJpanel getIndex(int index) {
		return listPanel.get(index);
	}
	public void removeTab(int index) {
		listPanel.get(indexTab).sendData().actionSave();
		tabbedPane.remove(index);
		listPanel.remove(index);
		numTabs--;
		if (index == numTabs - 1 && index > 0) {
			tabbedPane.setSelectedIndex(numTabs - 2);
		} else {
			tabbedPane.setSelectedIndex(index);
		}
		if (numTabs == 1) {
			addNewTab();
		}
	}
}
