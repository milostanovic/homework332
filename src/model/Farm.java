package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Farm {

    public Integer numberOfTractorsAssignedToFarm;
    private String[] typesOfFarm = {
        "Apples","Citrus","Grapes"
    };
    private Double[] farmLocationInMap = {
            0.0, 0.0, 0.0, 0.0
    };

    public String nameOfFarm = "";
    public String season = "";

    private ArrayList<Tractor> workingTractors = new ArrayList<>();

    public ArrayList<Tractor> getAllWorkingTractors(){
        for(int i=0; i< workingTractors.size(); i++){
            System.out.println("Current working tractor ID" + workingTractors.get(i).tractorId);
        }
        return workingTractors;
    }

    public String giveFarmId(String name){

        int geoLat = ThreadLocalRandom.current().nextInt(29, 34 + 1);
        int geoLong = ThreadLocalRandom.current().nextInt(33, 36 + 1);

        String farmId = name + geoLat + geoLong;
        System.out.println("Farm ID =" + farmId);
        return farmId;
    }

    public String getCorrectImplement(String season){
        String implement = "";
        switch (season){
            case "summer" : implement = "Sprayer filled with water";
            break;
            case "fall" : implement = "Mower";
            break;
            case "winter" : implement = "Driller";
            break;
            case "spring" : implement = "Sprayer filled with pesticides";
            break;
            default: System.out.println("Please enter correct value for season!");
        }
        return implement;
    }
    public ArrayList<Tractor> assignedTractors = new ArrayList<>();

    public void assignTractorsToFarm(Tractor tractorAssigned){
        assignedTractors.add(tractorAssigned);
    }

    public Farm(String farmName, String season){
        nameOfFarm = farmName;
        season = season;
    }
}
