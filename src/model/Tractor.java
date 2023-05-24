package model;

import enums.TRACTOR_STATUS;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Tractor implements Runnable{

    public String tractorId;

    public static int stopped = 0;

    //private String tractorType;

    public int tractorFuelStatus;

    public String typeOfImplementAttached = "";

    private TRACTOR_STATUS statusOfTheTractor ;

    public void run(){
        statusOfTheTractor = TRACTOR_STATUS.WORKING;
        for(int i=tractorFuelStatus; i>10; i--){
            try {
                //System.out.println("Tractor ID " + tractorId + " Info! Fuel deprecated by 1%! " + statusOfTheTractor);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.stopTractor();
        System.out.println("Warning! Fuel status reached 10% by Tractor ID -> " + tractorId + " " + statusOfTheTractor);
    }

    public int stopTractor(){
        stopped ++;
        statusOfTheTractor = TRACTOR_STATUS.STOPPED;
        return stopped;
    }

    public boolean isTractorStopped(){
        return TRACTOR_STATUS.STOPPED == statusOfTheTractor;
    }

    public void attachImplementToTractor(String implement){
        typeOfImplementAttached = implement; //
    }

    public void changeImplementToTractor(String implement){
        typeOfImplementAttached = implement;
    }

    private String[] typeOfTractor = { "JohnDeere", "NewHolland"};
    private String[] ImplementAttached = {"Mower","Sprayer filled with water", "Sprayer filled with pesticides","Driller","None"};

    private String getTractorType(){
            int rnd = new Random().nextInt(typeOfTractor.length);
            return typeOfTractor[rnd];
    }

    private String initializeImplementAttached(){
        int rnd = new Random().nextInt(ImplementAttached.length);
        return ImplementAttached[rnd];
    }


    public Tractor(){
        String type = getTractorType();

        Random r = new Random();
        int number = r.nextInt(99999);
        String randomId = String.format("%05d", number);
        tractorId = type + randomId;
        System.out.println("Tractor ID = " + tractorId);

        tractorFuelStatus = ThreadLocalRandom.current().nextInt(5,20);
        System.out.println("Tractor Fuel  = " + tractorFuelStatus);

        typeOfImplementAttached = initializeImplementAttached();
        System.out.println("Implement Attached  = " + typeOfImplementAttached);

        this.statusOfTheTractor = TRACTOR_STATUS.OFF;

    }
}
