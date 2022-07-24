package breaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class ObjAction {

	public  int map[][];
	public int dimension[];
	
	
	protected ObjAction(int x, int y)
	{
		int i;
		i=0;
		dimension=new int[2];
		map=new int[x][y];
		
		while(i<map.length)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=1;
				
			}
			i++;
		}
			
		dimension[0]=540/y;
		dimension[1]=150/x;
			
			
		}
	public void draw(Graphics2D g)
		{
		int i=0;
		
		
		while(i<map.length)
			{
				for(int j=0;j<map[0].length;j++)
				{
					if(map[i][j]>0)
					{
						if(i%2==0)
						{
						g.setColor(Color.yellow);
						g.fillRect(j*dimension[0] ,i*dimension[1] + 50, dimension[0], dimension[1]);
					
						}
						
						else
						{
							g.setColor(Color.red);
							g.fillRect(j*dimension[0],i*dimension[1] + 50,dimension[0],dimension[1]);
							
						}
							
						
						g.setStroke(new BasicStroke(3));
						g.setColor(Color.white);
						g.drawRect(j*dimension[0],i*dimension[1] + 50, dimension[0],dimension[1]);
					}
					
				}
				i++;
			}
		}
	
	public void updateGame(int myval, int x,int y)
	{
		map[x][y]=myval;

	}
			
}
