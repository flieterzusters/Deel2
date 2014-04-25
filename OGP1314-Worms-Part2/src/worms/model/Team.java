package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

import java.util.ArrayList;
import java.util.List;

public class Team {
	public Team(String name){
		this.setName(name);
	}


/**
 * Set the name of the team.
 * @param GivenName
 * 		 
 *
 * @throws IllegalArgumentException
 * 		   The given name has to be valid.
 *           | ValidName(GivenName) == FALSE;
 *         
 * @post  The name of the team is equal to the given name.
 *			 | new.getName() == GivenName;
 * 
 */
public void setName(String GivenName) throws IllegalArgumentException {
		if (isValidName(GivenName))
			{this.name=GivenName;}

		else 
			throw new IllegalArgumentException("This isn't a valid name.");	
}

private String name;


/**
 * Check whether the name for the worm is valid.
 * @param name
 * 		  The name for the team that has to be checked
 * 
 * @return TRUE if the name is valid, False if the name is not valid.
 * 		 
 */
@Model
public boolean isValidName(String name) {

	if (name.length() < 2)
		{return false ;}
	else if (!Character.isUpperCase(name.charAt(0)))
		{return false ;}

	else if (name.matches("[a-zA-Z'\" ]*"))
		{return true ;}
	else
	{ return false;  }
}

/**
 * Get the name of the worm.
 * @return the name of the worm.
 */
@Basic
public String getName() {
	return name; 
}
/**
 * assignes the world to the team
 * @param world the given world that need to be assigned to the team
 * @throws IllegalArgumentException if the world is not valid to assign
 * @throws IllegalStateException if the world provided is null
 * @effect the world is assigned to the team
 * 	/this.world=world
 */
public void setWorld(World world) throws IllegalArgumentException, IllegalStateException{
	if (! possibleWorld(world))
		throw new IllegalArgumentException(world+" is not valid to world");
	if (hasWorld())
		throw new IllegalStateException();
	this.world=world;
	}

/**
 *  looks if the world provided is possible to assigne to a team
 * @param world the given world to check
 * @return true if the world is not null and the word isn't already assigend to this team.
 */
public boolean possibleWorld (World world){
	return (world != null) && (!world.hasTeam(this));
}
/**
 * looks if the team has a world assiged
 * @return true if the assigend world is not null
 *  /!(getWorld()== null)
 */
public boolean hasWorld(){
	return(!(getWorld()== null));
}
/**
 * removes the world that is assigend to the team
 * @throws NullPointerException if there isn't a world assigned to the team.
 * @effect the world is removed from the team
 * 	/this.world = null
 * @effect the team is removed from the world
 * 	/teamWorld.removeTeam(this)
 */
public void removeWorld() throws NullPointerException{
	if (! hasWorld())
		 throw new NullPointerException();
	World teamWorld = getWorld();			//weglaten?
	this.world = null;
	teamWorld.removeTeam(this);
}
/**
 * a World to assigen a world to a team
 */
private World world;
/**
 * getter to get the world that is assigend to a team
 * @return the word that is assigend to a team
 */
public World getWorld(){
	return this.world;
	}
/**
 * looks if it is possible to assign the provided worm to a team
 * @param worm the worm that is needed to be checked
 * @return true if the worm is not null
 * 	/worm !=null
 */
public boolean PossibleWorm(Worm worm){
	return(worm !=null);
}
/**
 * looks if htis worm is assiged to the teams collection of worms
 * @param worm the worm that is need to be checked
 * @return true if the worm is in  collectionWorms of team
 */
public boolean hasWorm(Worm worm){
	return CollectionWorms.contains(worm);
}
/**
 * method to add a worm to a team
 * @param worm the worm that is need to be added
 * @throws IllegalArgumentException if it is not possible to assign the worm to the team
 * 	/!PossibleWorm(worm)
 * @throws IllegalStateException if the worm is not assiged to the same world as the team 
 * or if the worm is already assiged to the team.
 * @effect the worm is added to the team
 * 	/CollectionWorms.add(worm
 */
public void addWorm(Worm worm) throws IllegalArgumentException, IllegalStateException{
	if(!PossibleWorm(worm))
		throw new IllegalArgumentException(worm+" is not assigned");
	if ((worm.getWorld() != this.getWorld()) || (hasWorm(worm)))
		throw new IllegalStateException();
	CollectionWorms.add(worm);
}
/**
 * method to remove a worm from a team
 * @param worm the worm that need to be removed
 * @throws IllegalArgumentException if theworm is no assiged to this team and the worm is not null.
 * @throws IllegalStateException if the worm is not assiged to a team
 * @effect the worm is removed from the list of worms that is assigned to this team.
 * 	/CollectionWorms.remove(worm)
 */
public void removeWorm(Worm worm) throws IllegalArgumentException,IllegalStateException{
if ((worm == null) || (! hasWorm(worm)))
	throw new IllegalArgumentException(worm+" is not assigned");
if (worm.hasTeam())
	throw new IllegalStateException();
CollectionWorms.remove(worm);
}
/**
 * gives a list of worms that is assiged to this team
 * @return a list of worms that are assigned to this team
 */
public List<Worm> getWormCollection(){
	return this.CollectionWorms;
}
/**
 * list to stock all worms that are assigned to this team
 */
private final List<Worm>CollectionWorms = new ArrayList<Worm>();







}



