package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;

public class SplashProgress {
	private Thread thared;
	private JProgressBar bar;

	public SplashProgress() {
		thared = new Thread(run());
		bar = new JProgressBar();
		bar.setPreferredSize(new Dimension(300, 11));
		bar.setMaximum(100);
		bar.setMinimum(0);
		bar.setStringPainted(true);
		bar.setBackground(Color.gray);
		bar.setForeground(Color.green);
		thared.start();
	}

	public Runnable run() {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				for (int val = 0; val <= 500; val++) {
					bar.setValue(val);
					try {
						thared.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		return run;
	}

	public JProgressBar getBar() {
		return bar;
	}

	public void setBar(JProgressBar bar) {
		this.bar = bar;
	}

}
