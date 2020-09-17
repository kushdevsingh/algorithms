package com.luhach.algorithms.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreBoard {

    public class Node{
        int score;
        String name;
        Node(String name,int score){
            this.name=name;
            this.score=score;
        }
        public void updateScore(int score){this.score=score;}
    }

    private static final Logger LOG = LoggerFactory.getLogger(ScoreBoard.class);

    HashMap<String, Node> players=new HashMap<>();
    TreeMap<Integer,List<Node>> scoreIndex=new TreeMap<>();

    public Node addPlayer(String name,int newScore){
        Node player;
        int oldScore=0;
        if (this.players.get(name) != null) {
            player=this.players.get(name);
            oldScore=player.score;
            player.updateScore( newScore+oldScore);
            if (oldScore!=player.score){removeScoreIndex(oldScore,player);}
        }else{
            player=new Node(name,newScore);
            this.players.put(name,player);
        }
        maintainScoreIndex(player);
        return player;
    }

    public void maintainScoreIndex(Node player){
        List<Node> playersList=new LinkedList<>();
        if(scoreIndex.get(player.score)!=null){
            playersList=scoreIndex.get(player.score);
            playersList.add(player);
        }
        playersList.add(player);
        scoreIndex.put(player.score,playersList);

    }

    public void removeScoreIndex(int oldScore,Node player){
        List<Node> existingPlayersList=new LinkedList<>();
        if(scoreIndex.get(oldScore)!=null){ // If Found Node in Tree
            existingPlayersList=scoreIndex.get(oldScore);
            for (Node eachPlayer :existingPlayersList) { // Check if Player Object exist
                if(eachPlayer==player)
                {
                    existingPlayersList.remove(eachPlayer);
                }
            }
            if (existingPlayersList.size()==0){
                scoreIndex.remove(oldScore);
            }else{
                scoreIndex.put(oldScore,existingPlayersList);
            }

        }
    }

    public void getTopPlayers(int top){
        Set<Integer> reverseKeys=scoreIndex.descendingKeySet();
        Iterator<Integer> iterator = reverseKeys.iterator();
        int i=0;
        while (iterator.hasNext() && i<=top ){
                List<Node> playersList=scoreIndex.get(iterator.next());
                for(Node eachObj:playersList){
                    System.out.println(eachObj.name+" : "+eachObj.score);
                    i++;
                }
        }
    }

}
