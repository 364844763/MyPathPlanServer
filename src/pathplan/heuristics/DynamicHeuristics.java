/**
 * RACT-PAL (RACT Path-Planning Algorithms Library) - A Library of Path Planning
 * Algorithms
 * 
 * Copyright (C) 2010 Abhijeet Anand, RACT - RMIT Agent Contest Team, School of
 * Computer Science and Information Technology,
 * RMIT University, Melbourne VIC 3000.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package pathplan.heuristics;


import entity.SearchDomain;
import entity.State;

import java.util.HashMap;

/**
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet [dot] anand [at]
 *         rmit [dot] edu [dot] au</a>)
 * 
 */
public class DynamicHeuristics implements DistanceHeuristics {
    
    private static DynamicHeuristics            _instance               = null;
    
    private HashMap<State, HashMap<State, Float>> goalBasedHeuristicStore = new HashMap<State, HashMap<State, Float>>();
    
    /*
     * =======================================================================*
     * ----------------------------- INNER CLASS -----------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * ----------------------------- CONSTRUCTORS ----------------------------*
     * =======================================================================*
     */
    public DynamicHeuristics() {
        
    }
    
    /*
     * =======================================================================*
     * ---------------------------- STATIC METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Returns a reference to the singleton object of this class.
     * 
     * @return {@link DynamicHeuristics}
     */
    public static DynamicHeuristics instance() {
        if (_instance == null) {
            _instance = new DynamicHeuristics();
        }
        return _instance;
    }
    
    /*
     * =======================================================================*
     * ---------------------------- PUBLIC METHODS ---------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * --------------------------- ACCESSOR METHODS --------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * --------------------------- MUTATOR METHODS ---------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * --------------------- OVERRIDDEN INTERFACE METHODS --------------------*
     * =======================================================================*
     */
    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.ai.heuristics.DistanceHeuristics#h(au.rmit.ract.planning
     * .pathplanning.entity.SearchDomain, au.rmit.ract.planning.pathplanning.entity.MyNode,
     * au.rmit.ract.planning.pathplanning.entity.MyNode)
     */
    @Override
    public float h(SearchDomain map, State cNode, State gNode) {
        HashMap<State, Float> hStore = goalBasedHeuristicStore.get(gNode);
        if (hStore != null && hStore.containsKey(cNode)) {
            return hStore.get(cNode);
        }
        return map.hCost(cNode, gNode);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.ai.heuristics.DistanceHeuristics#updateH(au.rmit.ract.
     * planning.pathplanning.entity.SearchDomain, au.rmit.ract.planning.pathplanning.entity.MyNode,
     * au.rmit.ract.planning.pathplanning.entity.MyNode, float)
     */
    @Override
    public boolean updateH(SearchDomain map, State cNode, State gNode, float hModifier) {
        HashMap<State, Float> hStore = goalBasedHeuristicStore.get(gNode);
        
        if (hStore == null) {
            // Create a new store and add it to parent store.
            HashMap<State, Float> tempHStore = new HashMap<State, Float>();
            tempHStore.put(cNode, hModifier);
            goalBasedHeuristicStore.put(gNode, tempHStore);
        } else {
            // Update the value in the store.
            hStore.put(cNode, hModifier);
        }
        return false;
    }
    
    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */

}
