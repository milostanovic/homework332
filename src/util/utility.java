package util;

import model.Tractor;
import model.Farm;

import java.util.ArrayList;

public class utility {
    ArrayList<Tractor> tractors = new ArrayList<>();

    public void initializeTractors(int numOTractors, Farm farm, String farmId) {
        for (int i = 0; i < numOTractors; i++) {
            Tractor tractor = new Tractor();
            tractors.add(tractor);
            farm.assignTractorsToFarm(tractor);
            System.out.println("Tractor Created = " + tractor.tractorId);
            System.out.println("---------------------");
            System.out.println(">> " + numOTractors + " Tractors Assigned to farm " + farmId);
            System.out.println("---------------------");
        }
    }
}
