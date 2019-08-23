import java.io.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;
import java.awt.Graphics;
import javax.swing.event.EventListenerList;
import java.util.Random;

abstract class LivingThing
{
	int 									currXPosition;
	int 									currYPosition;
	double									xSpeed;
	double 									ySpeed;
	double 									angle;
	double 									bouncing;
//------times----------------------------------------
	long 									previousTime;
	long 									deltaTime;
	long 									currTime;
	long 									lifeTime;
//-----times end------------------------------------
	int 									angularVelocity;
	double 									gravity = 1.5;
	Random 						     		rand;
	controlPanel 							controlpanel;
	double 									xAcceleration;
	double 									yAcceleration;
	int 									radius;
	MortalityEvent							e;
	Vector<MortalityListener>               listenerList;
	int 									randoColor;
	Random rand1 							= new Random();
public LivingThing()
{

}//end constructor

void Update(int panelHeight,int panelWidth)
{
   currTime = System.currentTimeMillis();
   deltaTime = ((currTime - previousTime)/20) * controlpanel.animationSlider.getValue();
   previousTime = currTime;

   bouncin();
   updateVitality();
   updateSpeed();
   updateLocation(panelHeight,panelWidth);
   angluarUpdate();

}
public void updateLocation(int panelHeight,int panelWidth)
{

   currXPosition =(int) (currXPosition + (deltaTime * xSpeed));
   currYPosition =(int) (currYPosition + (deltaTime * ySpeed));


	if (currXPosition < radius )
		offLeft();

	if  (currXPosition > panelWidth - radius)
            bounceOffRightWall();

	 if ( currYPosition > panelHeight - radius )
          bounceOffFloor();

    if (currYPosition < radius)
         bounceOffTop();

	if(controlpanel.animatedDeath.isSelected() && currTime+ 1000 >= lifeTime){
		angularVelocity +=10000;
		randoColor = 5;
	}
   	if(controlpanel.gravityBox.isSelected()){
		yAcceleration = gravity;
	}
	if (currYPosition > panelHeight - radius)
	    currYPosition = panelHeight - (int)radius;


}
public void bounceOffTop()
{
   ySpeed = Math.abs(ySpeed);
}
void offLeft()
{
	xSpeed = Math.abs(xSpeed);
	 xSpeed *= bouncing;
}
void bounceOffRightWall()
{
	xSpeed = -Math.abs(xSpeed);
	 xSpeed *= bouncing;
}
public void bounceOffFloor()
{
   ySpeed = -Math.abs(ySpeed);
   ySpeed *= bouncing;
}
public void angluarUpdate()
{
	angle =  (angle + (deltaTime * angularVelocity));
}
void updateSpeed()
{
   xSpeed = xSpeed + xAcceleration * deltaTime;
   ySpeed = ySpeed + yAcceleration * deltaTime;
}
public void addMortalityListener(MortalityListener m)
{
	listenerList.add(m);
}
public void updateVitality()
{
}
public void draw(Graphics g1)
{}
public void bouncin()
{
	bouncing = controlpanel.bouncinSlider.getValue();
	bouncing = bouncing/10;

}
}//end class Living thing


































