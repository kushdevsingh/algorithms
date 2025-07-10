package com.luhach.algorithms.commands;

import com.luhach.algorithms.database.DatabaseOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.concurrent.TimeUnit;


@ShellComponent
public class DatabaseCommands {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseCommands.class);

    @Autowired
    private DatabaseOperations dbOperations;

    @ShellMethod("Check Pass by Value or reference")
    public void insert(int id, String firstName, String lastName, int age, char sex, int score, int rank) {
        long startTime = System.nanoTime();
        int count=0;
        for(int i=0;i<10000000;i++){
            if(i % 10000 == 0){
                count ++;
//                System.out.println(10000*count +" records Inserted...");
            }
            String status = dbOperations.insertPlayer( id,  firstName+"_"+ i,  lastName,  age,  sex,  count,  10000000-i);
//            System.out.println(status);
        }
        long endTime   = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Took "+ seconds +" Milliseconds");

    }
    @ShellMethod("Select All values")
    public void select(String column, String value){
        long startTime = System.nanoTime();

        switch (column) {
            case "score":
                dbOperations.display(Integer.parseInt(value));
                break;
            case "id":
                dbOperations.selectById(Integer.parseInt(value));
                break;
            case "firstName":
                dbOperations.fullScan(value);
                break;
        }
        long endTime   = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Took "+ seconds +" Milliseconds");

    }

    @ShellMethod("Select distinct key")
    public void distinct(String column){
        long startTime = System.nanoTime();
        dbOperations.distinctScore();
        long endTime   = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Took "+ seconds +" Milliseconds");
    }

    @ShellMethod("Check Index")
    public void bi(int pk,int newScore){
        dbOperations.updateScore(pk,newScore);
    }



}
