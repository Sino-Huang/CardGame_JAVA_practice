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
    //score value changes when call getHeuristicValue in Game.java

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

    public Player clone() {
        Player output = new Player(this.name, this.position);
        output.cards = (HashSet)this.cards.clone();
        output.flags = (HashSet) this.flags.clone();
        output.score = this.score;

        return output;
    }

}
