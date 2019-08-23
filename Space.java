import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
class Space extends JPanel
{

	BufferedImage      im;
    Graphics2D         imGraphics;
	controlPanel 	   jframe;
	Color 			   savedColor;
Space(controlPanel jframe)
{
	this.jframe = jframe;
}
@Override
public void paintComponent(Graphics g1)
{

		super.paintComponent(g1);

	if (imGraphics == null)
	{
	   im = new BufferedImage(1920, 1080,BufferedImage.TYPE_INT_RGB);
	   imGraphics = im.createGraphics();
    }

    if (!jframe.tracerBox.isSelected())
	   {
	    savedColor = imGraphics.getColor();
	    imGraphics.setColor(Color.BLACK); // background color
	    imGraphics.fillRect(0,0,im.getWidth(), im.getHeight());
	    imGraphics.setColor(savedColor);
       }

	for(int i =0; i < jframe.vectorOfLivingThings.size();i++)
		jframe.vectorOfLivingThings.elementAt(i).draw(imGraphics);		//change made

	g1.drawImage(im, 0,0,this);

}

}//ends