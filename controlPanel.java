import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.Timer;//timer
import java.awt.*;//action event
import java.awt.event.*;///action event
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class controlPanel extends JFrame implements ActionListener, MortalityListener
{
	Space space;
	Container cp;
	Star shape;
	Timer timer;
	Vector <Star>vectorOfLivingThings;
	JButton addShape;
	JButton addMultipleStars;
	JButton clearStars;
	JPanel buttonPanel;
	JCheckBox gravityBox;
	JCheckBox tracerBox;
	JSlider speedDeath;
	JCheckBox animatedDeath;
	JSlider animationSlider;
	JSlider gravitySlider;
	JSlider bouncinSlider;
	JLabel animationLabel;
	JLabel bouncyLabel;
	JPanel sliderPanel;
	JLabel gravityLabel;
	JLabel fastDeathLabel;
public controlPanel()
{
	space = new Space(this);

	timer = new Timer(25,new ActionListener(){
	@Override
	public void actionPerformed(ActionEvent e){
		for(int i=0;i<vectorOfLivingThings.size();i++){
			vectorOfLivingThings.elementAt(i).Update(space.getHeight(),space.getWidth());
		}
		repaint();
}
});

	vectorOfLivingThings = new Vector<Star>(5,1);
	timer.start();

	speedDeath = new JSlider(-10,0,0);
	animationSlider = new JSlider(1,10,1);
	gravitySlider = new JSlider(1,10,1);
	bouncinSlider = new JSlider(1,11,1);

	animationLabel = new JLabel("Animation");
	gravityLabel = new JLabel("Gravity");
	fastDeathLabel = new JLabel("Accelerated Death");
	bouncyLabel = new JLabel("Bounciness");
	buttonPanel = new JPanel(new GridLayout(1,7));
	sliderPanel = new JPanel(new GridLayout(1,4));
	gravityBox = new JCheckBox("Gravity?",false);
	tracerBox = new JCheckBox("Blur Effect",false);
	animatedDeath = new JCheckBox("Animated Death",false);


	clearStars = new JButton("Clear the stars");
	clearStars.addActionListener(this);
	clearStars.setActionCommand("clear");

	addShape = new JButton("Add New Star");
	addShape.addActionListener(this);
	addShape.setActionCommand("addShape");

	addMultipleStars = new JButton("Add multiple Stars");
	addMultipleStars.addActionListener(this);
	addMultipleStars.setActionCommand("mStars");

	sliderPanel.add(bouncinSlider,BorderLayout.SOUTH);
	sliderPanel.add(bouncyLabel,BorderLayout.SOUTH);
	sliderPanel.add(gravityLabel,BorderLayout.SOUTH);
	sliderPanel.add(gravitySlider,BorderLayout.SOUTH);
	sliderPanel.add(animationLabel,BorderLayout.SOUTH);
	sliderPanel.add(animationSlider,BorderLayout.SOUTH);
	buttonPanel.add(animatedDeath,BorderLayout.SOUTH);
	sliderPanel.add(fastDeathLabel,BorderLayout.SOUTH);
	sliderPanel.add(speedDeath,BorderLayout.SOUTH);
	buttonPanel.add(tracerBox,BorderLayout.SOUTH);
	buttonPanel.add(gravityBox,BorderLayout.SOUTH);
	buttonPanel.add(clearStars,BorderLayout.SOUTH);
	buttonPanel.add(addShape,BorderLayout.SOUTH);
	buttonPanel.add(addMultipleStars,BorderLayout.SOUTH);

	cp = getContentPane();
	cp.add(buttonPanel,BorderLayout.NORTH);
	cp.add(sliderPanel,BorderLayout.SOUTH);
	cp.add(space);
	setupMainFrame();
}
public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("addShape"))
	{
		Star shape = new Star(this);
		shape.getRandom(space.getHeight(),space.getWidth());
		shape.addMortalityListener(this);
		vectorOfLivingThings.addElement(shape);
	}
	else if(e.getActionCommand().equals("clear")){
			space.imGraphics.clearRect(0,0,space.getWidth(), space.getHeight());
			vectorOfLivingThings.clear();
			space.removeAll();
	}
	else if(e.getActionCommand().equals("mStars")){
		for(int i=0;i<5;i++){
			Star shape = new Star(this);
			shape.getRandom(space.getHeight(),space.getWidth());
			shape.addMortalityListener(this);
			vectorOfLivingThings.addElement(shape);
		}
	}
	else System.out.println("NADA");


}
void setupMainFrame()													//sets up panel
{
	Toolkit tk;
	Dimension d;
	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();
	setLocation(d.width/3, d.height/3);
	d.getWidth();
	d.getHeight();
	setSize(d.width/2,d.height/2);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("simple application");
	setVisible(true);
}	//end set up main frame
public void mortalityChanged(MortalityEvent e)
{

	if(e.eventType.equals("DEATH"))
	{

		vectorOfLivingThings.remove(e.source);

	}
}

}


