/**
 * Copyright 2010 Vivin Suresh Paliath
 * Distributed under the BSD License
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tree<T> {
    
    private TreeNode<T> root;
    
    public Tree() {
        super();
    }
    
    public TreeNode<T> getRoot() {
        return this.root;
    }
    
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }
    
    public int getNumberOfNodes() {
        int numberOfNodes = 0;
        
        if (root != null) {
            numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; // 1 for the
            // root!
        }
        
        return numberOfNodes;
    }
    
    private int auxiliaryGetNumberOfNodes(TreeNode<T> node) {
        int numberOfNodes = node.getNumberOfChildren();
        
        for (TreeNode<T> child : node.getChildren()) {
            numberOfNodes += auxiliaryGetNumberOfNodes(child);
        }
        
        return numberOfNodes;
    }
    
    public boolean exists(TreeNode<T> nodeToFind) {
        return (find(nodeToFind) != null);
    }
    
    public TreeNode<T> find(TreeNode<T> nodeToFind) {
        TreeNode<T> returnNode = null;
        
        if (root != null) {
            returnNode = auxiliaryFind(root, nodeToFind);
        }
        
        return returnNode;
    }
    
    private TreeNode<T> auxiliaryFind(TreeNode<T> currentNode,
            TreeNode<T> nodeToFind) {
        TreeNode<T> returnNode = null;
        int i = 0;
        
        if (currentNode.equals(nodeToFind)) {
            returnNode = currentNode;
        }

        else if (currentNode.hasChildren()) {
            i = 0;
            while (returnNode == null && i < currentNode.getNumberOfChildren()) {
                returnNode = auxiliaryFind(currentNode.getChildAt(i),
                        nodeToFind);
                i++;
            }
        }
        
        return returnNode;
    }
    
    public boolean isEmpty() {
        return (root == null);
    }
    
    public List<TreeNode<T>> build(TreeTraversalOrderEnum traversalOrder) {
        List<TreeNode<T>> returnList = null;
        
        if (root != null) {
            returnList = build(root, traversalOrder);
        }
        
        return returnList;
    }
    
    public List<TreeNode<T>> build(TreeNode<T> node,
            TreeTraversalOrderEnum traversalOrder) {
        List<TreeNode<T>> traversalResult = new ArrayList<TreeNode<T>>();
        
        if (traversalOrder == TreeTraversalOrderEnum.PRE_ORDER) {
            buildPreOrder(node, traversalResult);
        }

        else if (traversalOrder == TreeTraversalOrderEnum.POST_ORDER) {
            buildPostOrder(node, traversalResult);
        }
        
        return traversalResult;
    }
    
    private void buildPreOrder(TreeNode<T> node,
            List<TreeNode<T>> traversalResult) {
        traversalResult.add(node);
        
        for (TreeNode<T> child : node.getChildren()) {
            buildPreOrder(child, traversalResult);
        }
    }
    
    private void buildPostOrder(TreeNode<T> node,
            List<TreeNode<T>> traversalResult) {
        for (TreeNode<T> child : node.getChildren()) {
            buildPostOrder(child, traversalResult);
        }
        
        traversalResult.add(node);
    }
    
    public Map<TreeNode<T>, Integer> buildWithDepth(
            TreeTraversalOrderEnum traversalOrder) {
        Map<TreeNode<T>, Integer> returnMap = null;
        
        if (root != null) {
            returnMap = buildWithDepth(root, traversalOrder);
        }
        
        return returnMap;
    }
    
    public Map<TreeNode<T>, Integer> buildWithDepth(TreeNode<T> node,
            TreeTraversalOrderEnum traversalOrder) {
        Map<TreeNode<T>, Integer> traversalResult = new LinkedHashMap<TreeNode<T>, Integer>();
        
        if (traversalOrder == TreeTraversalOrderEnum.PRE_ORDER) {
            buildPreOrderWithDepth(node, traversalResult, 0);
        }

        else if (traversalOrder == TreeTraversalOrderEnum.POST_ORDER) {
            buildPostOrderWithDepth(node, traversalResult, 0);
        }
        
        return traversalResult;
    }
    
    private void buildPreOrderWithDepth(TreeNode<T> node,
            Map<TreeNode<T>, Integer> traversalResult, int depth) {
        traversalResult.put(node, depth);
        
        for (TreeNode<T> child : node.getChildren()) {
            buildPreOrderWithDepth(child, traversalResult, depth + 1);
        }
    }
    
    private void buildPostOrderWithDepth(TreeNode<T> node,
            Map<TreeNode<T>, Integer> traversalResult, int depth) {
        for (TreeNode<T> child : node.getChildren()) {
            buildPostOrderWithDepth(child, traversalResult, depth + 1);
        }
        
        traversalResult.put(node, depth);
    }
    
    public String toString() {
        /*
         * We're going to assume a pre-order traversal by default
         */

        String stringRepresentation = "";
        
        if (root != null) {
            stringRepresentation = build(TreeTraversalOrderEnum.PRE_ORDER)
                    .toString();
            
        }
        
        return stringRepresentation;
    }
    
    public String toStringWithDepth() {
        /*
         * We're going to assume a pre-order traversal by default
         */

        String stringRepresentation = "";
        
        if (root != null) {
            stringRepresentation = buildWithDepth(
                    TreeTraversalOrderEnum.PRE_ORDER).toString();
        }
        
        return stringRepresentation;
    }
    
    /**
     * Removes all of the elements from this tree. The tree will be
     * empty after this call returns with no root.
     */
    public void clear() {
        if (root != null) {
            for (TreeNode<T> node : build(TreeTraversalOrderEnum.PRE_ORDER)) {
                node.removeChildren();
                // node = null;
            }
        }
    }
}
