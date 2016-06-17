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
package pathplan;


import entity.Plan;
import entity.SearchDomain;
import entity.State;
import pathplan.heuristics.Heuristics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface provides a common view of almost all the path planning algorithms. To add an
 * implementation of an algorithm in this library, the relevant class should implement this
 * interface. A client application must call methods from this interface only. However, a class
 * which implements this interface, could have its own public methods, if needed.
 * 
 * @author abhijeet
 */
public interface PathPlanner {
    
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
     * Find a path from a starting node to the target node within a given map. A client application
     * should call this method whenever it needs to find a path, irrespective of the iteration
     * number.
     * 
     * @param map
     *            The {@link SearchDomain} to be searched.
     * @param sNode
     *            Start {@link State}
     * @param tNode
     *            Target {@link State}
     * @return Returns a {@link Plan} from given start {@link State} to the
     *         target MyNode.
     */
    public Plan findPath(SearchDomain map, State sNode, State tNode);
    
    /**
     * Set the <code>{@link Heuristics}</code> to be used with this search. This
     * method is experimental and can be removed in future version if its
     * existence is redundant.
     * 
     * @param heuristics
     * @return true if the given heuristics can be set and is applicable.
     */
    public boolean setHeuristics(Heuristics heuristics);
    
    /**
     * Search algorithms would expand certain nodes while searching for a path.
     * While some algorithms would explore the whole search space, others would
     * only expand a limited set of nodes. This method should return the
     * expanded nodes in the current search iteration.
     * 
     * @return an ArrayList of MyNode which were expanded during the current
     *         search iteration.
     */
    public ArrayList<State> expandedNodes();
    
    /**
     * Search algorithms would expand certain nodes while searching for a path.
     * While some algorithms would explore the whole search space, others would
     * only expand a limited set of nodes. However, not all the nodes in the search space are
     * expanded though they are prospective frontiers in search. This method should return those
     * unexpanded nodes in the current search iteration.
     * 
     * @return an ArrayList of MyNode which were not expanded during the current
     *         search iteration.
     */
    public ArrayList<State> unexpandedNodes();
    
    /**
     * Search algorithms usually annotate the nodes while expanding/generating them while searching
     * for a path. These annotations are used internally for determining a shortest path by the
     * algorithms. However, if the client needs to know these values it must gain access to them via
     * these method which returns the annotation as a string.
     * 
     * @return a HashMap of annotations formatted as Strings associated with every MyNode.
     */
    public HashMap<State, String> annotations();
    
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
