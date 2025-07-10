package com.luhach.algorithms.database;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DatabaseOperations {

    HashMap<String,Integer> lastId= new HashMap<>();
    HashMap<Integer,Players> players= new HashMap<>();
    TreeMap<Integer,List<Integer>> playerScoreIndex = new TreeMap<>();

    public int getId(String tableName){
        int returnId=(lastId.get(tableName)==null?1:lastId.get(tableName).intValue()+1);
        lastId.put(tableName,returnId);
        return returnId;
    }
    public String insertPlayer(int id, String firstName, String lastName, int age, char sex, int score, int rank){
        int primaryKey = 0;
        if(id == 0 && players.get(id)==null){
            primaryKey=getId("Players");
        }else if(id != 0 && players.get(id)==null)
        {
            primaryKey=id;
        }
        else{
            return "Primary Key Exist. Insert failed";
        }
        Players newPlayer = new Players(primaryKey,firstName,lastName,age,sex,score,rank);
        players.put(primaryKey,newPlayer);
        buildScoreIndex(score,primaryKey);
        return "Record Inserted...";
    }

    public void updateScore(int pk,int newScore){
        int oldScore=players.get(pk).getScore();
//        System.out.println("Key:"+oldScore);
        deleteIndex(oldScore,pk);
        players.get(pk).setScore(newScore);
        buildScoreIndex(newScore,pk);

    }

    public void deleteIndex(int key,int primaryKey){
        if(playerScoreIndex.containsKey(key)){
            List<Integer> keyList=playerScoreIndex.get(key);
            keyList.remove(primaryKey);
        }
    }

    public void buildScoreIndex(int key, int player){
        List<Integer> primaryKey = new ArrayList<>();
        if(playerScoreIndex.containsKey(key))
        {
            primaryKey=playerScoreIndex.get(key);
            primaryKey.add(player);
        }
        else
        {
            primaryKey.add(player);
            playerScoreIndex.put(key,primaryKey);
        }

//        for (Map.Entry<Integer,List<Integer>> entry : playerScoreIndex.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
//        }

    }

    public void selectById(int id){
        System.out.println(players.get(id));
    }

    public void fullScan(String value){
        boolean found=false;
        AtomicBoolean yes = new AtomicBoolean(true);
//        Iterator iterator = players.values().iterator();
//        while (iterator.hasNext() && !found)
//        {
//            Players p = (Players) iterator.next();
//            if(p.firstName.equals(value))
//            {
//                System.out.println(p);
//                found=true;
//            }
//        }
        players.values().parallelStream().takeWhile(value1 -> yes.get() ).forEach(player ->{
            if(player.firstName.equals(value))
            {
                System.out.println(player);
                yes.set(false);
            }
        });
    }

    public void distinctScore(){
        System.out.println(playerScoreIndex.keySet());
    }

    public void display(int score){
        if(score != 0){
            List<Integer> primaryKey=playerScoreIndex.get(score);
            primaryKey.forEach((eachPlayer)->System.out.println(players.get(eachPlayer)));
            System.out.println(primaryKey.size()+" Items Selected");
        }
        else
        {
            Iterator playerIterator = players.entrySet().iterator();
            while (playerIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) playerIterator.next();
                System.out.println(mapElement.toString());
            }
        }
//        Stream.of(players.values().toString()).forEach(System.out::println);

    }
}
