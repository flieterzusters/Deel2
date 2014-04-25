package worms.model;

import java.util.*;

/**
 * this class represents the game world of the game "worms".
 *
 */
public class World {

private double width, height;
private boolean[][] passableMap;
private Random random;
private Worm worm;

/**
 * creates a world and initializes some parameters.
 * @param width the given width of the world.
 * @param height the height a world will have.
 * @param passableMap A map which represent a number of pixels where each pixel has the value true or false so to explain if the area is passable or not. 
 * @param random random number generator
 * @effect the new world is given the width value
 * / this.setWidth(width)
 * @effect the new world is given the height value
 *  / this.setHeight(height);
 * @effect the passable map is set to the world
 *  /this.passableMap = passableMap;
 * @post  the provided map is set to the game as gameWorld
 * 	/this.passableMap = passableMap
 * @post random numbers are generated an provided to the game
 * 	/this.random = random
 * @throws IllegalArgumentException if the height or width aren't valid
 * 	/!isValidSize(newWidth) // !isValidSize(newHeight)
 */
public World(double width, double height,boolean[][] passableMap, Random random) throws IllegalArgumentException  {//zoals in interface	
	this.setWidth(width);
	this.setHeight(height);
	this.setPassableMap(passableMap);
	this.random = random ;

	CollectionWorms= new ArrayList<Worm>();
	foods = new ArrayList<Food>();
	projectiles = new ArrayList<Projectile>();
	team= new ArrayList<Team>();	
}	


/**
 * looks if there are worms left in the game
 *	/gameFinished()
 *if not the next worm is selected and on turn and HitPoints are increased with 10 and ActionPoints to maximumPoints
 *	/iterator=worms.iterator() //setActiveWorm((Worm) iterator.next()) //activeWorm().setActionPoints(activeWorm().getMaxPossiblePoints())
 *	/activeWorm().setHitPoints(activeWorm().getHitPoint()+10)
 */
public void nextTurn(){
	if (gameFinished())
		return;
	if(! iterator.hasNext())
		iterator=CollectionWorms.iterator();
	setActiveWorm((Worm) iterator.next());
	activeWorm().setActionPoints(activeWorm().getMaxPossiblePoints());
	activeWorm().setHitPoints(activeWorm().getHitPoint()+10);
}

/**
 * initialises the status of the game to not Fisished jet
 * /finished = false
 */
private boolean finished = false;

/**
 * gives the status of the game to look if it is already finished or not
 * @return if the game is finished
 * /return finished
 */
public  boolean gameFinished(){
	return finished;
}

/**
 * when game came to an end game is stopped and alle worms and teams are removerd
 * /this.finished =true	//this.setActiveWorm(null) 	//this.setActiveTeam(null);
 */
public void setFinished(){
	this.finished =true;
	this.setActiveWorm(null);
	this.setActiveTeam(null);
}

/**
 * Assigns the team who can play now.
 * /this.activeTeam=activeTeam
 * @param activeTeam the team who is on turn now
 * @post the new team is on turn.
 */
public void setActiveTeam(Team activeTeam){
	this.activeTeam= activeTeam;
}

/**
 * gives the team who is on turn at this moment.
 * @return the team who is on turn.
 * /this.activeTeam
 */
public Team getActiveTeam(){
	return this.activeTeam;
}

/**
 * the last team who was added to the game is the active on the start of the game
 */
private Team activeTeam;

/**
 * basic setter who assigns the worm who is on turn at this moment to activeWorm
 * @param worm the worm who is currently on turn
 * 
 */
public void setActiveWorm(Worm worm){
	this.activeWorm =worm;
}

/**
 * getter to get the worm who is actually on turn.
 * @return the worm who is currently on turn
 *  /this.activeWorm
 */
public Worm getActiveWorm(){
	return this.activeWorm;
}
/**
 * the last worm who is assigned to the world
 */
private Worm activeWorm;
/**
 * basic iterator to go over the list of worms
 */
private Iterator<Worm>iterator;
/**
 * makes the game ready to start playing the game.
 */
public void startGame(){
	iterator =CollectionWorms.iterator();
	if(iterator.hasNext())
	setActiveWorm((Worm)iterator.next());
	if (this.getActiveTeam() !=null && this.getActiveTeam().getWormCollection().size()==0)
		this.removeTeam(this.getTeam());
	setActiveTeam(null);
	setGameStarted();
}

/**
 * change the state of the game to started.
 * @throws IllegalStateException if there is only one team to play with the game can not start, or if there is a worm who is not assigned to a team.
 *  /(this.team.size()<=1) //!getTeam().hasWorm(worm))
 * @effect if there is only one worm game is finished
 */
private void setGameStarted()throws IllegalStateException{
	if (CollectionWorms.size()<=1)
		this.setFinished();
	else if (this.team.size()<=1) throw new IllegalStateException("there aren't enough teams to play");
	while (! getTeam().hasWorm(worm)) {
		this.team.iterator().next();
		if ((this.team.iterator().next() ==null) ){		
		throw new IllegalStateException("not all worms are assinged to a team.");
		}
	}
}
/**
 * basic setter to set set the width of the game world.
 * @param newWidth the provided width of the world
 * @throws IllegalArgumentException if the width is not valid 
 * 	/!isValidSize(newWidth)
 * @effect the width of the game world is set to the provided width.
 * 	/width = newWidth
 */
private void setWidth(double newWidth) throws IllegalArgumentException {
	if(!isValidSize(newWidth)) {throw new IllegalArgumentException("no valid width");} // catch aan te vullen in facade
	else {width = newWidth;}
	}
/**
 * getter for the width of the gameworld.
 * @return the width of the game world
 */
public  double getWidth() {
	return this.width;
	}
/**
 * basic setter to set set the height of the game world.
 * @param newHeight the provided width of the world
 * @throws IllegalArgumentException if the height is not valid 
 * 	/!isValidSize(newHeight)
 * @effect the height of the game world is set to the provided height.
 * 	/height = newHeight
 */

private void setHeight(double newHeight) throws IllegalArgumentException {
	if(!isValidSize(newHeight)) {throw new IllegalArgumentException("no valid hight");}
	else {height = newHeight;}
}	
/**
 * getter for the height of the gameworld.
 * @return the height of the game world
 */

public double getHeight() {
	return this.height;
	}
/**
 * looks if the value is valid for the size of the world
 * @param size the provided size that will be tested.
 * @return true if the provided size is a vaid double and between null and double.MAX_VALUE.
 * 	/Double.isNaN(size) &&  (0 <= size) && (size <= Double.MAX_VALUE)
 */

public boolean isValidSize(double size) {
	if (Double.isNaN(size) &&  (0 <= size) && (size <= Double.MAX_VALUE))
			{return true;}
	else {return false;}
	}
/**
 * getter for the width of the world in pixel size
 * @return the width of the world in size of number of pixels
 */
public double getPixelAreaWidth(){
	return getWidth()/ passableMap[0].length;
}
/**
 * getter for the height of the world in pixel size
 * @return the height of the world in size of number of pixels
 */
public double getPixelAreaHeight(){
	return getHeight()/ passableMap.length;
}
/**
 * getter to return the x position of the game location in pixel value and looks if it is a valid value
 * @param x the location on the x-axis of the world map
 * @return if it is a valid coordinate for x it returns the coordinate of x in pixelCoordinates 
 * 	/ValidCoordinateX(x)
 * @throws IllegalArgumentException if the provided parameter isn't a valid value.
 */
public int getCoordinatePixelX(double x) throws IllegalArgumentException { //catch aanvullen
	if (ValidCoordinateX(x)){
		double pixelWidth=getPixelAreaWidth();
		int pixelCoordinate = passableMap.length;
		for(double i = passableMap.length; i>=0;){
			if (x>=i){
				break;}
			pixelCoordinate= pixelCoordinate-1;
			i= i-pixelWidth;
			}
		return pixelCoordinate;
		}
	else throw new IllegalArgumentException();	
	}
/**
 * looks if the provided value is valid
 * @param x the value to check
 * @return true if the provided value is between 0 and the width of the world
 * 	/x>= 0 && x<=getWidth()
 */
public boolean ValidCoordinateX(double x) {
	if (x>= 0 && x<=getWidth()){
	return true;}
	else return false;
}

/**
 * getter to return the y position of the game location in pixel value and looks if it is a valid value
 * @param y the location on the y-axis of the world map
 * @return if it is a valid coordinate for y it returns the coordinate of y in pixelCoordinates 
 * 	/ValidCoordinateY(y)
 * @throws IllegalArgumentException if the provided parameter isn't a valid value.
 */
public int getCoordinatePixelY(double y) throws IllegalArgumentException {
	if (ValidCoordinateY(y)){
		double pixelHeight=getPixelAreaHeight();
		int pixelCoordinate = passableMap[0].length;
			for(double i = passableMap[0].length; i>=0;){
				if (y>=i){
					break;}
				pixelCoordinate= pixelCoordinate-1;
				i= i-pixelHeight;
				}
			return pixelCoordinate;
	}
else throw new IllegalArgumentException();	
}
/**
 * looks if the provided value is valid
 * @param y the value to check
 * @return true if the provided value is between 0 and the height of the world
 * 	/y>= 0 && y<=getHeight()
 */
public boolean ValidCoordinateY(double y) {
	if (y>= 0 && y<=getHeight()){
		return true;}
		else return false;
}


/**
 *  gives the map where menisioned the areas who are passable or not	
 * @return the map where located the passable and impasseble spaces
 */
public boolean[][] getPassableMap(){
	return this.passableMap;
}
/**
 * loads the map for the world and initialises it to the worlds passableMap
 * /this.passableMap =map
 * @param map the map provided which represent the passable and impassable areas
 * @effect the map for the world is created.
 */
public void setPassableMap(boolean[][] map) throws IllegalArgumentException{
	if (map == null){throw new IllegalArgumentException("no valid map");}
	this.passableMap =map;
	
}
/**
 * checks if the provided coordinates and radius of the object is fully into the world and do not pass the boundarys.
 * @param x the location on the x-axis 
 * @param y the location on the y-axis
 * @param radius the radius of the object
 * @return false if it is fully located in the world else true
 * 	/!(x+Math.abs(radius)>getWidth()) && !(y+Math.abs(radius)>getHeight()) && !(x-Math.abs(radius)<0.0) && !(y-Math.abs(radius)<0.0)
 */
public boolean outOfWorld(double x, double y, double radius) {
	if (x+Math.abs(radius)>getWidth())
	return true;
	if (y+Math.abs(radius)>getHeight())
	return true;
	if (x-Math.abs(radius)<0.0)
	return true;
	if (y-Math.abs(radius)<0.0)
	return true;
	else return false;
	
}

/**
 * Removes a game object from this world.
 */
public void removeObject (GameObject object) {
//laten staan zie link met game object terminate()
}


/**
 * a set that contains all the worms of the game
 */

private List<Worm> CollectionWorms = new ArrayList<Worm>();

/**
 * add a worm to the set of worms
 * /this.getWorms()).add(wormpie)
 */
public void addWormTocollection(Worm wormpie) {
    assert (wormpie !=null);
	(this.getWorms()).add(wormpie);
}

/**
 * Returns a set collecting all the worms.
 * @return a set containing all the worms
 */
public List<Worm> getWorms() {
	return CollectionWorms ; 
}
/**
 * adds a new worm to the world on a random location with a random angle and the minimum radius.
 * /generetedX =  this.random.nextInt()
 * /generetedY =  this.random.nextInt()
 * /angle =  2 * Math.PI * this.random.nextDouble()
 * /radius = Worm.getMinRadiusWorm()
 * /position = this.adjacentPosition(new Vector(generetedX,generetedY),radius)
 *  @effect if the worm can be placed the worm is created
 *  /createWorm(this,position,angle,radius,"RandomNewWorm") 
 */

public void addNewWorm() {
	int generetedX =  this.random.nextInt();
	int generetedY =  this.random.nextInt();
	double angle =  2 * Math.PI * this.random.nextDouble();
	double radius = Worm.getMinRadiusWorm();
	Vector position = this.adjacentPosition(new Vector(generetedX,generetedY),radius);

	if (position == null){throw new IllegalArgumentException();} //als object niet geplaatst kan worden
	else {createWorm(this,position,angle,radius,"RandomNewWorm") ;}
}
/**
 * the creation of a new worm
 * @param world the world where the worm is placed in
 * @param position the position where the worm will be placed
 * @param angle the angle that the worm is facing at
 * @param radius the radius the worm will have
 * @param name the name the worm has
 */
public void createWorm(World world, Vector position, double angle, double radius, String name){
	Worm wormpje = new Worm(world,position,angle,radius,name);
	this.addWormTocollection(wormpje);
}


/**
 * searches a adjacent Position to impassable terrain for the location where the object is located
 * @param position the provided position of the object
 * @param radius the radius of the object
 * @return the adjacent position for the object next to impassable terrain
 */

private Vector adjacentPosition(Vector position, double radius){
	int XcenterMap = (int) Math.round(this.getWidth() /2 ); 
	int YcenterMap = (int) Math.round(this.getHeight()/2) ; 
	int Xtempor = (int) position.getPositionX();
	int Ytempor = (int) position.getPositionY(); 	
	
	while (! isAdjacent(new Vector(Xtempor,Ytempor),radius)){	
	if (Xtempor < XcenterMap) {Xtempor = Xtempor + 1;}
	if (Xtempor > XcenterMap) {Xtempor = Xtempor - 1;}
	if (Ytempor < YcenterMap) {Ytempor = Ytempor +1;}
	if (Ytempor > YcenterMap) {Ytempor = Ytempor -1;}
	else 
		return null;
	}
	return new Vector(Xtempor, Ytempor);
}

/**
 * looks if there is adjacent to the provided location impassable terrain
 * @param position the provided position to check
 * @param radius the radius of the object
 * @return true if there is no impassable terrain available at the minimum distance  
 */
public boolean isAdjacent(Vector position, double radius){
	double minDistanceRadius = radius * 0.1 ; 
	if (!imPassable(position.getPositionX(), position.getPositionY(), minDistanceRadius)) 
	return true ;
	else return false;
}
/**
 * determines if in a area around the given coordinates and radius the world is impassable or not for an object
 * @param x 
 * 		the x coordinate of the object
 * @param y 
 * 		the y coordinate of the object
 * @param radius 
 * 		the radius coordinate of the object
 * @return 
 * 		looks if the coordinate is not out of the bounds of the world
 */
public boolean imPassable(double x, double y,double radius){
	double direction =0;
	double startX = x;
	double startY = y;
	for (direction=0; direction<=2*(Math.PI);)
		if(passableMap[getCoordinatePixelX(startX)][getCoordinatePixelY(startY)]){
			direction= direction+Double.MIN_VALUE;
			startX= x+radius*(Math.cos(direction));
			startY= y+radius*(Math.sin(direction));
		}
		else return false;
	return true;
	
}

/*
public boolean isAdjacent(double x, double y, double radius) {
	double newRadius = radius * 0.1;
	return ( !isImpassable(x, y, 0) && !isImpassable(x, y, newRadius) );
}

public boolean isImpassable(double x, double y, double radius){
	if (outOfWorld(x,y, radius))
		return true;
	return checkIfImpassable(x,y,radius);
}
private boolean checkIfImpassable(double x, double y, double radius) {
	double XPixel=(x/getWidth())*getPassableMap()[0].length;
	double YPixel=(y/getHeight())*getPassableMap().length;
	double RadiusInPixels=Math.abs(radius/getWidth()*getPassableMap()[0].length);
	for (int i = (int) Math.floor(XPixel*RadiusInPixels); !Util.fuzzyGreaterThanOrEqualTo(i,  XPixel+RadiusInPixels); i++ ){
		if (i>=0 && i< getPassableMap()[0].length){
			for (int j = (int) Math.floor(YPixel*RadiusInPixels); !Util.fuzzyGreaterThanOrEqualTo(j,  YPixel+RadiusInPixels); j++ ){
				if ((j>=0 && j< getPassableMap().length) && (inRange(Math.abs(i-XPixel),Math.abs(j-YPixel),RadiusInPixels)&& (! getPassableMap()[j][i]))){
					return true;}
			}
			
		}
	}
		return false;
}

*/


/**
 * looks if the object is completely situated in passable area.
 * @param i_X the location of the object on the x-axis
 * @param j_Y the location of the object on the y-axis
 * @param radius the radius of the object
 * @return true if the circle of the object is completely in passable area.
 */
public boolean inRange(double i_X, double j_Y, double radius)
{double RadiusInPixels=Math.abs(radius/getWidth()*getPassableMap()[0].length);
	return Math.sqrt(Math.pow(i_X,2)+ Math.pow(j_Y, 2))<=RadiusInPixels;
}
/**
 * a list of all the food objects.
 */
ArrayList<Food>foods= new ArrayList<Food>();
/**
 * add new food to the world on a random location
 * @throws IllegalArgumentException if the random position is not a valid position to place the object
 * @effect a new food object is created on a random location adjacent to impassable terrain
 * /createFood(location)
 */
public void addNewFood() throws IllegalArgumentException{
	int X= random.nextInt();
	int Y= random.nextInt();
	Vector location= adjacentPosition(new Vector(X,Y),Food.getFoodRadius());
	if (location==null)
		throw new IllegalArgumentException();
	createFood(location);
}
/**
 * the creation of the food object in the world
 * @param location the location where the food object will be located
 * @effect the wood is set to the location in the world
 * 	/food.setWorld(this)
 */
public void createFood(Vector location) {
	Food food = new Food(this, location);
	food.setWorld(this);
	addAsFood(food);
	
}
public ArrayList<Food> getFoods(){
	return foods;
}
/**
 * if the value of food is valid the food object is added to the list of foods
 * @param food the provided object of food
 * @throws IllegalArgumentException if the food object is not valid
 * @throws IllegalStateException if the food already exists in the world
 * @effect the food is added to the list of foods
 * /this.foods.add(food)
 */
public void addAsFood(Food food) throws IllegalArgumentException,IllegalStateException{
	if(!canHaveAsFood(food))
		throw new IllegalArgumentException();
		if ((food.getWorld()!=this) || (hasFood(food)))
			throw new IllegalStateException();
		this.foods.add(food);
}
/**
 * checks the food object if it is not empty
 * @param food the food object to check
 * @return true if the food object is not null
 *  /!(food == null)
 */
private boolean canHaveAsFood(Food food) {
	
	return (!(food == null));
}
/**
 * looks if the food already is in the list of food objects
 * @param food the food object to look at
 * @return true if the food object is in the list
 * 	/this.foods.contains(food)
 */
public boolean hasFood(Food food) {
	return this.foods.contains(food);
}
/**
 * delete the food from the world
 * @param food the food that need to be removed
 * @effect if the food is still active the food is removed from the world
 *  /food.terminate()
 */
public void removeFood(Food food) {
	if (food.isActive())
		food.terminate();
	
}
/**
 * basic list where all worms are in
 */
// public List<Worm>worms = new ArrayList<Worm>();
/**
 * looks who is the next worm if all actionPoints are used
 * @return the worm who is currently on turn
 */

public Worm activeWorm(){
	Worm worm =CollectionWorms.get(this.getIndex());
	if (worm.getActionPoint() == 0 && CollectionWorms.size()>1)
		this.nextWorm();
	return worm;
}
/**
 * inreases the index of the list of worms with 1.
 */
public void nextWorm()
{
	this.setIndex(this.getIndex()+1);
}
/**
 * the index who in witch item of the list the worm is playing
 * @return the index number
 */
public int getIndex(){
	return index;
}
/**
 * if the index is at the end of the list sets the index back to 0 else increases the index number to the provided number
 * @param index the provided index number
 * @effect if index is supperior to the size of the worms list index is set to 0 else it is set to the provided number.
 *	/if (index>worms.size()-1)
		this.index=0;
	else
		this.index= index
 */
public void setIndex(int index){
	if (index>CollectionWorms.size()-1)
		this.index=0;
	else
		this.index= index;
}
/**
 * the initial value of the index variable
 */
private int index = 0;
/**
 * a list containing all the team of the world.
 */
private List<Team> team= new ArrayList<Team>();


private List<Projectile> projectiles= new ArrayList<Projectile>();
/**
 * getter to get the current active team who is on turn.
 * @return the current active team
 * /this.getActiveTeam()
 */
public Team getTeam() {
	return this.getActiveTeam();
}
/**
 * delete the team of the world
 * @param team the team that need to be removed
 * @throws IllegalArgumentException if the team doesn't exists
 * @throws IllegalStateException if the team doesn't have a world
 * @effect if the team is still active the team is removed from the list of teams
 *  /this.team.remove(team)
 */
public void removeTeam(Team team) throws IllegalArgumentException, IllegalStateException{
	if (( team==null)|| (! hasTeam(team)))
		throw new IllegalArgumentException("This team is not right"+team);
	if (!team.hasWorld())
		throw new IllegalStateException();
	this.team.remove(team);
}
/**
 * looks of the team provided exists in the list of Team
 * @param team the team to check
 * @return true if the team is in the list of Team
 * 	/this.team.contains(team)
 */
public boolean hasTeam(Team team){
	return this.team.contains(team);
}
/**
 * looks if the provided team is valid
 * @param team the team to check
 * @return true if the team is not null
 */
public boolean canHaveAsTeam(Team team){
	return(!(team==null));

}
/**
 * creates a team whit a provided name if it is a valid name
 * @param name the name the team should have
 * @throws IllegalArgumentException if the provided name empty or if there are already to many teams
 * @effect the team is set to the world
 *  /team.setWorld(this)
 */
public void createTeam(String name) throws IllegalArgumentException{
	if (name == null || team.size()==10)
			throw new IllegalArgumentException();
	Team team = new Team(name);
	team.setWorld(this);
	addTeam(team);
}
/**
 * add the team to the list of Team
 * @param team the team to add to Team
 * @throws IllegalArgumentException if the provided team is null
 * @throws IllegalStateException if the team already exists
 * @effect the team is added to the list of teams
 * 	/this.team.add(team)
 */
public void addTeam(Team team) throws IllegalArgumentException, IllegalStateException{
	if(! canHaveAsTeam(team))
		throw new IllegalArgumentException(team+" is not a valid team.");
	if ((team.getWorld()!=this)||(hasTeam(team)))
		throw new IllegalStateException();
	this.team.add(team);
	
}
/**
 * counts the number of teams
 * @return the number of teams there are in team
 * 	/team.size()
 */
 public int numberOfTeams() {
	 return team.size();
 }
 /**
  * counts the number of worms
  * @return the number of worms there are in CollectionWorms
  * 	/CollectionWorms.size()
  */
 public int numberOfWorms() {
	 return CollectionWorms.size();
 }
  /**
   * counts the number of food
   * @return the number of food there are in foods
   * 	/foods.size()
   */
 public int numberOfFood() {
	 return foods.size();
 }


public Projectile getActiveProjectile() {
	return this.activeProjectile();
}
public Projectile activeProjectile(){
	Projectile projectile =this.activeWorm().projectile;
	return projectile;
}
//niet juist denk ik
public void addProjectile(Projectile givenProjectile){
	Projectile Newprojectile = new Projectile(this,this.activeWorm.getPosition().getPositionX(),this.getActiveWorm().getPosition().getPositionY());
	
	
}
public String getWinner(){
	if ((finished) && (team.size() == 1) && this.CollectionWorms.size()==1)
		return (this.getTeam().getName());
	if (finished && team.size() == 0 && this.CollectionWorms.size()==1)
		return (activeWorm.getNaming());
	return null;
}
}


