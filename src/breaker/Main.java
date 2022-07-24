package breaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try
	     {
		    Thread.sleep(1000);
		 	JFrame frame=new JFrame();
			Play breaker=new Play();
			
			frame.setBounds(11,11,540,600);
			frame.setTitle("My Brick Breaker Game");
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.add(breaker);
	     }
	     catch (InterruptedException e) {
	     
	    	 e.printStackTrace();
	     }
	      
		

	}

}
