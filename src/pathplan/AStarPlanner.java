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
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package pathplan;

import entity.*;
import pathplan.heuristics.DistanceHeuristics;
import pathplan.heuristics.Heuristics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * This class implements the classic A* algorithm developed by Hart et. al. in 1968.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 */
public class AStarPlanner implements PathPlanner {
    
    private PriorityQueue<SearchNode> m_openList             = null;
    private HashMap<State, SearchNode> m_closedListHashMap    = null;
    private DistanceHeuristics        m_heuristics           = null;
    
    /**
     * This is the super list containing all the generated {@link SearchNode}s
     * so far.
     */
    private HashMap<State, SearchNode> m_allSNodesListHashMap = null;
    private static final int          BUCKET_SIZE            = 50;
    /**
     * List of all the expanded nodes in a search iteration.
     */
    private ArrayList<State>           m_expandedNodes        = null;
    
    private static final int          F                      = /* "f" */0;
    // private static final float DEF_F = 0;
    private static final int          G                      = /* "g" */1;
    private static final float        DEF_G                  = Float.POSITIVE_INFINITY;
    private static final int          H                      = /* "h" */2;
    private static final float        DEF_H                  = 0;
    
    private float Weight = 1;   // current weight of WA*
    
    
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


    /**
     * Creates an Weighted A-Star Path Planner based on a heuristic and a weight
     */
    public AStarPlanner(DistanceHeuristics h, float Weight) {
        m_openList = new PriorityQueue<SearchNode>(11,
                new Comparator<SearchNode>() {
                    
                    @Override
                    public int compare(SearchNode sn1, SearchNode sn2) {
                        if (sn1 != null && sn2 != null) {
                            if (sn1.get(F) < sn2.get(F)) {
                                return -1;
                            } else if (sn1.get(F) == sn2.get(F)) { // Break ties
                                if (sn1.get(G) < sn2.get(G)) {
                                    return -1;
                                }
                                if (sn1.get(G) > sn2.get(G)) {
                                    return 1;
                                }
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                        throw new NullPointerException();
                    }
                });
        
        m_closedListHashMap = new HashMap<State, SearchNode>(BUCKET_SIZE);
        m_allSNodesListHashMap = new HashMap<State, SearchNode>(BUCKET_SIZE);
        m_expandedNodes = new ArrayList<State>();
        m_heuristics = h;
        
        this.Weight = Weight;
    }

    /**
     * Creates a classical A-Star Path Planner based on a heuristic
     */
    public AStarPlanner(DistanceHeuristics h) {
        this(h, 1F);
    }
    
    public void setWeight(float Weight) {
        this.Weight = Weight;
    }
    
    public float getWeight() {
        return this.Weight;
    }
    
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
     * au.rmit.ract.planning.pathplanning.ai.PathPlanner#findPath(au.
     * edu.rmit.cs.ract.planning.pathplanning.data.SearchDomain,
     * au.rmit.ract.planning.pathplanning.entity.MyNode,
     * au.rmit.ract.planning.pathplanning.entity.MyNode)
     */
    @Override
    public Plan findPath(SearchDomain map, State sNode, State tNode) {
        // Date inTime = Calendar.getInstance().getTime();
        // If the destination is not traversible, there can be no path. Same applies to the start
        // node.
        if (map.isBlocked(sNode) || map.isBlocked(tNode)) {
            // Date outTime = Calendar.getInstance().getTime();
            // System.out.println("Time Taken: ASTAR: " + (outTime.getTime() - inTime.getTime()));
            return null;
        }
        
        /**
         * Initial state for A*. The closed list is empty. Only the starting
         * node is in the open list and its cost is zero, i.e. we're already
         * there
         */
        initialise();
        // Create and initialise SearchNodes for start and end node.
        SearchNode sSNode = newSearchNode(sNode);
        SearchNode tSNode = newSearchNode(tNode);
        
        // Annotate the start node and put it in the open list.
        sSNode.set(G, 0);
        sSNode.set(H, m_heuristics.h(map, sNode, tNode)); // Heuristics is h
        sSNode.set(F, sSNode.get(G) + sSNode.get(H)); // Total f-value = g + h
        m_openList.add(sSNode);
        
        tSNode.setParent(null);
        
        // Until the target node is reached
        while (m_openList.size() != 0) {
            // Pull out the top MyNode from the open list, which is most likely to
            // be the next step based on the heuristics.
            SearchNode currentSNode = m_openList.peek();
            State currentNode = currentSNode.getNode();
            
            // Terminate the search if the target node was found, else continue
            // searching
            if (currentSNode == null || currentSNode.equals(tSNode)) {
                break;
            }
            
            m_openList.remove(currentSNode);
            m_closedListHashMap.put(currentNode, currentSNode);
            m_expandedNodes.add(currentNode);
            
            // Now begin searching through all the successors of the current
            // node and evaluate them as candidates for next step, putting them
            // all in the Open List if they have not been visited already.
            ArrayList<State> neighbours = map.getSuccessors(currentSNode.getNode());
            for (State neighbourNode : neighbours) {
                SearchNode neighbourSNode = newSearchNode(neighbourNode);
                float nextStepG = currentSNode.get(G) + map.cost(currentNode, neighbourNode);
                
                if (nextStepG < neighbourSNode.get(G)) {
                    // There's a better way
                    neighbourSNode.setParent(null);
                    m_closedListHashMap.remove(neighbourNode);
                    m_openList.remove(neighbourSNode);
                    
                    // Process the node
                    neighbourSNode.set(G, nextStepG);
                    neighbourSNode.set(H, m_heuristics.h(map, neighbourNode, tNode));
                    neighbourSNode.set(F, nextStepG + Weight*neighbourSNode.get(H));
                    // if (neighbourSNode.get(F) != Float.POSITIVE_INFINITY) {
                    neighbourSNode.setParent(currentSNode);
                    // }
                    m_openList.add(neighbourSNode);
                }
            } // END foreach Neighbours
            
        } // END while open != EMPTY
        
        // If there was no path found to target, return null
        if (tSNode.getParent() == null) {
            // Date outTime = Calendar.getInstance().getTime();
            // System.out.println("Time Taken: ASTAR: " + (outTime.getTime() - inTime.getTime()));
            return null;
        }
        
        // At this point, a path was defintely found, which means we need to
        // create a Path by traversing through the parent pointers
        ComputedPlan path = new ComputedPlan();
        
        SearchNode target = tSNode;
        while (target != sSNode) {
            path.prependStep(target.getNode());
            target = target.getParent();
        }
        path.prependStep(sNode);
        path.setCost(tSNode.get(G));
        
        // System.gc(); // Free up lost and unused memory
        
        // Date outTime = Calendar.getInstance().getTime();
        // System.out.println("Time Taken: ASTAR: " + (outTime.getTime() - inTime.getTime())); //
        // SOP TimeTaken
        return path;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.ai.PathPlanner#setHeuristics
     * (au.rmit.ract.planning.pathplanning.ai.heuristics.Heuristics)
     */
    @Override
    public boolean setHeuristics(Heuristics heuristics) {
        m_heuristics = DistanceHeuristics.class.cast(heuristics);
        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.ai.PathPlanner#expandedNodes()
     */
    @Override
    public ArrayList<State> expandedNodes() {
        return new ArrayList<State>(m_expandedNodes);
    }
    
    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.ai.PathPlanner#unexpandedNodes()
     */
    @Override
    public ArrayList<State> unexpandedNodes() {
        ArrayList<State> unexpanded = new ArrayList<State>();
        for (SearchNode node : m_openList) {
            unexpanded.add(node.getNode());
        }
        return unexpanded;
    }
    
    /*
     * (non-Javadoc)
     * @see au.rmit.ract.planning.pathplanning.ai.PathPlanner#annotations()
     */
    @Override
    public HashMap<State, String> annotations() {
        HashMap<State, String> annotations = new HashMap<State, String>();
        StringBuilder annotStringBuilder = new StringBuilder();
        
        try {
            for (SearchNode sNode : m_allSNodesListHashMap.values()) {
                annotStringBuilder.append("F:").append(sNode.get(F));
                annotStringBuilder.append(", G:").append(sNode.get(G));
                annotStringBuilder.append(", H:").append(sNode.get(H));
                
                annotations.put(sNode.getNode(), annotStringBuilder.toString());
                annotStringBuilder.delete(0, annotStringBuilder.length());
            }
        } catch (Exception excptn) {
            excptn.printStackTrace();
        }
        return annotations;
    }
    
    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Creates and initialises a new SearchNode for the MyNode provided as
     * parameter. If the node already exists in the super list, returns it
     * instead.
     * 
     * @param node
     * @return A new SearchNode or the one in the super list if created earlier.
     */
    private SearchNode newSearchNode(State node) {
        if (m_allSNodesListHashMap.containsKey(node)) {
            return m_allSNodesListHashMap.get(node);
        } else {
            SearchNode sNode = new SearchNode(node);
            sNode.set(G, DEF_G);
            sNode.set(H, DEF_H);
            sNode.set(F, DEF_G + DEF_H);
            m_allSNodesListHashMap.put(node, sNode);
            return sNode;
        }
    }
    
    /**
     * This method initialises the system; basically clears all the lists.
     */
    private void initialise() {
        // Clear the lists.
        m_closedListHashMap.clear();
        m_openList.clear();
        m_allSNodesListHashMap.clear();
        m_expandedNodes.clear();
        // System.gc();
    }
    
}
