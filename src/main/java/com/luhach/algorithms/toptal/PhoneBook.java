package com.luhach.algorithms.toptal;

import com.luhach.algorithms.amazon.ScoreBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneBook {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneBook.class);

    public class Node{
        String number;
        String name;
        Node(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "number='" + number + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public class TreeNode{
        TreeNode[] child;
        boolean isEnd;
        int depth;
        List<Long> numbers = new LinkedList<>();
        TreeNode(){
            this.child = new TreeNode[10];
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "child=" + Arrays.toString(child) +
                    ", isEnd=" + isEnd +
                    '}';
        }
    }

    TreeNode parent = new TreeNode();
    HashMap<Long,Node> contacts=new HashMap<>();
    String[] display;
    Set<Long> found = new HashSet<Long>();
    public void addContact(String name, Long number){

        if(!contacts.containsKey(number)){
            Node newContact =  new Node(name,number.toString());
            contacts.put(number,newContact);
            addNumber(number);
        }
//        LOG.info("Contacts:{}",contacts);
    }

    public void addNumber(Long number){
        String numberString = String.valueOf(number);
        int numLength = numberString.length();
        for (int i =0 ; i< numLength; i++){
            String eachSuffix = numberString.substring(i,numLength);
            addSuffixEntries(eachSuffix,number);
        }
        display = new String [11];
        display[0] = "[--------------ROOT--------------]";
        displayTree(parent);
    }
    public void addSuffixEntries( String numberSuffix , Long number){
//        LOG.info("Adding numberSuffix :{}",numberSuffix);
        if(parent ==null ){
            parent= new TreeNode();
        }
        TreeNode parent = this.parent;
        int depth = this.parent.depth;
        int numLength = numberSuffix.length();
        for (int i =0 ; i< numLength; i++){
            int newNumber = Character.getNumericValue(numberSuffix.charAt(i));
            if(parent.child[newNumber] == null) {
                TreeNode child = new TreeNode();
                parent.child[newNumber] = child;
                parent = child;
            }else{
                parent = parent.child[newNumber];
            }
            parent.depth = depth+1;
            depth=parent.depth;
        }
        parent.isEnd= true;
        if(parent.numbers.isEmpty()){
            List<Long> newContact = new LinkedList<>();
            newContact.add(number);
            parent.numbers = newContact;
        }else{
            parent.numbers.add(number);
        }

    }

    public void display(){
        if(display == null ){
            System.out.println("Nothing to display.");
        }else {
            for (String eachNode : display) {
                System.out.println(eachNode);
            }
        }
    }
    public void findContact(String pattern){
        found.clear();
        TreeNode foundNode = findPatternNode(pattern);
        if(foundNode != null){
            findAll(foundNode);
        }
        if(found !=null){
            Iterator i = found.iterator();
            while(i.hasNext()){
                System.out.println(contacts.get(i.next()).toString());
            }
        }

    }
    public void findAll(TreeNode parentNode){
//        System.out.println("Found at Node" + parentNode.toString());
        if(parentNode !=null){
            for(int i=0; i<=9; i++){
                if(parentNode.child[i] != null) {
                    if (parentNode.child[i].isEnd) {
                        for (Long eachNumber:parentNode.child[i].numbers) {
                            found.add(eachNumber);
//                            System.out.println("Found Contact :" + contacts.get(eachNumber));
                        }
                    }
                    findAll(parentNode.child[i]);
                }
            }
            if(parentNode.isEnd){
                for (Long eachNumber:parentNode.numbers) {
                    found.add(eachNumber);
//                    System.out.println("Found Contact :" + contacts.get(eachNumber));
                }
            }
        }
    }
    public TreeNode findPatternNode(String pattern){
        TreeNode parent = this.parent;
        if(parent !=null ) {
            for (int i = 0; i < pattern.length(); i++) {
                int eachNumber = Character.getNumericValue(pattern.charAt(i));
                if (!isAvailable(parent, eachNumber)) {
//                    System.out.println("Pattern not found.!!!");
                    return null;
                }
                parent = parent.child[eachNumber];
            }
//            System.out.println("Found at Node" + parent.toString());
            return parent;
        }
//        System.out.println("Pattern not found.!!!");
        return null;
    }

    public boolean isAvailable(TreeNode parent, int number){
        if(parent.child[number] != null){return true;}
        return false;
    }
    public void displayTree( TreeNode parent){
        String displayString=" ";
        if(parent != null){
            for(int i=0 ; i <=9 ; i++){
                if(parent.child[i] != null){
//                    System.out.println("["+i+"] ===>Depth"+parent.child[i].depth);
                    if(display[parent.child[i].depth] != null){
                        displayString = display[parent.child[i].depth] ;
                    }
                    displayString = parent.child[i].isEnd?displayString + "{"+ i +"} ":displayString + i +" ";
                    display[parent.child[i].depth]=displayString;
                    displayTree(parent.child[i]);
                }
            }
        }
    }
    public String refactorNumber(String inputString){
        Queue<Integer> integerQueue = new LinkedList<>();
        String refactoredString = "";

        if(inputString.length() == 0){ return "Empty";}
        integerQueue = getIntegerQueue(inputString);
        int queueLength = integerQueue.size();
        for(int i =0; i< queueLength;i++){
            int nextNumber=integerQueue.remove();
            if(i % 3 == 0 && i >0 ){
                refactoredString= refactoredString+"-"+nextNumber;
            }else{
                refactoredString= refactoredString+""+nextNumber;
            }
        }
        return refactoredString;
    }

    public Queue<Integer> getIntegerQueue(String s){
        Queue<Integer> integerQueue = new LinkedList<>();
        int stringLength = s.length();
        if(stringLength == 0){ return null; }

        for(int i=0; i< stringLength; i++){
            char newCharacter = s.charAt(i);
            if(isNumber(newCharacter)){
                integerQueue.add(Character.getNumericValue(newCharacter));
            }
        }
        return integerQueue;
    }

    public boolean isNumber(char c){
        return Character.isDigit(c);
    }
}
