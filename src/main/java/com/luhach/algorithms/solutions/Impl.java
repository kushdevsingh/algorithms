package com.luhach.algorithms.solutions;


import com.luhach.algorithms.amazon.ScoreBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;



@ShellComponent
public class Impl {

    @Autowired
    private Utils utils;

    @Autowired
    private SearchAndSort searchAndSort;

    @Autowired
    private ScoreBoard scoreBoard;

    private static final Logger LOG = LoggerFactory.getLogger(Impl.class);

    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            int secondValue=target-nums[i];
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]==secondValue){
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    @ShellMethod("Get index of the sum of the vale given")
    public String index(@ShellOption(arity=5) int[] nums,
                       int target)
    {
        return  Arrays.toString(this.twoSum(nums,target));

}
    @ShellMethod("Reverse Array")
public String reverseArray(@ShellOption(arity=50) int [] arr){
        int left=0,right=arr.length-1;
        int swap;
        while(left<=right){
            swap=arr[left];
            arr[left]=arr[right];
            arr[right]=swap;
            left++;
            right --;
        }
        return Arrays.toString(arr);
    }

    @ShellMethod("Add two integers together.")
    public String reverseList(@ShellOption(arity=50) int [] arr)  {

        LinkedList newList=new LinkedList();
        for (int i=0;i< arr.length;i++){
            newList.add(new Node(arr[i]));
        }

        Node next=null;
        Node previous=null;
        Node current= newList.head;
        newList.tail=current;
        if(!newList.isEmpty()) {
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            newList.head=previous;
        }
//        LOG.info("headValue:{}, headNodeNext:{} tailValue:{},:getNodeAtIndex2:{}",newList.head.data,newList.head.next.data,newList.tail.data,newList.getNodeAt(2).data);
        return Arrays.toString(newList.valueToArray());
    }

    @ShellMethod("BinarySearch")
    public Number search(@ShellOption(arity=10) int[] a, int find)  {
        return  searchAndSort.binarySearch(a,find);
    }

    @ShellMethod("QuickSort")
    public String qs(@ShellOption(arity=50) int[] a)  {
        return  Arrays.toString(searchAndSort.quickSort(a,0,a.length-1));
    }

    @ShellMethod("Scoreboard")
    public int addPlayer(String playerName,int score)  {
        for(int i=0;i<=1000000;i++){
            scoreBoard.addPlayer(playerName+i,score*100*i).hashCode();
        }
        return 1;
    }

    @ShellMethod("Scoreboard")
    public void topPlayers(int top)  {
        scoreBoard.getTopPlayers(top);
    }

//    @ShellMethod("Graph")
//    public int maxNumber(@ShellOption(arity=10) int[] arr){
//        LinkedList newList=new LinkedList();
//        for (int i=0;i< arr.length;i++){
//            newList.add(new Node(arr[i]));
//        }
//    }

}
