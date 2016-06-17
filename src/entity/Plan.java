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

/**
 * This is an interface which defines the basic requriements of the path
 * returned by any path planning algorithm such that the client application can
 * easily extract the information in the format it understands. Any class
 * implementing this interface should be immutable once returned by the planning
 * algorithm, such that client applications are not able to modify it once a
 * path has been found and created.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 */
public abstract class Plan {
    
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
     * @return The length of the path in terms of number of steps.
     */
    public abstract int getLength();
    
    
    /**
     * @return The the current step number in the plan
     */
    public abstract int getCurrentStepNo();
    
    /**
     * Set the current step.
     * 
     * @param id
     * @return step number to set.
     */
    public abstract void setCurrentStep(int id);

    /**
     * Return the Node corressponding to the specified step in the path.
     * 
     * @param id
     * @return Node for this step.
     */
    public abstract State getStep(int id);
    
    /**
     * Iterates over the path and returns the next step incementally.
     * 
     * @return Node for the next step.
     */
    public abstract State getNextStep();
    
    /**
     * @return Total cost of this path.
     */
    public abstract float getCost();
    
    /**
     * Checks whether the node lies in the current path.
     * 
     * @param wayPoint
     * @return true if the node is in the path; <br>
     *         false otherwise.
     */
    public abstract boolean contains(State wayPoint);
    
    /**
     * Returns the step number corresponding to the provided node, iff the node is in the current
     * path.
     * 
     * @param waypoint
     * @return The step id.
     */
    public abstract int getStepId(State waypoint);
    
    /**
     * Reset the pointer to the current step to the beginning of the path, such that the next call
     * to getNextStep returns Nodes from the beginning of the path.
     * 
     * @return true if the iterator on this path was successfully reset to the start; <br>
     *         false
     *         otherwise.
     */
    public abstract boolean resetSteps();
    
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
