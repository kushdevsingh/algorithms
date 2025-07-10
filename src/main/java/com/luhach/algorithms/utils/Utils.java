package com.luhach.algorithms.utils;

import org.springframework.stereotype.Service;

@Service
public class Utils {

    public int reverseNumber(int num){

        int newNumber=0;
        int number=num;

        while(number>9){
            newNumber=(newNumber*10)+(number % 10);
            number=number/10;
        }
        newNumber=(newNumber*10)+(number);
        return newNumber;
    }


}
