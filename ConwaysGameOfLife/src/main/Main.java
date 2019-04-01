package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener{
	private BufferedImage canvas;
	private Grid grid;
	Timer timer=new Timer(50, this);
	
	public Main(int height, int width) {
		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//fillCanvas(Color.BLUE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(canvas, null, null);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
	
	public void fillCanvas(Color c) {
		int color = c.getRGB();
		for(int i = 0; i < canvas.getWidth(); i++) {
			for(int j = 0; j < canvas.getHeight(); j++) {
				canvas.setRGB(i, j, color);
			}
		}
		repaint();
	}
	
    public void drawRect(Color c, int x1, int y1, int width, int height) {
        int color = c.getRGB();
        // Implement rectangle drawing
        for (int x = x1; x < x1 + width; x++) {
            for (int y = y1; y < y1 + height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

	public static void main(String[] args) {
		Main main = new Main(800, 800);
		JFrame frame = new JFrame("Conway's Cell Automata");
		
		frame.add(main);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main.start();
	}
	
	public void start() {
		
		grid = new Grid(200, 200);
		grid.populateGrid(5000);
		timer.start();
		
	}
	
	public void actionPerformed(ActionEvent ev){
		int empty = Color.BLACK.getRGB();
    	int full = Color.WHITE.getRGB();
    	int born = Color.RED.getRGB();
    	
	    if(ev.getSource()==timer){
				for(int x = 0; x < canvas.getWidth(); x++) {
					for(int y = 0; y < canvas.getHeight(); y++) {
						if(grid.getGrid()[x/4][y/4] == 1) {
							canvas.setRGB(x, y, full);
						} else if(grid.getGrid()[x/4][y/4] == 2) {
							canvas.setRGB(x, y, born);
						} else {
							canvas.setRGB(x,  y, empty);
						}
					}
				}
				//grid.simulate();
				grid.simulateSphere();
				repaint();
	    }

	}

}
