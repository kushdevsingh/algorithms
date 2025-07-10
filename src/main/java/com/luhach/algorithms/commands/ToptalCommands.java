package com.luhach.algorithms.commands;

import com.luhach.algorithms.toptal.PhoneBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Random;



@ShellComponent
public class ToptalCommands {

    private static final Logger LOG = LoggerFactory.getLogger(ToptalCommands.class);

    @Autowired
    private PhoneBook phoneBook;

    @ShellMethod(key = "refactorNumber", value = "Format given String into Phone Format(111-111-111)")
    public String refactorNumber(String unstructuredNumber)  {
        return phoneBook.refactorNumber(unstructuredNumber);
    }

    @ShellMethod(key = "addContact", value = "Add contact and maintain suffix.")
    public void addContact(String name,Long number)  {
        phoneBook.addContact( name, number);
    }

    @ShellMethod(key = "addStandardContacts", value = "Add Standard Numbers.")
    public void addStandardContacts()  {
        phoneBook.addContact( "Kushdev Singh", 4373448260L);
        phoneBook.addContact( "Purnima Singh", 4373450249L);
        phoneBook.addContact( "Veda Singh", 7875512160L);
        Random rand = new Random(); //instance of random class
        int upperbound = 1000000;
        Long num = 4373450243L;
        for(int i=0; i<= 1000; i++){
            num = num + rand.nextInt(upperbound) + i; ;
            phoneBook.addContact( " Random : "+ i +" Singh", num);
            if(i % 100 == 0){
                System.out.println("Reached :"+ i);
            }
        }

    }

    @ShellMethod(key = "display", value = "display contacts Tree.")
    public void display()  {
        phoneBook.display();
    }

    @ShellMethod(key = "findContact", value = "find contact with pattern.")
    public void findContact(String pattern)  {
        phoneBook.findContact(pattern);
    }
}
