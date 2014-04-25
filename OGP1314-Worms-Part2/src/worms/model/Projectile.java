package worms.model;

import java.util.ArrayList;
import java.util.List;

import worms.util.Util;

public class Projectile extends GameObject {


private Worm worm;	

private double x,y;

/**
 * This is the Gravity a worm has, this is final and static because it is the same for all the worms and can't change during the game.
 */
public static final double Gravity = 9.80665;



/**
//KLOPT NIET
public Projectile(Worm worm, Vector position, double angle, double) throws IllegalArgumentException{
	this.worm = worm;
	this.newX = worm.getPosition().getPositionX() + (Math.cos(worm.getOrientation()) * worm.getRadius()); 
	this.newY = worm.getPosition().getPositionY() + (Math.sin(worm.getOrientation()) * worm.getRadius());
	
	if (!Worm.getPosition().validX(newX) || !Worm.getPosition().validY(newY))
			throw new IllegalArgumentException();
		
	}
	
	
public Projectile(World world, Position position, double angle, double forceTime, double propulsionYield)
	
}
	// wat ik dacht maar klopt ook niet
//waar is getPositionX en getPositionY naartoe in klasse Worm
public Projectile(Worm worm, double radius  ){
	super((worm.getPosition().getPositionX()+1.1*worm.getRadius()),(worm.getPosition().getPositionY()+1.1*worm.getRadius()), radius, worm.getOrientation());
	
}


// krijg hier een fout er zou moeten staan van Porjectile(World world, double x, double y) maar dan lukt het mij niet om de radius van de womr op te halen
public Projectile(Worm worm) throws IllegalArgumentException {
	if (world.ValidCoordinateX(x+ Math.cos(worm.getOrientation()*worm.getRadius())
	) && world.ValidCoordinateY(y+ Math.sin(worm.getOrientation()*worm.getRadius()))){
	this.setPosProjectileX(this.getPosition().getPositionX());
	this.setPosProjectileY(this.getPosition().getPositionY());}
	else throw new IllegalArgumentException("no valid Y coordinate for projectile");
	this.setWorld(world);
	}	
*/

public Projectile(World world, double x, double y){
	super(world,x,y,0);
	this.setRadius(getRadius());
}
public Worm getWorm(){
	return this.worm;

}
public void setPosProjectileY(double y)throws IllegalArgumentException {
	double posY= y+ Math.sin(worm.getOrientation()*worm.getRadius());
	if (world.ValidCoordinateY(posY))
		 this.y =posY ;
	else throw new IllegalArgumentException("no valid Y coordinate for projectile");
}
public double getPosProjectileY(){
	return y;
}
public void setPosProjectileX(double x)throws IllegalArgumentException {
	double posX= x+ Math.cos(worm.getOrientation()*worm.getRadius());
	if (world.ValidCoordinateX(posX))
		 this.x =posX ;
	else throw new IllegalArgumentException("no valid X coordinate for projectile");
}
public double getPosProjectileX(){
	return x;
}

public void setWorld(World world){
this.world =world;
}
public World getWorld() {
	return world;
}

public double getRadius(){
	return worm.getRadius();	
}
private boolean hitWorm = false;

 public boolean isActive(){
	 WormsAlife();
 if (hitWorm == true && this.getWorld().ValidCoordinateX(x) && this.getWorld().ValidCoordinateY(y) && this.getWorld().imPassable(x, y, this.getRadius()))
	  return true;
 else return false;
 

 }

