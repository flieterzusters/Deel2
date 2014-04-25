package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
/**
 * A class of  game objects: worms, projectiles, food.
 * @author Pieter Kusters
 * @author Thibaut Bender
 * @version 1.0
 * 
 */



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
	
/**
 * The current position of the space object in the world.
 */		
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

/**
 * Creates a game object with the initialization parameters position and radius. 
 * 
 * @param position
 * 		  The initial position of game object in the world.
 * @param radius
 * 		  The initial radius of the game object
 * 
 * @effect The position of the game object is set the value given.
 * 		 /new.getPosition() == position
 * @effect The radius of the game object is set the value given.
 * 		 |new.getRadius() == radius
 */		
public GameObject (Vector position, double radius) {
		this.setPosition(position);
		this.setRadius(radius);
}



/**
 * Sets the state of this game object.
 * @param state
 *        the new state of the game object.
 * @post the new state of game object is equal to the right value (ACTIVE or TERMINATED) of the enumeration type.
 * 		/new.getState == State.state
 * 
 */
protected void setState(State state)
{
	if(state == null) {throw new NullPointerException();}
	this.state = state;
}

/**
 * returns the state of this game object.
 * @return the state of the game object.
 * 
 */
protected State getState()
{
	return this.state;
}

/**
 * Sets the new position in the world of this game object.
 * @param newPosition
 *        The new position of this game object.
 * @post the new position of game object is equal to the value of the parameter.
 * 		/new.getPosition() == position
 */
public void setPosition(Vector newPosition) {
	this.Position = newPosition;
}

/**
 * returns the position in the world of this game object.
 * @return the radius of this game object.
 */
public Vector getPosition() {	
	return this.Position;
}

/**
 * set the radius of the game object to the given radius.
 * @param givenradius
 * 		  the new radius of this game object.
 * @post the new radius of game object is equal to the value of the parameter
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
 * checks if the radius of game object is a valid radius.
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
 * returns the radius of the worm.
 * @return the radius of this worm.
 */
@Basic
public double getRadius() {
	return radius;
}



/**
 * Sets this game object in a world.
 * @param world 
 * 		 the new world for this game object.
 * @post new world of game object is equal to the value of the parameter.
 * 		  /new.getWorld() == world.
 */
public void setWorld(World world) {
	if (this.isValidWorld(world))
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
 * Check whether this given world is valid for this game object when setting the world to a game object.
 * @param  world
 * 			the given world for this game object.
 * @post false if the world is finished or world is null value.

 */		
public boolean isValidWorld(World world){ //p3: each game object one world, not twice-- nog toevoegen hasProperWorld()...
	if (world == null)  {return false;} 
	else {return true;} 
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