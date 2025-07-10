package com.luhach.algorithms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedList {

    private static final Logger LOG = LoggerFactory.getLogger(LinkedList.class);

    public static Node head;
    public static Node tail;
    public static int size;

    public boolean isEmpty(){return this.head==null?true:false; }

    public void add(Node nextNode) {
        if (head == null && tail == null) {
            this.head = nextNode;
        }else{
            this.tail.next = nextNode;
        }
        this.tail=nextNode;
        this.size++;
        }

        public Node getNodeAt(int index){
            Node next=this.head;
            for(int i=1;i<index;i++){
                next=next.next;
            }
            return next;
        }

        public int[] valueToArray(){
            int[] arr=new int[this.size];
            Node next=this.head;
            int i=0;
            while(next!=null){
//                LOG.info("NextValue{}",next.data);
                arr[i]=next.data;
                next=next.next;
                i++;
            }
            return arr;
        }
    }
