public class Game {


    AgentGenome agent1;
    AgentGenome agent2;
    Resource resource;

    public Game(AgentGenome agent1,AgentGenome agent2, Resource resource){

        this.agent1 = agent1;
        this.agent2 = agent2;
        this.resource = resource;

    }

    public String Start(int wordLength){

        String word;

        AgentGenome speaker = new AgentGenome(agent1);
        AgentGenome hearer = new AgentGenome(agent2);

        word = speaker.generateWord(wordLength);



        do{


            if (hearer.wordRatio(word) >= hearer.similarityThreshold || speaker.fitness <= speaker.talkingThreshold){


                    /*
                    agent1.win(resource);
                    agent2.win(resource);
                    */

                    agent1.win((int)agent1.wordRatio(word)*resource.reward);
                    agent2.win((int)agent2.wordRatio(word)*resource.reward);


                    agent1.addMorphemes(word);
                    agent2.addMorphemes(word);

    //                System.out.println(agent1.genome + " " + agent1.fitness + "\n" + agent2.genome +" "+ agent2.fitness +  "\n" +word );


                return word;

                } else{

                AgentGenome temp = new AgentGenome(speaker);
                speaker = new AgentGenome(hearer);
                hearer = temp;

                word = speaker.mutateWord(word);



            }


        } while (true);


    }





}
