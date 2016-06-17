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
 * This class stores the annotations specific to A* search for Nodes in a map.
 * 
 * @author Abhijeet Anand (<a href="mailto:abhijeet.anand@rmit.edu.au">abhijeet
 *         [dot] anand [at] rmit [dot] edu [dot] au</a>)
 * 
 * @deprecated This class has been deprecated since version 0.2
 */
public class AStarSearchNode extends SearchNode {
    
    /**
     * The f(s) value of a Node as defined by A-Star search and its extensions.
     */
    private float m_f = 0;
    /**
     * The g(s) value of a Node as defined by A-Star search and its extensions.
     */
    private float m_g = Float.POSITIVE_INFINITY;
    /**
     * The h(s), heuristic value of this node
     */
    private float m_h = 0;
    
    /*
     * =======================================================================*
     * ----------------------------- CONSTRUCTORS ----------------------------*
     * =======================================================================*
     */
    /**
     * @param node
     */
    public AStarSearchNode(State node) {
        super(node);
        // TODO Auto-generated constructor stub
    }
    
    /*
     * =======================================================================*
     * --------------------------- ACCESSOR METHODS --------------------------*
     * =======================================================================*
     */
    public float f() {
        return m_f;
    }
    
    public float g() {
        return m_g;
    }
    
    public float h() {
        return m_h;
    }
    
    /*
     * =======================================================================*
     * --------------------------- MUTATOR METHODS ---------------------------*
     * =======================================================================*
     */
    public void setF(float f) {
        m_f = f;
    }
    
    public void setG(float g) {
        m_g = g;
    }
    
    public void setH(float h) {
        m_h = h;
    }
    
    /*
     * =======================================================================*
     * --------------------- OVERRIDDEN INTERFACE METHODS --------------------*
     * =======================================================================*
     */
    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AStarSearchNode o) {
        if (o == null) {
            throw new NullPointerException(this.toString()
                    + " cannot be compared to " + o);
        } else {
            if (m_f < o.m_f) {
                return -1;
            } else if (m_f == o.m_f) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    /*
     * (non-Javadoc)
     * @see
     * au.rmit.ract.planning.pathplanning.entity.SearchNode#toString()
     */
    @Override
    public String toString() {
        String content = "";
        content += "Node=" + m_node + ",F=" + m_f + ",G=" + m_g + ",H=" + m_h;
        return content;
    }
    
    /*
     * =======================================================================*
     * ----------------------------- INNER CLASS -----------------------------*
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

    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */

}
