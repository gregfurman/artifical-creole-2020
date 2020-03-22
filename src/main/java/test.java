import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class test{


    static Simulation[] simulations;

    public static void load(int expID) {

        boolean[][] gameTypes = {{true, true}, {true, false}, {false, false}};
        int[][] sizeArr = {{50,50,50},{100,25,25},{25,100,25}, {25,25,100}};
        double[] radii = {0.25,0.5, 0.75, 1};


        int count = 0;
        int index = 0;

        for (int[] sizes : sizeArr) {

            for (boolean[] gameType : gameTypes) {

                for (double radius : radii) {

                    count++;
                    if (count == expID) {
                        simulations[index] = new Simulation(count, sizes, radius, gameType[0], gameType[1]);
                        index++;
                    }
                }

            }
        }



        for (int thread = 0; thread < simulations.length; thread++) {

            try {
                BufferedWriter writerProportions = new BufferedWriter(new FileWriter("agentsvsproportions_" + simulations[thread].index + ".txt", true));
                BufferedWriter writerFitness = new BufferedWriter(new FileWriter("agentsvfitness_" + simulations[thread].index + ".txt", true));
                BufferedWriter writerMap = new BufferedWriter(new FileWriter("letterMap" + simulations[thread].index + ".txt", true));
                BufferedWriter writerIndex = new BufferedWriter(new FileWriter("index.txt", true));


                writerIndex.write("index,Aprop,Bprop,Cprop,twoPlayer,similar,radius\n");
                writerProportions.write("Generation,Asize,Bsize,Csize,Aprop,Bprop,Cprop\n");
                writerFitness.write("Generation,Fitness,SimilarityThreshold\n");
                writerMap.write("Generation,a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z," +
                        "&, ', (, ), *, +, comma, -, ., /, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, :, ;, <, =, >, ?," +
                        "A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z\n");

                writerProportions.close();
                writerFitness.close();
                writerMap.close();
                writerIndex.close();

            } catch (IOException e) {

            }

        }




    }


    public static void main(String[] args) {

        int[] expID;

        if (args.length > 0) {

            expID = new int[args.length];

            for (int i = 0; i < args.length; i++)
                expID[i] = Integer.parseInt(args[i]);
        } else{

            expID = new int[48];

            for (int i = 0; i < 48; i++)
                expID[i] = i+1;
        }

        new test(expID);


    }



    private test(int[] expIDArray){

        simulations = new Simulation[expIDArray.length];
        for (int expID : expIDArray)
            load(expID);

        run();


    }

    public void run() {


        for (int thread = 0; thread < simulations.length; thread++) {

            Thread thread1;

            for (int i = 0; i< 50; i++) {
                thread1 = new Thread(simulations[thread]);
                thread1.start();
            }


        }

    }
}






