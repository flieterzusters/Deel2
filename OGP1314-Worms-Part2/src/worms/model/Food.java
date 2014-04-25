package worms.model;


public class Food extends GameObject {
/**
 * variable that contains the world for the food object	
 */
private World world;	
/**
 * variable that contians the position of the food object
 */
private Vector Position;
/**
 * variable that has the radius that a food object has
 */
private static double foodRadius = 0.20;

/**
 * Creates a new food.
 * @param world the world wherein the food is created
 * @param position the location where the food element is located in the world
 *  @effect Food is initialized as a GameObject with a given position and radius.
 *        | super(position, foodRadius)
 * @effect the food is set to the world
 * 		/this.setWorld(world)
 *         
 */	
public Food(World world, Vector position){
	super(position, getFoodRadius());
	this.setWorld(world);
}

/**
 * sets the world for the food object to the given world
 * @param world the provided world where the food obdject will be placed in
 * @throws IllegalStateException if the food obdject is already assigned to a world
 */
public void setWorld(World world) throws IllegalStateException{
	if (hasWorld())
		throw new IllegalStateException();
	this.world =world;
	}
/**
 	//Not used
public boolean possibleWorld (World World){
	return (world!= null) && (!world.hasFood(this));
}
*/
/**
 * looks if the food object isn't already assigned to a world
 * @return true if the food object has a world assigned
 * 	/!(getWorld()== null
 */
public boolean hasWorld(){
	return(!(getWorld()== null));
}
/**
 * removes the world from the food obdject
 * @throws NullPointerException if there is no world assigned
 * 
 */
public void removeWorld() throws NullPointerException{
	if (! hasWorld())
		 throw new NullPointerException();
	World formerWorld = getWorld();
	this.world = null;
	formerWorld.removeFood(this);
}

/**
 * gives the world whereto the foodobject is assigned to
 * @return the world where it is assigned to
 * 		/this.world
 */
public World getWorld(){
	return this.world;
	}
/**
 * sets the position where the food object need to be located
 * @param newPosition the position where he food object need to be placed
 */
public void setPosition(Vector newPosition) {
	this.Position = newPosition;
	}
/**
 * gives the location of the food object
 * @return the position of the food object
 */
public Vector getPosition() {	
	return this.Position;
	}
/*
private double x;

private double y;


public void setX (double x){
	this.x=x;
}

public void setY (double y){
	this.y=y;
}

public double getX(){
	return this.x;
}
public double getY(){
	return this.y;
}
*/
/**
 * looks if the food object is still active in the game
 * @return true if the state of the food object isn't terminated
 * 		/if (this.terminated=false)  return= true
 */
public boolean isActive(){
	if (this.terminated=false){
		return true;
	}
	else { return false;}
}
/**
 * variable containing the state of the food object (terminated or not)
 */
private  boolean terminated=false;
public void terminated(){
	this.terminated=true;
}
/**
 * gives the radius of the food object
 * @return the value of the variable foodRadius
 */
public static double getFoodRadius() {
	return foodRadius;
}
/*
 * sets the foodRadius to the provided parameter
 * @param FOODRADIUS the provided radius for the food object
 *  @effect /foodRadius = FOODRADIUS

public static void setFoodRadius(double FOODRADIUS) {
	foodRadius = FOODRADIUS;
}
*/




}