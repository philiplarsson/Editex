package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class TextField extends JTextPane {

	private static final int HEIGHT = 800;
	private static final int WIDTH = 700;
	private int topBottomPadding;
	private int sidePadding;
	
	public TextField() {
		super();
		init();
		fillGiberish();

	}

	private void init() {
		Font font = new Font("Ubuntu", Font.PLAIN, 16);
//		Font font = new Font("Tahomaaoeua", Font.PLAIN, 15);
		setFont(font);
		setBackground(new Color(255, 255, 245));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		sidePadding = (int) (WIDTH * 0.2);
		topBottomPadding = (int) 60;
		this.setMargin(new Insets(topBottomPadding, sidePadding,
				topBottomPadding, sidePadding));
	}

	public void fillGiberish() {
		Random rng = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 100; i < 350; ++i) {
			sb.append(rng.nextInt(i) + " ");
		}
		setText(sb.toString());
	}
}
