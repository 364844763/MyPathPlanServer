/**
 * RACT-PAL (RACT Path-Planning Algorithms Library) - A Library of Path Planning
 * Algorithms
 * <p>
 * Copyright (C) 2010 Abhijeet Anand, RACT - RMIT Agent Contest Team, School of
 * Computer Science and Information Technology,
 * RMIT University, Melbourne VIC 3000.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package entity;

import java.util.ArrayList;

/**
 * The shortest path computed by any path planning algorithm. This class stores the nodes in order
 * in which they are added. This class is immutable and does not allow modifications to the path
 * once it has been created (all the waypoints/nodes in a path have been added and the cost has been
 * set).
 *
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 */
public class ComputedPlan extends Plan {
    private ArrayList<State> waypoints = new ArrayList<State>();
    private float totalCost = 0;
    private boolean costSetOnce = false;

    private int currentIndex = 0;

    /**
     * Append steps/waypoints to this path.
     *
     * @param wayPoint
     * @return The index of this wayPoint if it was added successfully; <br>
     * -1 otherwise.
     */
    public int appendStep(State wayPoint) {
        if (!costSetOnce && waypoints.add(wayPoint)) {
            return waypoints.indexOf(wayPoint);
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.entity.Path#contains(au.edu
     * .rmit.cs.ract.planning.pathplanning.entity.Node)
     */
    @Override
    public boolean contains(State wayPoint) {
        return waypoints.contains(wayPoint);
    }

    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.entity.Path#getCost()
     */
    @Override
    public float getCost() {
        return totalCost;
    }

    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.entity.Path#getLength()
     */
    @Override
    public int getLength() {
        return waypoints.size();
    }


    /**
     * Get the current index in the plan
     *
     * @return current index in the plan
     */
    @Override
    public int getCurrentStepNo() {
        return currentIndex;
    }

    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.entity.Path#getNextStep()
     */
    @Override
    public State getNextStep() {
//        return waypoints.get(currentIndex++);

        if (getLength() > currentIndex)
            return waypoints.get(currentIndex++);
        else
            return null;

    }

    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.entity.Path#getStep(int)
     */
    @Override
    public void setCurrentStep(int id) {
        currentIndex = id;
    }

    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.entity.Path#getStep(int)
     */
    @Override
    public State getStep(int id) {
        return waypoints.get(id);
    }


    @Override
    public int getStepId(State waypoint) {
        return waypoints.indexOf(waypoint);
    }


    public int prependStep(State wayPoint) {
        if (!costSetOnce) {
            waypoints.add(0, wayPoint);
            return 0;
        }
        return -1;
    }


    @Override
    public boolean resetSteps() {
        currentIndex = 0;
        return true;
    }


    public boolean setCost(float cost) {
        if (!costSetOnce) {
            totalCost = cost;
            costSetOnce = true;
            return costSetOnce;
        }
        return !costSetOnce;
    }

    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        int count = 0;

        stringRep.append("[START");
        for (State node : waypoints) {
            stringRep = (count++ % 10 == 0) ? stringRep.append("\n" + node) : stringRep.append(", " + node);
        }
        stringRep.append("\nGOAL]");

        return stringRep.toString();
    }


}
