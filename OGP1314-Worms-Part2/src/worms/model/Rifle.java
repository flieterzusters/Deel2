package worms.model;

public class Rifle extends Projectile {
/**
* variable that contains the yield of the Rifle projectile
*/
private final int yield;
/**
* variable that contains the mass of the Rifle
*/
private static double massRifle=0.01;	
/**
* variable that contains the force a Rifle has. 
*/
private static double ForceRifle=2.5;
/**
* variable that contains the maximal yield a Rifle can have.
*/
private int maxYield=100;
/**
* variable that contains the minimal yield a Rifle should at least have.
*/
private int minYield=0;
/**
* creates a Rifle with the given parameters
* @param world the world wherein the Rifle is created
* @param x the position of the Rilfe on the x-axis
* @param y the position of the Rifle on the y-axis
* @param yield the given yield for the Rifle projectile
* @throws IllegalArgumentException if the given yield is not valid
* 		/! ValidYield(yield)
* @post this.yield= yield
* @effect super(world, x, y)
*/
public Rifle(World world, double x, double y, int yield)
		throws IllegalArgumentException {
	super(world, x, y);
	if (! ValidYield(yield))
		throw new IllegalArgumentException();
	this.yield= yield;
}
/**
* checks if the provided yield is between the minimal and maximal yield for a Rifle.
* @param yield the yield that is need to be checked
* @return true if the given yield is between the  minYield & maxYield
*/

private boolean ValidYield(int yield) {
	
	return (yield>=minYield) && (yield<=maxYield);
}
/**
* gives the mass a Rifle has
* @return the mass of the Rifle /massRifle
*/
public static double getMass(){
return massRifle;
}
/**
* returns the cost of a launch of a Rifle
* @return /result=10
*/
public static int getCostLaunch(){
	return 10;}
/**
 * gives the force a Rifle has.
 * @return /result=ForceRifle
 */
public static double getForce(){
	return ForceRifle ;
}
/**
* returns the yield of this Rifle Projectile
* @return the yield of the Rifle that was provided whith the creation of the Rifle
* 		/result = this.yield
*/
public double getYield() {
	return this.yield;
}
/**
* gives the damage a Rifle does
* @return /result=80
*/
public int getDamage(){
	return 20;
}
}