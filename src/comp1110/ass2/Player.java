package comp1110.ass2;

import comp1110.ass2.gui.Cards;
import comp1110.ass2.gui.Flags;


import java.util.HashSet;



public class Player {
    public String name;
    public int position;
    public HashSet<Cards> cards = new HashSet<>();
    public HashSet<Flags> flags = new HashSet<>();
    public double score;

    @Override
    public String toString() {
        return "{'" + name + '\'' +
                ", position=" + position +
                ", cards=" + cards +
                ", flags=" + flags +
                ", score=" + score +
                '}';
    }

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
        this.score = 0;
    }


    public void setScore() {
        this.score = flags.size() * 10 + cards.size() * 0.1;
    }
}
