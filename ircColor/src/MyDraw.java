import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;







import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MyDraw extends JPanel implements ActionListener{
	private int WIDTH;
	private int HEIGHT;
	private BufferedImage image;
	private Timer refresh = new Timer(250, this);

	
	public void actionPerformed(ActionEvent e){
		this.getParent().repaint();
	}
	
	public MyDraw(int x, int y){
		WIDTH = x;
		HEIGHT = y;
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();
		refresh.start();
	}
	
	
	public Graphics2D getGraphics(){
		return this.image.createGraphics();
	}
	
	public void imgOut(String Path){
		try {
			ImageIO.write(image,"png",new File(Path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
	}

}

