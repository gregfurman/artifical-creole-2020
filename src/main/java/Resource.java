public class Resource extends Cell {


    boolean twoPlayer;
    int reward;

    public Resource(boolean twoPlayer, int reward){
        this.twoPlayer = twoPlayer;
        this.reward = reward;
    }

    public String toString(){
        return "[R]";
    }



}
