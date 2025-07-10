package com.luhach.algorithms.commands;

import com.luhach.algorithms.database.Players;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellMethod;

public class PassByValueOrReference {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseCommands.class);

    @ShellMethod("Check Pass by Value or reference")
    public void check(String firstName,String lastName,int score) {
        Players newPlayer = new Players(firstName,lastName,score);
        String newPlayerAnnouncement="Creating new Player : "+ firstName +" "+ lastName;
        int[] scoreArray =new int[] {0,1,2,3,4,5,6,7,8,9};
        LOG.info("Announcement :{} ",newPlayerAnnouncement);
        LOG.info("scoreArray :{} ",scoreArray);
        LOG.info("Before: Object newPlayer :{}, Data:{}",newPlayer.hashCode(),newPlayer.toString());
        changeObject(newPlayer,newPlayerAnnouncement,scoreArray);
        LOG.info("Announcement :{} ",newPlayerAnnouncement);
        LOG.info("scoreArray :{} ",scoreArray);
        LOG.info("After: Object newPlayer :{}, Data:{}",newPlayer.hashCode(),newPlayer);
    }

    public void changeObject(Players playerObject,String stringValue,int[] scoresArray){
        stringValue = stringValue +" with Id as "+ playerObject.getId();
        LOG.info("Announcement :{} ",stringValue);
        scoresArray[0]=10;
        LOG.info("Object playerObject :{}, Data:{}",playerObject.hashCode(),playerObject.toString());
        Players cloneObject = playerObject;
        cloneObject.setRank(scoresArray[0]);
        LOG.info("Object cloneObject :{}, Data:{}",cloneObject.hashCode(),cloneObject.toString());
    }
}
