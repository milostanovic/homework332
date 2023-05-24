import model.Farm;
import model.Tractor;
import util.utility;
import model.Implement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static <List> void main(String[] args) throws InterruptedException {


        Scanner enterFarmName = new Scanner(System.in);
        System.out.println("Please enter Farm Name: ");
        String farmName = enterFarmName.nextLine();

        Scanner enterSeason = new Scanner(System.in);
        System.out.println("Please choose season (spring, summer, fall or winter): ");
        String season = enterSeason.nextLine();

        Farm farm = new Farm(farmName, season);
        String farmId = farm.giveFarmId(farmName);

        Scanner number = new Scanner(System.in);
        System.out.println("Please enter number of tractors:");
        int numOTractors = number.nextInt();

        //initialize tractors and assign
        utility init = new utility();
        init.initializeTractors(numOTractors, farm, farmId);

        ArrayList<Tractor> workingTractors = new ArrayList<>();
        ArrayList<Tractor> fuelLowTractors = new ArrayList<>();
        ArrayList<Tractor> fuelReadyTractors = new ArrayList<>();
        ArrayList<Tractor> activeTractors = new ArrayList<>();


        //check fuel statuses of all assigned, make list of fuel ready
        for (int i = 0; i < farm.assignedTractors.size(); i++) {
            if(farm.assignedTractors.get(i).tractorFuelStatus <= 10) {
                fuelLowTractors.add(farm.assignedTractors.get(i));
                activeTractors.add(farm.assignedTractors.get(i));
            }
            else if (farm.assignedTractors.get(i).tractorFuelStatus > 10)
                fuelReadyTractors.add(farm.assignedTractors.get(i));
            if (fuelReadyTractors.size() == numOTractors) {
                break;
            }
        }
        int actTractors = fuelReadyTractors.size();
        System.out.println("ACTIVE TRACTORS! " + actTractors);
        System.out.println("Number of fuel low tractors currently is " + fuelLowTractors.size());
        System.out.println("Number of ready tractors currently is " + fuelReadyTractors.size());

        //attach appropriate implement
        String implement = farm.getCorrectImplement(season);
        for (int i = 0; i < fuelReadyTractors.size(); i++){
            if(!implement.equals(fuelReadyTractors.get(i).typeOfImplementAttached)) {
                fuelReadyTractors.get(i).changeImplementToTractor(implement);
                workingTractors.add(fuelReadyTractors.get(i));
                System.out.println("Tractor ID " + fuelReadyTractors.get(i).tractorId);
                System.out.println("tractor current implement " + fuelReadyTractors.get(i).typeOfImplementAttached);
            }
            else {
                System.out.println("Tractor ID " + fuelReadyTractors.get(i).tractorId +" Change of implement not needed!");
                workingTractors.add(fuelReadyTractors.get(i));
            }
            if (workingTractors.size() == 3) {
                break;
            }
        }
        // create pending tractors set
        for(int i=0; i< workingTractors.size(); i++){
            fuelReadyTractors.remove(workingTractors.get(i));
        }


        System.out.println("prepare for start!");

        //start the engines
        for (Tractor t : workingTractors) {
            new Thread(t).start();
        }


        while(workingTractors.size() != 0) {
            ArrayList<Tractor> tempTractors = new ArrayList<>();
            for (int i = 0; i < workingTractors.size(); i++) {
                if(workingTractors.get(i).isTractorStopped())
                {
                    if(fuelReadyTractors.size() > 0) {
                        Tractor newTractor = fuelReadyTractors.get(0);
                        new Thread(newTractor).start();
                        newTractor.changeImplementToTractor(farm.getCorrectImplement(season));
                        tempTractors.add(newTractor);
                        fuelReadyTractors.remove(0);
                    }
                }else {
                    tempTractors.add(workingTractors.get(i));
                }
            }
            workingTractors = tempTractors;
        }

        //implement all tractors off when fuel = 0
        for (int i = 0; i < activeTractors.size(); i++) {
            activeTractors.get(i).offTractor();
        }
        System.out.println(" >>> ALL TRACTORS ARE OUT OF FUEL AND ARE NOW SET OFF  <<< ");
    }
}