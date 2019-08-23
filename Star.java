import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.lang.Math;
class Star extends LivingThing
{
	int numTips;
	int innerRadius;
	int xPos;
	int yPos;
	Random rand = new Random();

	double currAngluarStatus;
	Polygon	star = new Polygon();
	int xAcceleration;
	int yAcceleration;

public Star(controlPanel controlpanel)
{
	this.controlpanel = controlpanel;
	currXPosition = 30+ rand.nextInt(200);
    currYPosition = 30+ rand.nextInt(200);

	listenerList = new Vector<MortalityListener>();
	xSpeed = 0;
    ySpeed = 0;



	previousTime = System.currentTimeMillis();
	lifeTime = previousTime + 10000;
}
@Override
public void draw(Graphics g1)
{
	Graphics2D g;
	g =(Graphics2D)g1;

	double point;
	int xPos,yPos;

	switch(randoColor){
		case 1: g.setColor(Color.YELLOW);
			break;
		case 2: g.setColor(Color.RED);
			break;
		case 3: g.setColor(Color.GREEN);
			break;
		case 4: g.setColor(Color.ORANGE);
			break;
		case 5: g.setColor(Color.WHITE);
			break;
		default: g.setColor(Color.BLUE);
			break;
	}

	for(int i=0; i <= numTips; i++)
	{
		double currAngluarStatus = i + (angle/numTips) + 90;


		point = ( 2* Math.PI / numTips) * currAngluarStatus;
		xPos = (int)(Math.cos(point) * radius + currXPosition);
		yPos = (int)(Math.sin(point) * radius + currYPosition);
		star.addPoint(xPos,yPos);

		point = (2* Math.PI / numTips) * (currAngluarStatus + .5);
		xPos = (int)(Math.cos(point) * (radius *.50) + currXPosition);
		yPos = (int)(Math.sin(point) * (radius *.50) + currYPosition);
		star.addPoint(xPos,yPos);
}
	g.fill(star);
	g.draw(star);
	star.reset();



}
public void getRandom(int height,int width)
{
	numTips = rand.nextInt(5)+6;
	radius = rand.nextInt(20)+ 50;
	angle =1 + rand.nextInt(8);
	angularVelocity = 1 + rand.nextInt(8);
	randoColor = 1 + rand.nextInt(4);
	ySpeed = 1 + rand.nextInt(9);
	xSpeed = 1 + rand.nextInt(9);
	xAcceleration = 1 + rand.nextInt(3);
	yAcceleration = 1 + rand.nextInt(3);
}
@Override
public void updateVitality()
{
	lifeTime += (controlpanel.speedDeath.getValue())* 100;
	if(currTime >= lifeTime)
	{
		e = new MortalityEvent(this,"DEATH");
		//made it here, checkd with cout
		for(int i = 0;i < listenerList.size();i++)
			listenerList.elementAt(i).mortalityChanged(e);
	}
}
}