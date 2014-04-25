
package worms.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * describes x and Y valuee.
 * 
 * @invar 	x always needs to have a valid value.
 * 			| validX(double x)
 * @invar	y always needs to have a valid value.
 * 			| validY(double y)
 * 
 * @author Pieter Kusters
 * @author Thibaut Bender
 * @version 1.0
 */

public class Vector {
	//geknipt uit Worm
	/**
	 * This is the variable that gives the horizontal position of the worm on the x-axis.
	 */
	private double positionX;
	
	/**
	 * This is the variable that gives the vertical position of the worm on the y-axis.
	 */
	private double positionY;
	
	
	
	public Vector(double x, double y) {
		this.setPositionX(x);
		this.setPositionY (y);
	}
	
	
	
	/**
	 * Gives the horizontal position of the worm.
	 * @return gives the location of the worm on the X-axis
	 */
	@Basic
	public double getPositionX(){
		return this.positionX;
	}
	
	/**
	 * The horizontal position of the worm is set.
	 * @param X the x-coordinate of the worm
	 * @post the new x-coordinate for this worm is set to the given x-coordinate.
	 * 		/new.getX()==x
	 * @throws IllegalArgumentException
	 * 			If the given x-coordinate is not valid
	 * 		/!validX
	 */
	public void setPositionX(double X) throws IllegalArgumentException {
		if (validX(X)){
		this.positionX=X;}
		else throw new IllegalArgumentException();
	}
	

	/**
	 * Looks if the horizontal coordinate is valid.
	 * @param x the x-coordinate of the worm
	 * @return looks if the given parameter is of the type double if it is it returns true
	 */
	public boolean validX(double x) {
	return (!Double.isNaN(x));
	}

	
	
	
	
	/**
	 * gives the vertical position of the worm.
	 * @return gives the location of the worm on the y-axis
	 */
	@Basic
	public double getPositionY(){
		return this.positionY;
	}

	/**
	 * The vertical position of the worm is set.
	 * @param Y the y-coordinate of the worm
	 * @post the new y-coordiinate for this worm is set tot the given x-coordinate.
	 * 		/new.getY()==y
	 * @throws IllegalArgumetnExeption
	 * 				if the fiven y-coordinate is not valid
	 * 		/!validY
	 */
	public void setPositionY(double Y){
	if (validY(Y)){
	this.positionY=Y;}
	else throw new IllegalArgumentException();
	}


	/**
	 *  Looks if the vertical coordinate is valid.
	 * @param y the y-coordinate of the worm
	 * @return looks if the given parameter is of the type double if it is it returns true
	 */
	public boolean validY(double y) {
	return (!Double.isNaN(y));
	}



	public double distance(Vector currentPosition) {
		return Math.sqrt(Math.pow(this.getPositionX()-currentPosition.getPositionX(),2)+Math.pow(this.getPositionY()-currentPosition.getPositionY(), 2));
	}
	
	
	
}
