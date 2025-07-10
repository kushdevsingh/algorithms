package com.luhach.algorithms.database;

public class Players {
    int     id;
    String  firstName;
    String  lastName;
    int     age;
    char    sex;
    int     score;
    int     rank;

    public Players(String firstName, String lastName,int score){
        this.firstName=firstName;
        this.lastName=lastName;
        this.score=score;

    }

    public Players(int id, String firstName, String lastName, int age, char sex, int score, int rank) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.score = score;
        this.rank = rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}
