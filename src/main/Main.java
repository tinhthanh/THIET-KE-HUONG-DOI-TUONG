package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.Mypanel;
import view.ViewPaint;
import controller.IControllerPanit;
public class Main {
	static SplashProgress progress;
	public Main() {
		IControllerPanit iControllerPanit = new IControllerPanit();
		ViewPaint a = new ViewPaint(iControllerPanit);
		a.setVisible(true);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setLayout(new BorderLayout());
		window.setUndecorated(true);
		window.setBackground(new Color(0, 0, 0, 0));
		Mypanel mypanel = new Mypanel();
		Image image = new ImageIcon("images/gd.png").getImage();
		window.setIconImage(image);
		progress = new SplashProgress();
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		jp.add(progress.getBar());
		mypanel.setOpaque(false);
		ImageIcon icon = new ImageIcon("hello.png");
		mypanel.add(new JLabel(icon), BorderLayout.CENTER);
		window.add(mypanel, BorderLayout.CENTER);
		window.add(jp, BorderLayout.SOUTH);
		window.setBounds(600, 150, 500, 430);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		try {
			Thread.sleep(2222);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setVisible(false);
		window.dispose();
		try {
		       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		Main main = new Main();

	}
}