 public void WormsAlife(){
	 if (this.getWorld() !=null){
		 List<Worm>worms = new ArrayList<Worm>(getWorld().getWorms());
		 for (Worm worm: worms)
		 {
			 if (worm != getWorld().getWorms() && world.inRange(x,y,this.getRadius()))
			 {damage(worm,getDamageWapon());
			 hitWorm=true;
			 }
		 }
	 }
		 
 }


private void damage(Worm worm, Object damage) {
	
	
}
public void Bazooka(int yield, Projectile projectile){
	this.getWorld().addProjectile(projectile);
	new Bazooka(this.world,this.x,this.y,yield);
	Jump(yield);
}
public void Rifle(int yield, Projectile projectile){
	this.getWorld().addProjectile(projectile);
	 new Rifle(this.world,this.x,this.y,yield);
	Jump(yield);
}

public int getDamageWapon() {
	if (this.getWorld().activeWorm().getWeapon() == "Bazooka")
		return Bazooka.getDamage();
	return 0;
	
		
}
public void OverlapWithWorm() {
	if (!isTerminated()){
		for(Worm worm:  this.getWorld().getWorms())
			if (this.overlap(worm)) 
				setDamage(worm);
	}
}
private boolean overlap(Worm worms) {
	for (Worm worm: this.getWorld().getWorms()){
		if(Util.fuzzyLessThanOrEqualTo(this.getPosition().distance(getPosition()), this.getRadius()+worm.getRadius()) && (worm!=this.world.activeWorm())){
			return true;
		}
	}
	return false;
}


public void setDamage(Worm worm){
	worm.setActionPoints(worm.getHitPoint()-getDamageWapon());
}

public void Jump(double jumpTime) throws IllegalArgumentException {
			if(this.CheckOrientation()){
				double[] endPosition = this.getJumpStep(jumpTime);
				this.setPosition(new Vector(endPosition[0],endPosition[1]));
			}
			else { throw new IllegalArgumentException("this is not a valid jump, the projectile has to be facing up.");}
	
}


/**
 * Check if the worm has enough action points to make a jump.
 * @return TRUE if the worm has more then 0 action points, FALSE if the worm has 0 action points.
 */
public boolean CheckActionPoints() {
	return this.worm.getActionPoint() > 0 ;
}


/**
 * Check if the worm is facing up to make a jump. 
 * @return TRUE if the worm's orientation is in the range ( 0 < orientation < PI ) 
 */
public boolean CheckOrientation() {
	return Util.fuzzyGreaterThanOrEqualTo(this.worm.getOrientation(),0) && (!Util.fuzzyGreaterThanOrEqualTo(this.worm.getOrientation(),Math.PI));
}



/**
 * Get the time to make a jump.
 * @return the time in seconds for a potential jump from the current position.
 */
//formal doc
public double getJumpTime(Projectile projectile,double timeStep) {
		if (this.CheckOrientation())
			return 0;
	Vector currentPosition= new Vector(this.getPosition().getPositionX()+Math.cos(this.worm.getOrientation()),this.getPosition().getPositionY()+Math.sin(this.worm.getOrientation()));
	boolean newPositionFound = false;
	if (this.getWorld().imPassable(currentPosition.getPositionX(),currentPosition.getPositionY(),this.getRadius())){
		newPositionFound=true;
		return 0;
	}
	int nbSteps = 0;
	while(!newPositionFound &&! this.getWorld().outOfWorld(currentPosition.getPositionX(),currentPosition.getPositionY(),this.getRadius())){
		nbSteps++;
		Vector newPosition= jumpStep(nbSteps*timeStep);
		currentPosition = new Vector(newPosition.getPositionX(),newPosition.getPositionY());
		if (this.getWorld().imPassable(currentPosition.getPositionX(),currentPosition.getPositionY(), this.getRadius()) ||this.overlap(getWorm()))
		{		newPositionFound=true;
		return nbSteps*timeStep;	}
	}
	return 0;
}
// divided by 0 pi/2
public Vector jumpStep(double t){
	double v0 =getVelocity();
	double v0X=v0*Math.cos(this.getWorm().getOrientation());
	double v0Y=v0*Math.sin(this.getWorm().getOrientation());
	return new Vector(this.x+v0X*t,this.y+v0Y*t-0.5*Gravity*Math.pow(t,2));
}
public double getVelocity(){
	if (this.getWorm().getWeapon()=="Bazooka")
			return ((Bazooka.getForce()/Bazooka.getMass())*0.5);
	
	if (this.getWorm().getWeapon()=="Rifle")
				return ((Rifle.getForce()/Rifle.getMass())*0.5);
	return 1;
		
}

/**
 * Computes the on-flight position [x,y] of a jumping worm at any seconds afther launch.
 * @param t
 * 		the time in seconds for a potential jump from the current position.
 * @return
 * 		the end position of the worm after a jump.
 */
//formal doc
public double[] getJumpStep(double t){  
	double velocityX=(this.getIntialVelocity()*Math.cos(this.worm.getOrientation()));
	double velocityY=(this.getIntialVelocity()*Math.sin(this.worm.getOrientation()));
	double positionAftherJumpX= (this.getPosition().getPositionX() + (velocityX*t));
	double positionAftherJumpY= (this.getPosition().getPositionY() + ((velocityY*t) - 0.5*(Worm.Gravity*Math.pow(t,2))));
	double[] endPosition = {positionAftherJumpX,positionAftherJumpY};
	return endPosition;	
}


/**
 * Looks what is the initial velocity of the worm.
 * @return the initial velocity of the worm in current conditions
 * 			/F= (5*this.getActionPoint())+(this.getMass()*Worm.Gravity)
 * 			/V0= ((F/this.getMass())*0.5)
 */
public double getIntialVelocity() {
	double F= (5*this.worm.getActionPoint())+(this.worm.getMass()*Worm.Gravity);
	double V0= ((F/this.worm.getMass())*0.5);
	return V0;		
}

}









