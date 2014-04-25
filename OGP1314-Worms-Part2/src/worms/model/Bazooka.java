package worms.model;
//aanpassingstest

public class Bazooka extends Projectile {
/**
 * variable that contains the yield of the Bazooka projectile
 */
private static int yield;
/**
 * variable that contains the mass of the Bazooka
 */
private static double massBazooka=0.3;	
/**
 * variable that contains the minimal force a Bazooka should at laest have.
 */
private static double minForceBazooka=2.5;
/**
 * variable that contains the maximal force a Bazooka can have. 
 */
private static double maxForceBazooka=9.5;
/**
 * variable that contains the maximal yield a Bazooka can have.
 */
private static int maxYield=100;
/**
 * variable that contains the minimal yield a Bazooka should at least have.
 */
private int minYield=0;
/**
 * creates a Bazooka with the given parameters
 * @param world the world wherein the Bazooka is created
 * @param x the position of the Bazooka on the x-axis
 * @param y the position of the Bazooka on the y-axis
 * @param yield the given yield for the bazooka projectile
 * @throws IllegalArgumentException if the given yield is not valid
 * 		/! ValidYield(yield)
 * @post this.yield= yield
 * @effect super(world, x, y)
 */
public Bazooka(World world, double x, double y, int yield)
		throws IllegalArgumentException {
	super(world, x, y);
	if (! ValidYield(yield))
		throw new IllegalArgumentException();
	this.yield= yield;
}
/**
 * checks if the provided yield is between the minimal and maximal yield for a Bazooka
 * @param yield the yield that is need to be checked
 * @return true if the given yield is between the  minYield & maxYield
 */

private boolean ValidYield(int yield) {
	
	return (yield>=minYield) && (yield<=maxYield);
}
/**
 * gives the mass a Bazooka has
 * @return the mass of the Bazooka /massBazooka
 */
public static double getMass(){
return massBazooka;
}
/**
 * returns the cost of a launch of a Bazooka
 * @return /result=50
 */
public static int getCostLaunch(){
	return 50;}
/**
 * gives the force a Bazooka has
 * @return / result= minForceBazooka +(this.getYield()*((maxForceBazooka-minForceBazooka)/maxYield))
 */
public static double getForce(){
	return minForceBazooka +(yield*((maxForceBazooka-minForceBazooka)/maxYield));
}
/**
 * returns the yield of this Bazooka Projectile
 * @return the yield of the Bazooka that was provided with the creation of the Bazooka
 * 		/result = this.yield
 */
public double getYield() {
	return this.yield;
}
/**
 * gives the damage a Bazooka does
 * @return /result=80
 */
public static int getDamage(){
	return 80;
}
}
