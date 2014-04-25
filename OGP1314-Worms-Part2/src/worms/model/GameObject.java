package worms.model;

import be.kuleuven.cs.som.annotate.Basic;




/**
 * hb p 453...
 *creeren van een superclasse voor worm, food  enzo aan te maken (hierarchie creeren)
 *gevolg: super(...) bij argumenten object worm in classe worm
 *in Worm kunnen alle methode uit deze klasse direct aangesproken worden
 *
 */


public abstract class GameObject {  //abstract want has now object of his own zie p 477



/**
 * Enumeration of all possible states of an Game object.
 */
protected static enum State{
	ACTIVE,TERMINATED;
}


/**
*  this game object begins with ACTIVE state.
*/
protected State state = State.ACTIVE;		
	
	
private Vector Position;	

/**
 * This indicates the minimal radius a worm should have when he is created.
 */
private final static double minRadiusWorm=0.25;

/**
 * The worm is shaped as a circle, this circle is defined by this value, it must be greater than the minRadius.  
 */
private double radius;

/**
 * The world in which this object is placed. Null if this object doesn't have a world.
 */
protected World world;

	
public GameObject (Vector position, double radius) {
		this.setPosition(position);
		this.setRadius(radius);
}


public GameObject(World world2, double x, double y, int i) {
	// TODO Auto-generated constructor stub
}


/**
 * Sets the state of this game object.
 */
protected void setState(State state)
{
	if(state == null) {throw new NullPointerException();}
	this.state = state;
}


protected State getState()
{
	return this.state;
}


public void setPosition(Vector newPosition) {
	this.Position = newPosition;
}


public Vector getPosition() {	
	return this.Position;
}

/**
 * gives the worm the radius given by the parameter
 * @param givenradius the new radius of this worm
 * @post the new radius of the worm is equal to the value of the parameter
 * 		/new.getRadius() == radius
 * @throws IllegalArgumentException
 * 				the given radius is not valid
 */
public void setRadius(double givenradius) throws IllegalArgumentException {
	if (validRadius(givenradius)) {
		this.radius= givenradius;
	}
	else throw new IllegalArgumentException("Illegal value for the radius");
}
/**
 * checks if the radius of the worm is a valid radius
 * @param radius the radius witch need to be checked if it is valid or not.
 * @return True if the radius is bigger than the minimal radius or 
 * 			false if the radius is lower than the minimal radius a worm should need to have.
 * 		/radius>minRadius
 */

public boolean validRadius(double radius){
	if (radius>getMinRadiusWorm()) {return true;}
	else {return false;}
}

/**
 * basic inspector that returns the radius of the worm.
 * @return the minimal radius that the worm should have
 */
@Basic
public static double getMinRadiusWorm() {
	return minRadiusWorm;
}


/**
 * the radius of the worm
 * @return the radius of this worm
 */
@Basic
public double getRadius() {
	return radius;
}



/**
 * Sets this game object in a world.
 * @param world 
 */
public void setWorld(World world) {
	if (this.validWorld(world))
	{this.world = world;}
}

/**
 * Returns the world of this game object.
 * @return world
 */
public World getWorld() {
	return world;
}	
	
	
	
/**
 * Check whether this given world is valid for this game object.
 * @param  world
 */
public boolean validWorld(World world){ //p3: each game object one world, not twice
	if (this.isTerminated() || (world == null) ) {return false;} 
	else {return true;} //nog niet volledig 
	}


/**
 * Check whether this game object is terminated or not.
 * @return True if the state of this object is terminated.
 */
public boolean isTerminated(){
	if (this.getState() == State.TERMINATED) {return true;}
	else {return false;}
}	



/**
 * This object will be removed from the world.
 */
protected void terminate(){
	if(getWorld() == null) {throw new NullPointerException();}
	getWorld().removeObject(this);
	this.setState(State.TERMINATED);
}









}