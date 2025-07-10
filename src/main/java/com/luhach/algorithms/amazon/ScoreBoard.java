package com.luhach.algorithms.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ScoreBoard {

    public class Player{
        int score;
        String name;
        Player(String name,int score){
            this.name=name;
            this.score=score;
        }
        public void updateScore(int score){this.score=score;}
    }

    private static final Logger LOG = LoggerFactory.getLogger(ScoreBoard.class);

    HashMap<String, Player> players=new HashMap<>();
    TreeMap<Integer,List<Player>> scoreIndex=new TreeMap<>();

    public Player addPlayer(String name,int newScore){
        Player player;
        int oldScore=0;
        if (this.players.get(name) != null) {
            player=this.players.get(name);
            oldScore=player.score;
            player.updateScore( newScore+oldScore);
            if (oldScore!=player.score){removeScoreIndex(oldScore,player);}
        }else{
            player=new Player(name,newScore);
            this.players.put(name,player);
        }
        maintainScoreIndex(player);
        return player;
    }



    public void maintainScoreIndex(Player player){
        List<Player> playersList=new LinkedList<>();
        if(scoreIndex.get(player.score)!=null){
            playersList=scoreIndex.get(player.score);
            playersList.add(player);
        }
        playersList.add(player);
        scoreIndex.put(player.score,playersList);

    }

    public void removeScoreIndex(int oldScore,Player player){
        List<Player> existingPlayersList=new LinkedList<>();
        if(scoreIndex.get(oldScore)!=null){ // If Found Node in Tree
            existingPlayersList=scoreIndex.get(oldScore);
            for (Player eachPlayer :existingPlayersList) { // Check if Player Object exist
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
                List<Player> playersList=scoreIndex.get(iterator.next());
                for(Player eachObj:playersList){
                    System.out.println(eachObj.name+" : "+eachObj.score);
                    i++;
                }
        }
    }

}
