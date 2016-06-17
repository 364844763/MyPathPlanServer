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
 * A distance based heuristics which is mostly applicable in geometric world.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 * 
 */
public interface DistanceHeuristics extends Heuristics {
    
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

    /*
     * =======================================================================*
     * ---------------------------- STATIC METHODS ---------------------------*
     * =======================================================================*
     */

    /*
     * =======================================================================*
     * ---------------------------- PUBLIC METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * The heuristic cost between two nodes in a map. A heuristics cost is an approximation of cost
     * of traversing from the current node to the target node. This value is usually calculated
     * between the current node and the goal node.
     * 
     * @param map
     *            {@link SearchDomain} to be queried for distance values
     * @param cNode
     *            The current {@link State}
     * @param gNode
     *            The goal {@link State}
     * @return A heuristic cost between the current and goal node.
     */
    public float h(SearchDomain map, State cNode, State gNode);
    
    /**
     * Updates the heuristic cost between two nodes in a map. A heuristics cost is an approximation
     * of cost of traversing from the current node to the target node. This value is usually
     * calculated/updated between the current node and the goal node. This method in particular is
     * provided for the use of learning real time algorithms, such as LRTA*, LSS-LRTA* and others,
     * which update the heuristic value as they learn more about the topology of the map.
     * 
     * @param map
     *            {@link SearchDomain} to be queried for updating the distance value
     * @param cNode
     *            The current {@link State}
     * @param gNode
     *            The goal {@link State}
     * @param hModifier
     *            The value by which the original heuristics of the current node should be modified
     * @return true if the map successfully updates the h-value; <br>
     *         false otherwise.
     */
    public boolean updateH(SearchDomain map, State cNode, State gNode, float hModifier);
    
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
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */

}
