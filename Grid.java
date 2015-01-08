/**
	Purpose: The Graphics for the Grid Interface
**/
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
public class Grid
{
	public Grid(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		// ---------------------
		// Creating the Grid
		// ---------------------
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				//Stroke oldStroke = g2.getStroke();
				//g2.setColor(new Color(0x3A, 0x45, 0x52));
				//g2.setStroke(new BasicStroke(1));
				//g2.drawRect(560+(80*i), 0+(80*j), 80, 80);
				//g2.setStroke(oldStroke);
				
				if(j % 2 == 0)
				{
					if(i % 2 == 0)
					{
						g2.setColor(new Color(0x5A, 0x6B, 0x7E));
					}
					else if(i % 2 == 1)
					{
						g2.setColor(new Color(0x62, 0x75, 0x8A));
					}
				}
				else if(j % 2 == 1)
				{
					if(i % 2 == 1)
					{
						g2.setColor(new Color(0x5A, 0x6B, 0x7E));
					}
					else if(i % 2 == 0)
					{
						g2.setColor(new Color(0x62, 0x75, 0x8A));
					}
				}
				g2.fillRect(535+(80*i), 20+(80*j), 80, 80);
			}
		}
		
		// --------------------------
		// Creating the Command Block
		// --------------------------
		
		g2.setColor(new Color(0x44, 0x53, 0x60));
		g2.fillRect(535, 675, 77, 80);
		
		g2.setColor(new Color(0x37, 0x42, 0x4D));
		g2.fillRect(617, 675, 315, 80);
		
		g2.setColor(new Color(0x44, 0x53, 0x60));
		g2.fillRect(937, 675, 77, 80);
		g2.fillRect(1019, 675, 77, 80);
		
		// ----------------------------------
		// Creating Backgrounds for Draggables
		// ----------------------------------
		
		g2.setColor(new Color(0x4A, 0x59, 0x68));
		g2.fillRect(15, 12, 77, 80);
		g2.fillRect(98, 12, 77, 80);
		g2.fillRect(181, 12, 77, 80);
		
		g2.fillRect(15, 97, 315, 80);
		g2.fillRect(15, 182, 315, 80);
		g2.fillRect(15, 267, 315, 80);
		g2.fillRect(15, 352, 315, 80);
		
		g2.fillRect(15, 438, 77, 80);
		g2.fillRect(98, 438, 77, 80);
		g2.fillRect(181, 438, 77, 80);
		g2.fillRect(264, 438, 77, 80);
		
		g2.fillRect(15, 523, 77, 80);
		g2.fillRect(98, 523, 77, 80);
		g2.fillRect(181, 523, 77, 80);
		g2.fillRect(264, 523, 77, 80);
		
		g2.fillRect(15, 609, 77, 80);
		g2.fillRect(98, 609, 77, 80);
		g2.fillRect(181, 609, 77, 80);
		g2.fillRect(264, 609, 77, 80);
		
		g2.fillRect(15, 695, 77, 80);
		g2.fillRect(98, 695, 77, 80);
	}
}
