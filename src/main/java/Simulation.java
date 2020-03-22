import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class Simulation implements Runnable {


    volatile Population population;
    volatile int index;
    int[] proportions;
    double radius;
    boolean twoPlayer, similar;
    Map<Character,Integer> map;
   // volatile boolean done;



    public Simulation(int index,int[] proportions, double radius, boolean twoPlayer, boolean similar){

        this.proportions = proportions;
        this.radius = radius;
        this.twoPlayer = twoPlayer;
        this.similar = similar;
        this.index = index;
       // done = false;
    }


    synchronized void simulate(){


        population = new Population(proportions[0], proportions[1], proportions[2], 50, 40, 40, twoPlayer, similar, radius);

            try {



                BufferedWriter writer3 = new BufferedWriter(new FileWriter("index.txt", true));

                BufferedWriter writer = new BufferedWriter(new FileWriter("agentsvfitness_" + index + ".txt", true));

                BufferedWriter writer2 = new BufferedWriter(new FileWriter("agentsvsproportions_" + index + ".txt", true));

                    System.out.println("file doesnt exist and is being created");

                    writer3.write(index + "," + population.A + "," + population.B + "," + population.C + ","
                            + String.valueOf(population.twoPlayer)
                            + "," + String.valueOf(population.playWithMostSimilar) + "," + population.radius + "\n");

                    writer3.close();

                    BufferedWriter writer4 = new BufferedWriter(new FileWriter("letterMap"+index+".txt", true));



                {

                    for (int j = 0; j < 1000; j++) {

                        for (int i = 0; i < 100; i++) {
                            population.move();
                            population.checkAdjacency();
                        }


                        double[] values = languageProportions();

                        String proportions = "";


                        for (Map.Entry entry: map.entrySet()){
                        proportions += entry.getValue() + ",";
                        }

                        proportions = proportions.substring(0,proportions.length()-1);


                        writer4.write((j+1) + "," + proportions + "\n");


                        writer.write((j + 1) + "," + population.averageFitness() + "," +population.averageThreshold() + "\n");


                        writer2.write((j + 1) + "," + population.A + ","
                                + population.B + ","
                                + population.C + "," + values[0] + "," + values[1] + "," + values[2] + "\n");

                        population.loadResources();
                        population.evaluateFitness();
                        population.loadGrid(40, 40);

                    }

                }
                System.out.println("index: " + index + " has completed");



                writer.close();
                writer2.close();
                writer4.close();

            } catch (IOException e) {
                System.out.println("Error");
            }


    }

    @Override
    public void run() {

        simulate();

    }

    double[] languageProportions(){


        int sumA = 0,sumB=0,sumC=0;

        map = population.evaluateSimilarities();

        for (Map.Entry entry: map.entrySet()){

            if (population.languageA.contains((char)entry.getKey())){
                sumA+=(int)entry.getValue();

            } else if (population.languageB.contains((char)entry.getKey()))
                sumB += (int)entry.getValue();
            else
                sumC+=(int)entry.getValue();
        }


        double total = sumA + sumB + sumC;

        double[] values ={sumA/total,sumB/total,sumC/total};
        return values;

    }

}
