import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;



public class drawBox {
	//class constants.
	public final int  WIDTH = 500;
	public final int HEIGHT = 500;
	private int sqrS= (int)Math.sqrt(WIDTH*HEIGHT/100);
	private Color[] colArray = new Color[100];
	private int iter = 0;
	private JFrame frame = new JFrame("ircColor");
	private MyDraw D = new MyDraw(WIDTH,HEIGHT);
		/*addColor("255000000");
		addColor("000255000");
		addColor("000000255");
		buildFrame();
		drawSq();*/
		
	public drawBox(){
		buildFrame();
	}
	
	//Creates a jframe and embeds MyDraw  into it.
	private void buildFrame(){
		frame.add(D);
		frame.addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(WindowEvent winEv) {
				System.exit(0);
			}
		});
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	
	//draws squares itterating over array.
	private void drawSq(){
		Graphics2D g = D.getGraphics();
		if(iter == 0){
			g.setColor(colArray[iter]);
			g.fillRect(((iter)%10) * sqrS, ((iter)/10)*sqrS,sqrS,sqrS);
		} else {
			g.setColor(colArray[iter-1]);
			g.fillRect((iter-1)%10 *sqrS, (iter-1)/10 *sqrS, sqrS,sqrS);
		}
		g.dispose();
		//frame.repaint();
		D.imgOut("G:/Documents/testIRCout.png");
	}
	
	
	public String getNumCol(){
		int total = 0;
		for(int i= 0; i<100; i++){
			if(colArray[i]!= null){
				total++;
				System.out.println(colArray[i].toString());
			}
		}
		return "total colors: " + total;
	}
	
	//adds values must be a semi properly formatted hex or decimal value.
	//form #rrggbb (hex 0-f)
	// rrrgggbbb (decimal 0-9)
	// must contain # if hex. if lacking any full or partial pair/triplet it'll be ignored.
	public void addColor(String str){
		if(str != null){
			if(iter >= 100){
				iter = 0;
			}
			int r =0;
			int g = 0;
			int b = 0;
			if(str.startsWith("#")){
				if(str.length()>2){
					r =Integer.parseInt(str.substring(1, 3),16);
				}
				if(str.length()>4){
					g =Integer.parseInt(str.substring(3, 5),16);
				}
				if(str.length()>6){
					b =Integer.parseInt(str.substring(5, 7),16);
				}
				colArray[iter]= new Color(r,g,b);
			} else {
				if(str.length()>2){
					 r= Integer.parseInt(str.substring(0,3),10);
				}
				if(str.length()>5){
					g = Integer.parseInt(str.substring(3,6),10);
				}
				if(str.length()>8){
					b = Integer.parseInt(str.substring(6,9));
				}
				colArray[iter] = new Color(r,g,b);
			}
			iter++;
		}
		drawSq();
	}
}
