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

/**
 * An implementation of {@link DistanceHeuristics} based on Manhattan distance
 * between any two points in a two dimensional space. This is a Singleton class.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 * 
 */
public class ManhattanDistanceHeuristics implements DistanceHeuristics {
    
    private static ManhattanDistanceHeuristics _instance = null;
    
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
    protected ManhattanDistanceHeuristics() {
        // Just an empty constructor as at the time of first creation of the
        // class. This could later be extended/modified to provide proper
        // initialisation of the class, if necessary.
    }
    
    /*
     * =======================================================================*
     * ---------------------------- STATIC METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Returns a reference to the singleton object of this class.
     * 
     * @return {@link ManhattanDistanceHeuristics}
     */
    public static ManhattanDistanceHeuristics instance() {
        if (_instance == null) {
            _instance = new ManhattanDistanceHeuristics();
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
     * au.rmit.ract.planning.pathplanning.ai.heuristics.DistanceHeuristics
     * #h(au.rmit.ract.planning.pathplanning.entity.SearchDomain,
     * au.rmit.ract.planning.pathplanning.entity.MyNode,
     * au.rmit.ract.planning.pathplanning.entity.MyNode)
     */
    @Override
    public float h(SearchDomain map, State cNode, State gNode) {
        return map.hCost(cNode, gNode);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.ai.heuristics.DistanceHeuristics#updateH(au.edu
     * .rmit.cs.ract.planning.pathplanning.entity.SearchDomain,
     * au.rmit.ract.planning.pathplanning.entity.MyNode,
     * au.rmit.ract.planning.pathplanning.entity.MyNode, float)
     */
    @Override
    public boolean updateH(SearchDomain map, State cNode, State gNode, float hModifier) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */

}
