package worms.model;

import java.util.Collection;
import java.util.Random;

import worms.model.IFacade;
import worms.model.ModelException;

public class Facade implements IFacade {


	public void addEmptyTeam(World world, String newName)throws IllegalArgumentException {
		try{
			world.addTeam(new Team(newName));
			}
			catch(IllegalArgumentException illegalArgument){
				throw new ModelException(illegalArgument);
			}	
		
	}

	public void addNewFood(World world) {
		world.addNewFood();
	}

	public void addNewWorm(World world) {
		world.addNewWorm();
		
	}

	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	public boolean canMove(Worm worm) {
		return worm.movePossible();
	}

	public boolean canTurn(Worm worm, double angle) {
		return worm.TurnPossible(angle);
	}

	public Food createFood(World world, double x, double y) {
		Vector positionFood = new Vector(x,y);
		Food freshFood = new Food(world, positionFood);
		return freshFood;
	}

	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
		try {World newWorld = new World(width,height,passableMap,random);
	      return newWorld;}
	      catch(IllegalArgumentException illegalargument) {
				throw new ModelException(illegalargument);
			}
	}

	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name) throws IllegalArgumentException {
		Vector position = new Vector(x,y);
		try {Worm wormpie = new Worm(world, position,direction,radius,name);
			return wormpie;}
		catch(IllegalArgumentException illegalargument) {
			throw new ModelException(illegalargument);
		}
	}

	public void fall(Worm worm) {
		worm.fall();
	}

	public int getActionPoints(Worm worm) {
		return worm.getActionPoint();
	}

	public Projectile getActiveProjectile(World world) {
		return world.getActiveProjectile(); 
	}

	public Worm getCurrentWorm(World world) {
		return world.getActiveWorm();
	}

	public Collection getFood(World world) {
		return world.getFoods();
	}

	public int getHitPoints(Worm worm) {
		return worm.getHitPoint(); 
	}

	public double[] getJumpStep(Projectile projectile, double t) {
		return projectile.getJumpStep(t);
	}

	public double[] getJumpStep(Worm worm, double t) {
		return worm.getJumpStep(t);
	}

	public double getJumpTime(Projectile projectile, double timeStep) {
		return projectile.getJumpTime(projectile,timeStep);
	}

	public double getJumpTime(Worm worm, double timeStep) {
		return worm.getJumpTime();
	}

	public double getMass(Worm worm) {
		return worm.getMass();
	}

	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxPossiblePoints();
	}

	public int getMaxHitPoints(Worm worm) {
		return worm.getMaxPossiblePoints();
	}

	public double getMinimalRadius(Worm worm) {
		return Worm.minRadiusWorm;
	}

	public String getName(Worm worm) {
		return worm.getNaming();
	}

	public double getOrientation(Worm worm) {
		return worm.getOrientation();
	}

	public double getRadius(Food food) {
		return food.getRadius();
	}

	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	public String getSelectedWeapon(Worm worm) {
		return worm.getWeapon();
	}

	public String getTeamName(Worm worm) {
		return worm.getTeam().getName();
	}

	public String getWinner(World world) {
		return world.getWinner(); 
	}

	public Collection getWorms(World world) {
		 return world.getWorms();
	}

	public double getX(Food food) {
		return food.getPosition().getPositionX();
	}

	public double getX(Projectile projectile) {
		return projectile.getPosition().getPositionX();
	}

	public double getX(Worm worm) {
		return worm.getPosition().getPositionX();
	}

	public double getY(Food food) {
		return food.getPosition().getPositionY();
	}

	public double getY(Projectile projectile) {
		return projectile.getPosition().getPositionY();
	}

	public double getY(Worm worm) {
		return worm.getPosition().getPositionY();
	}

	public boolean isActive(Food food) {
		return food.isActive();
	}

	public boolean isActive(Projectile projectile) {
		return projectile.isActive();
	}

	public boolean isAdjacent(World world, double x, double y, double radius) {
		Vector position = new Vector(x,y);
		return world.isAdjacent(position, radius);
	}

	public boolean isAlive(Worm worm) {
		return !worm.isTerminated();
	}

	public boolean isGameFinished(World world) {
		return world.gameFinished();
	}

	public boolean isImpassable(World world, double x, double y, double radius) {
		return world.imPassable(x, y, radius);
	}

	public void jump(Projectile projectile, double timeStep) {
		projectile.Jump(timeStep);
	}

	public void jump(Worm worm, double timeStep) throws IllegalArgumentException {
		try {worm.Jump();}
		catch(IllegalArgumentException illegalargument)	{
				throw new ModelException(illegalargument);
				}
	}

	public void move(Worm worm) {
		worm.Move();
		
	}

	public void rename(Worm worm, String newName) {
		  worm.changeNaming(newName); 
	}

	public void selectNextWeapon(Worm worm) {
		worm.nextWeapon();
		
	}

	public void setRadius(Worm worm, double newRadius) throws IllegalArgumentException{
		try{worm.setRadius(newRadius);}
		catch(IllegalArgumentException illegalargument){
			throw new ModelException(illegalargument);
		}
		
	}

	public void shoot(Worm worm, int yield) {
		worm.shoot(yield);
	}

	public void startGame(World world) {
		world.startGame();
	}

	public void startNextTurn(World world) {
		world.nextTurn();
		
	}

	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	} 
}
