package com.luhach.algorithms.database;

import java.util.Set;

public class BinaryTreeIndex {

    public class Nodes{
        int key;
        Set<Integer> primaryKey;
        Nodes leftChild;
        Nodes rightChild;

        public Nodes(int key,int primaryKey){
            this.key=key;
            this.primaryKey.add(primaryKey);
            this.leftChild =this.rightChild = null;
        }
    }
}
