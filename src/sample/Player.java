package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Player implements Serializable, Comparable<Player> {
    private static final long serialVersionUID = 1789469392935285364L;
    String name;
    int eggsBroken;
    int eggsCaught;
    int level;
    String time;
    long totalSeconds;

    static ArrayList<Player> players = new ArrayList<>();

    public Player(String name){
        File file = new File("listData");
        if(file.length() != 0){ deserialize(); }
        this.name = name;
        this.eggsBroken = Egg.broken;
        this.eggsCaught = Egg.caught;
        this.level = GameRoot.level;
        this.totalSeconds = Controller.timeCounter;
        this.time = timeInterpreter();
        players.add(this);
        Collections.sort(players);
        serialize();
    }

    public String timeInterpreter(){
        long sec = Controller.timeCounter;
        int seconds, minutes, hours;
        seconds = (int) (sec%60);
        sec = (int) (sec - seconds)/60;
        minutes = (int) (sec%60);
        sec = (int) (sec - seconds)/60;
        hours = (int) (sec%24);
        return hours + ":" + minutes + ":" + seconds;
    }

    @Override
    public String toString(){
        return (players.indexOf(this) + 1) +
                ". @" + this.name + ": eggs caught - " +
                this.eggsCaught + "; difficulty: " + this.level + "; time played - " + this.time + "\n";
    }

    public static void deserialize(){
        try
        {
            FileInputStream fis = new FileInputStream("listData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            players = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    public static void serialize(){
        try
        {
            FileOutputStream fos = new FileOutputStream("listData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(players);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    @Override
    public int compareTo(Player p) {
        if(this.eggsCaught == p.eggsCaught) {
            if (this.level == p.level){
                return (this.totalSeconds > p.totalSeconds) ? -1 : 1;
            }
            else return (this.level > p.level) ? -1 : 1;
        }
        else return (this.eggsCaught > p.eggsCaught) ? -1 : 1;
    }
}
