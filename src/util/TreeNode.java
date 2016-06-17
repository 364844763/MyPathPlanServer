/**
 * Copyright 2010 Visin Suresh Paliath
 * Distributed under the BSD license
 * 
 * Copyright (c) 2010, Vivin Suresh Paliath
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the <ORGANIZATION> nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * The original source could be found here:
 * http://vivin.net/2010/01/30/generic-n-ary-tree-in-java/
 */

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

package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreeNode<T> {
    
    public T                      data;
    public ArrayList<TreeNode<T>> children;
    public TreeNode<T>            parent = null;
    
    public TreeNode() {
        super();
        children = new ArrayList<TreeNode<T>>();
    }
    
    public TreeNode(T data) {
        this();
        setData(data);
    }
    
    public List<TreeNode<T>> getChildren() {
        // To ensure immutability, return new objects
        return new ArrayList<TreeNode<T>>(this.children);
    }
    
    public int getNumberOfChildren() {
        return getChildren().size();
    }
    
    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }
    
    public void setChildren(List<TreeNode<T>> children) {
        // this.children = children;
        this.children = new ArrayList<TreeNode<T>>(children);
    }
    
    public void addChild(TreeNode<T> child) {
        child.parent = this;
        children.add(child);
    }
    
    public void addChildAt(int index, TreeNode<T> child)
            throws IndexOutOfBoundsException {
        children.add(index, child);
    }
    
    public void removeChildren() {
        this.children = new ArrayList<TreeNode<T>>();
    }
    
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
    
    public TreeNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }
    
    public T getData() {
        return this.data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public String toString() {
        return getData().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TreeNode<?> other = (TreeNode<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }
    
    public String toStringVerbose() {
        String stringRepresentation = getData().toString() + ":[";
        
        for (TreeNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }
        
        // Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's
        // retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);
        
        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";
        
        return stringRepresentation;
    }
}
