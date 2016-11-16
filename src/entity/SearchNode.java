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
package entity;

import java.util.Arrays;

/**
 * This class is a wrapper around Node interface, which stores useful
 * annotations and parent pointers. The annotations are stored in a growing array of floats,
 * hence providing a flexible data storage. These annotations can be accessed via get(·) and
 * set(·) methods of SearchNode. Though we have separated the data from logic, path planning
 * algorithms usually need to store some algorithm-specific information on each Node they expand.
 * This is accomplished by means of this class.
 *
 */
public class SearchNode {
    
    /**
     * The {@link State} to be annotated
     */
    protected State       m_node          = null;
    /**
     * The parent {@link SearchNode} of this node. This helps in retracing
     * the path found during a search. This requirement of an SearchNode
     * descends from A-Star search type algorithms
     */
    protected SearchNode /* T */m_parent = null;
    
    // protected HashMap<String, Float> annotations;
    
    protected float[]    annotation;
    
    protected int        size            = 5;
    
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
    public SearchNode(State node) {
        m_node = node;
        // annotations = new HashMap<String, Float>();
        annotation = new float[size];
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
    /**
     * @return The parent SearchNode
     */
    public SearchNode/* T */getParent() {
        return m_parent;
    }
    
    /**
     * @return the {@link State} object for this SearchNode
     */
    public State getNode() {
        return m_node;
    }
    
    // public float value(String of) {
    // return annotations.get(of);
    // }
    
    /**
     * The value of the stored annotation at the provided index
     * 
     * @param i
     *            index
     * @return the stored value of the annotation.
     */
    public float get(int i) {
        assert i >= 0 && i < size;
        return annotation[i];
    }
    
    /*
     * =======================================================================*
     * --------------------------- MUTATOR METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Sets the parent node for this node. The parent is set iff this assignment does not result in
     * cyclic linkage. More precisely, A.parent = (B == null)? B: !A.equals(B.parent)? B: A.parent;
     * 
     * @param parent
     */
    public void setParent(SearchNode/* T */parent) {
        m_parent = parent == null ? parent : !this.equals(parent.m_parent) ? parent : m_parent;
    }
    
    /**
     * @param node
     *            the node to set for this {@link SearchNode}
     */
    @SuppressWarnings("unused")
    private void setNode(State node) {
        m_node = node;
    }
    
    // public void value(String of, float value) {
    // annotations.put(of, value);
    // }
    
    /**
     * Set the value of the annotation
     * 
     * @param index
     * @param value
     */
    public void set(int index, float value) {
        assert index >= 0;
        if (index >= size) {
            grow(index + 1);
        }
        annotation[index] = value;
    }
    
    /*
     * =======================================================================*
     * --------------------- OVERRIDDEN INTERFACE METHODS --------------------*
     * =======================================================================*
     */
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if (obj != null) {
        // if (getClass().isAssignableFrom(obj.getClass()))
        // return m_node.equals(getClass().cast(obj).m_node);
        // }
        // return false;
        if (obj != null)
            return m_node.equals(((SearchNode) obj).m_node);
        return false;
    }
    
    @Override
    public int hashCode() {
        return m_node.hashCode();
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public/* abstract */String toString() {
        String content = "";
        content += "Node=" + m_node;
        
        if (m_parent != null) {
            content += "Parent=" + m_parent.m_node;
        } else {
            content += "Parent=" + m_parent;
        }
        
        content += "[" + annotation[0];
        for (int i = 1; i < annotation.length; i++) {
            content += "," + annotation[i];
        }
        content += "]";
        
        return content;
    }
    
    /*
     * =======================================================================*
     * --------------------------- UTILITY METHODS ---------------------------*
     * =======================================================================*
     */
    /**
     * Increases the capacity of the array.
     * 
     * This method has been taken from Sun's implementation of
     * java.util.PriorityQueue
     * 
     * @param minCapacity
     *            the desired minimum capacity
     */
    private void grow(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError("MinCapacity: " + minCapacity);
        int oldCapacity = annotation.length;
        // Double size if small; else grow by 50%
        int newCapacity = ((oldCapacity < 64) ?
                ((oldCapacity + 1) * 2) :
                ((oldCapacity / 2) * 3));
        if (newCapacity < 0) // overflow
            newCapacity = Integer.MAX_VALUE;
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        annotation = Arrays.copyOf(annotation, newCapacity);
        size = annotation.length;
        // System.gc();
    }
    
}
