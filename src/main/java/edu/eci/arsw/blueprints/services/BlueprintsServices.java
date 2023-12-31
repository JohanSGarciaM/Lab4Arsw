/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.filters.FiltersPersistence;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp;
    
    @Autowired
    FiltersPersistence fpp;
    
    /*
     * @param the blueprint to be registered
     * @throws BlueprintPersistenceException if the blueprint is not compatible
     */
    
    public void registerBlueprint(Blueprint bp) throws BlueprintPersistenceException{
    	try{
    		bpp.saveBlueprint(bp);
    	}catch (BlueprintPersistenceException e) {
    		throw new BlueprintPersistenceException(e.getMessage());
    	}
    }


	/*
	 * @return the collections of existing blueprints
	 */
    
    public HashSet<Blueprint> getAllBlueprints() throws BlueprintNotFoundException{
        return bpp.getBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
    	try {
    		return bpp.getBlueprint(author,name);
    	}catch (BlueprintNotFoundException e) {
    		throw new BlueprintNotFoundException(e.getMessage());
    	}
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        try {
        	return bpp.getBlueprintByAuthor(author);
        }catch(BlueprintNotFoundException e) {
        	throw new BlueprintNotFoundException(e.getMessage());
        }
    }
    
    
    
}
