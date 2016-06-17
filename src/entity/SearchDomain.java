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
package entity;


import bean.NewNode;

import java.util.ArrayList;

/**
 * A SearchDomain interface represents the search space graph as an abstract view
 * of the environment/domain. This interface defines the basic requirements for the client
 * application to provide domain dependent information to the planning algorithms. <br>
 * Although the actual domain information contained within the application could be stored in any
 * form (e.g., some form of data structure stored locally within the application, an object over the
 * network, or even a database), SearchDomain interface requires the application to “export” such
 * data in certain uniform format.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 * 
 */
public abstract class SearchDomain {
    
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
     * Returns the successor nodes/states of the current node.
     * 
     * @param <T>
     *            sub-class of Node
     * @param cNode
     *            The current node
     * @return An ArrayList of Nodes, which contains the successors of the current node.
     */
    public abstract <T extends State> ArrayList<State> getSuccessors(State cNode);

    
    /**
     * Returns the predecessor nodes/states of the current node.
     * 
     * @param <T>
     *            sub-class of Node
     * @param cNode
     *            The current node
     * @return An ArrayList of Nodes, which contains the predecessors of the current node.
     */
    public  abstract <T extends State> ArrayList<State> getPredecessors(State cNode);
    
    /**
     * Returns the edges for which the cost of transition changed between the last and the current
     * path planning iteration.
     * 
     * @param <T>
     *            sub-class of Edge
     * @return ArrayList of Edges.
     */
    public abstract <T extends Edge> ArrayList<T> getChangedEdges();
    
    /**
     * The cost of transition/traversal between the two provided nodes.
     * 
     * @param sNode
     *            start node
     * @param tNode
     *            target node
     * @return cost of traversal
     */
    public abstract float cost(State sNode, State tNode);
    
    /**
     * The heuristic cost between the two provided nodes.
     * 
     * @param sNode
     *            start node
     * @param tNode
     *            target node
     * @return heuristic cost of traversal
     */
    public abstract float hCost(State sNode, State tNode);
    
    /**
     * Whether the node is blocked and hence cannot be stepped into.
     * 
     * @param cNode
     *            The node for which to check the blockage status
     * @return true if the node is blocked; <br>
     *         false otherwise.
     */
    public abstract boolean isBlocked(State cNode);
    

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
