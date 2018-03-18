package comp1110.ass2;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {

    /**
     * Determine whether a card placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range a..g (kingdom) or is z (Zhang Yi)
     * - the second character is numeric, and is a valid character number for that kingdom (9 for Zhang Yi)
     * - the third character is in the range A .. Z or 0..9 (location)
     *
     * @param cardPlacement A string describing a card placement
     * @return true if the card placement is well-formed
     */
    static boolean isCardPlacementWellFormed(String cardPlacement) {
        // FIXME Task 2: determine whether a card placement is well-formed
        ArrayList<String> country =  new ArrayList<String>();

        for (int i = 97; i <= 103; i++){ // create countries a...g
            String alpha  = String.valueOf((char) i);
            country.add(alpha);
        }

        ArrayList<String> place =  new ArrayList<String>();

        for (int i = 65; i <= 90; i++){ // create string of place A..Z&0..9
            String p  = String.valueOf((char) i);
            place.add(p);
        }

        for (int i = 0; i <= 9; i++){
            String p  = String.valueOf(i);
            place.add(p);
        }

        ArrayList<String> vaildCombination = new ArrayList<>();
        int character = 7;
        for (String c : country) {
            String vaild = "";
            String vaildc = vaild.concat(c);  // add country to string
            for (int i = 0 ; i <= character;i++){
                String vaildcn =vaildc.concat(String.valueOf(i)); // add person of all countries
                for (String p : place){
                    String vaildcnp =vaildcn.concat(p); // add place of each person
                    vaildCombination.add(vaildcnp);
                }
            }
            character --;
        }

        String ZhangYi = "Z9";  //place of ZhangYi
        for (String p : place){
            String vaildcnp =ZhangYi.concat(p);
            vaildCombination.add(vaildcnp);
        }


        boolean tOrf = false;

        if (vaildCombination.contains(cardPlacement)){
            tOrf = true;
        }

        return tOrf;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N three-character card placements (where N = 1 .. 36);
     * - each card placement is well-formed
     * - no card appears more than once in the placement
     * - no location contains more than one card
     *
     * @param placement A string describing a placement of one or more cards
     * @return true if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        boolean tOrf = true;


        if (placement.length() != 36*3){
            tOrf = false;   // check the length of placement
        }
        else {
            ArrayList<String> placementArray = new ArrayList<>();
            int index = 0;
            while (index < placement.length()) {   // split the placement by 3
                placementArray.add(placement.substring(index, index + 3));
                index += 3;
            }
            int sum=0 ;  // check is the splited array well formed
            for (String p : placementArray){
                if (isCardPlacementWellFormed(p)){
                    sum ++;
                }
            }

            if (sum<36){
                tOrf = false;
            }
            ArrayList<Character> place = new ArrayList<>(); //check the place repeated or not
            for (int i = 2; i <= 36 * 3; i = i + 3) {
                place.add(placement.charAt(i));
            }
            Set<Character> placeSet = new HashSet<>(place); // check the duplicate of place
            if (placeSet.size() < place.size()) {
                tOrf = false;
            }

            ArrayList<String> card = new ArrayList<>(); // check if any card repeat
            for (int i = 0; i <= 36 * 3 - 2; i = i + 3) {
                char c1 = placement.charAt(i);
                char c2 = placement.charAt(i + 1);
                String s = String.valueOf(c1).concat(String.valueOf(c2));

                card.add(s);
            }
            Set<String> cardSet = new HashSet<>(card);
            if (cardSet.size() < card.size()) {
                tOrf = false;
            }
        }

        return tOrf;
    }

    /**
     * Determine whether a given move is legal given a provided valid placement:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the location is in the same row or column of the grid as Zhang Yi's current position; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     *   there are no other cards along the line from the same kingdom as the chosen card
     *   that are further away from Zhang Yi.
     * @param placement    the current placement string
     * @param locationChar a location for Zhang Yi to move to
     * @return true if Zhang Yi may move to that location
     */
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        return false;
    }

    /**
     * Determine whether a move sequence is valid.
     * To be valid, the move sequence must be comprised of 1..N location characters
     * showing the location to move for Zhang Yi, and each move must be valid
     * given the placement that would have resulted from the preceding sequence
     * of moves.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @return True if the placement sequence is valid
     */
    static boolean isMoveSequenceValid(String setup, String moveSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        return false;
    }

    /**
     * Get the list of supporters for the chosen player, given the provided
     * setup and move sequence.
     * The list of supporters is a sequence of two-character card IDs, representing
     * the cards that the chosen player collected by moving Zhang Yi.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @param playerId     the player number for which to get the list of supporters, [0..(numPlayers-1)]
     * @return the list of supporters for the given player
     */
    public static String getSupporters(String setup, String moveSequence, int numPlayers, int playerId) {
        // FIXME Task 7: get the list of supporters for a given player after a sequence of moves
        return null;
    }

    /**
     * Given a setup and move sequence, determine which player controls the flag of each kingdom
     * after all the moves in the sequence have been played.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing a sequence of moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @return an array containing the player ID who controls each kingdom, where
     * - element 0 contains the player ID of the player who controls the flag of Qin
     * - element 1 contains the player ID of the player who controls the flag of Qi
     * - element 2 contains the player ID of the player who controls the flag of Chu
     * - element 3 contains the player ID of the player who controls the flag of Zhao
     * - element 4 contains the player ID of the player who controls the flag of Han
     * - element 5 contains the player ID of the player who controls the flag of Wei
     * - element 6 contains the player ID of the player who controls the flag of Yan
     * If no player controls a particular house, the element for that house will have the value -1.
     */
    public static int[] getFlags(String setup, String moveSequence, int numPlayers) {
        // FIXME Task 8: determine which player controls the flag of each kingdom after a given sequence of moves
        return null;
    }

    /**
     * Generate a legal move, given the provided placement string.
     * A move is valid if:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the destination location is different to Zhang Yi's current location;
     * - the destination is in the same row or column of the grid as Zhang Yi's current location; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     * If there is no legal move available, return the null character '\0'.
     * @param placement the current placement string
     * @return a location character representing Zhang Yi's destination for the move
     */
    public static char generateMove(String placement) {
        // FIXME Task 10: generate a legal move
        return '\0';
    }
}
