package com.group11.othello.AI;


import java.io.*;
import java.util.Formatter;


public class Testing {

    public static String fName,sName,ev1,ev2,ev3;
    public static float score;

   Formatter file = new Formatter("Tests.txt");
   BufferedReader br = new BufferedReader(new FileReader("Tests.txt"));
   String thisLine = null;
   BufferedWriter bw = new BufferedWriter(new FileWriter("Tests.txt"));
    String newline = System.getProperty("line.separator");
    public Testing() throws IOException {
    }

    public void addRecords(String firstAiName,String secondAiName,String ev1,String ev2,String ev3,float score,String winner) throws IOException {
            String row = firstAiName + " | " + secondAiName + " | " + ev1 + " | " + ev2 + " | " + ev3 + " | " + score + " | " + winner;
            bw.write(row);
            bw.close();
    }

    public void setfName(String name)
    {
        fName=name;
    }
    public void setsName(String name)
    {
        sName=name;
    }
    public void setScore(float score)
    {
        this.score=score;
    }
    public void setev1(String name)
    {
        ev1=name;
    }
    public void setev2(String name)
    {
        ev2=name;
    }
    public void setev3(String name)
    {
        ev3=name;
    }

    public String getfName(){
        return this.fName;
    }
    public String getsName(){
        return this.sName;
    }

    public String getEv1(){
        return this.ev1;
    }
    public String getEv2(){
        return this.ev2;
    }
    public String getEv3(){
        return this.ev3;
    }
}
