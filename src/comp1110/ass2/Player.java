package comp1110.ass2;

import comp1110.ass2.gui.Characters;

import java.util.HashSet;

enum Flags {
    A,B,C,D,E,F,G;

    private String name;

    Flags() {
        this.name = this.name();
    }

    public String getName() {
        return name;
    }
}

public class Player {
    private String name;
    private int position;
    private HashSet<Characters> cards;
    private HashSet<Flags> flags;
    private double score;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public void setCards(String cards) {
        for (int i = 0; i < cards.length(); i += 2) {
            for (Characters characters : Characters.values()) {
                if (characters.name().equals(cards.substring(i, i + 2))) {
                    this.cards.add(characters);
                }
            }
        }
    }

    public void setFlags(String flags) {
        for (Flags flag : Flags.values()) {
            if (flag.getName().equals(flags)) {
                this.flags.add(flag);
            }
        }
    }

    public void setScore() {
        this.score = flags.size() * 10 + cards.size() * 0.1;
    }
}
