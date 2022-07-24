package breaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Play extends JPanel implements KeyListener,ActionListener,Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Thread t;
	Rectangle myRec,slider;
	private int data[] = {0, 32, 8, 200, 250,490,-1,-2};
	private int values[];
	private ObjAction map;
	private boolean start=false;
	private Timer timer;

	public Play()
	{
		t = new Thread(this);
		t.start();
		
	}
	
	
	public void run() {
		try {
		 values=new int[8];
		  for (int i = 0; i < data.length; i++)
		        values[i]=data[i];

			map=new ObjAction(4,8);
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			timer=new Timer(values[2],this);
			timer.start();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(1, 1, 535,592);
		
		map.draw((Graphics2D) g);
		g.setColor(Color.red);
		g.fillRect(0, 0, 3, 560);
		g.fillRect(0, 0, 540, 3);
		g.fillRect(535, 0, 3, 592);
		
		g.setColor(Color.black);
		g.fillRect(values[3], 548, 120,6);
		

		g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.ITALIC,23));
		g.drawString("Score : " + values[0], 400, 30);
		
		BufferedImage img = null;
		
	  try {
			 img = ImageIO.read(this.getClass().getResource("images/ball.png"));
			   g.drawImage(img, values[4],values[5],30,20, this);
			   
		  }
	  catch(Exception e)
		   {
			   e.printStackTrace();
			   g.setColor(Color.red);
		   }
	  
	  
		
		if( values[1]<0)
		{
			start=false;
			values[6]=0;
			values[7]=0;
			g.setColor(Color.green);
			g.setFont(new Font("serif",Font.ITALIC,23));
			g.drawString("You won Score : " + values[0], 190, 300);
			
			g.setFont(new Font("serif",Font.ITALIC,23));
			g.drawString("Press Enter to restart.. " , 230, 350);
			
		}
		//if loses
				if(values[5]>560)
				{
					start=false;
					values[6]=0;
					values[7]=0;
					g.setColor(Color.red);
					g.setFont(new Font("serif",Font.ITALIC,23));
					g.drawString("Score : " +  values[0], 150, 300);
					
					g.setFont(new Font("serif",Font.ITALIC,23));
					g.drawString("Game Over Press Enter to restart " , 120, 350);
					
				}
		     
		    g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		timer.start();
		
		if(start)
		{
			if(new Rectangle(values[4],values[5],20,30).intersects(new Rectangle(values[3],500,100,8)))
			{
				values[7]=-values[7];
				
			}
			
			

			for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0)
					{	
						myRec=new Rectangle( j*map.dimension[0],i*map.dimension[1]+50,map.dimension[0],map.dimension[1]);
						slider= new Rectangle(values[4],values[5],20,20);
						
						
						if(slider.intersects(myRec)) 
						{
							//removes the brick
							map.updateGame(0, i, j);
							int a= values[1];
							a--;
							 values[1]=a;
							 values[0]+=5;
							
							if(values[4]+19<=myRec.x|| values[4]+1 >=myRec.x+myRec.width)
									values[6]=-values[6];
				
							else
								values[7]=-values[7];
							
						}
					}
					
				}
				
			}
			values[4]+=values[6];
			values[5]+=values[7];
//if ball outside of frame
				if(values[4]<0)
				{
					values[6]=-values[6];
				}
				
//if ball outside of frame
				if(values[5]<0)
				{
					values[7]=-values[7];
				}
//if ball outside of frame
				if(values[4]>534)
				{
					values[6]=-values[6];
				}
				
			}
			
		
		
		repaint();
		
		}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(values[3]>=500)
			{
				values[3]=350;
			}
			
			else
			{
				shiftRight();
			}
			
		}
		
		
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(values[3]<=10)
			{
				values[3]=10;
			}
			
			else
			{
				shiftLeft();
			}
			
		}
		
		
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!start)
			{
				start=true;
				values[4]=0;
				values[5]=350;
				values[6]=-1;
				values[7]=-2;
				 values[0]=0;
				 values[1]=28;
				map=new ObjAction(4,8);
				repaint();
			}
			
		}
		
		
	}

	private void shiftLeft() {
		// TODO Auto-generated method stub
		start=true;
		values[3]-=20;
		
	}

	private void shiftRight() {
		// TODO Auto-generated method stub
		start=true;
		values[3]+=20;
		
	}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		}
	


}
