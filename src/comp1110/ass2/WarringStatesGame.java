package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {
    private static ArrayList<String> validCombination = new ArrayList<>();

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
        validCombination.clear();
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

        int character = 7;
        for (String c : country) {
            String valid = "";
            String validdc = valid.concat(c);  // add country to string
            for (int i = 0 ; i <= character;i++){
                String validcn =validdc.concat(String.valueOf(i)); // add person of all countries
                for (String p : place){
                    String validcnp =validcn.concat(p); // add place of each person
                    validCombination.add(validcnp);
                }
            }
            character --;
        }
        String ZhangYi = "z9";  //place of ZhangYi is 'z9'
        for (String p : place){
            String vaildcnp =ZhangYi.concat(p);
            validCombination.add(vaildcnp);
        }

        return validCombination.contains(cardPlacement);
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
        if (placement == null || placement.equals("")) { // add null case
            return false;
        }
        if (placement.length() % 3 != 0) { // Wrong length of string check
            return false;
        }
        Set<String> indexSet = new HashSet<>();
        Set<String> characterSet = new HashSet<>();
        Set<String> placementSet = new HashSet<>();
        for (int i = 0; i < placement.length(); i += 3) {
            String card = placement.substring(i, i + 3);
            indexSet.add(card.substring(2));
            characterSet.add(card.substring(0, 2));
            placementSet.add(card);
        }
        if (indexSet.size() != placement.length() / 3) { // duplicate index check
            return false;
        }
        if (characterSet.size() != placement.length() / 3) { // Character is not correct or duplicate check
            return false;
        }
        isCardPlacementWellFormed("a2A"); // init the validplacementarray
        for (String card : placementSet) { // card valid check
            if (!validCombination.contains(card)) {
                return false;
            }
        }
        return true;
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
     static HashMap<Character,int[]> hm = new HashMap();
     static { // Create hashmap to describe the location
        hm.put(' ', new int[] {-1,-1});
        hm.put('A', new int[] {0,0});
        hm.put('B', new int[] {0,1});
        hm.put('C', new int[] {0,2});
        hm.put('D', new int[] {0,3});
        hm.put('E', new int[] {0,4});
        hm.put('F', new int[] {0,5});
        hm.put('G', new int[] {1,0});
        hm.put('H', new int[] {1,1});
        hm.put('I', new int[] {1,2});
        hm.put('J', new int[] {1,3});
        hm.put('K', new int[] {1,4});
        hm.put('L', new int[] {1,5});
        hm.put('M', new int[] {2,0});
        hm.put('N', new int[] {2,1});
        hm.put('O', new int[] {2,2});
        hm.put('P', new int[] {2,3});
        hm.put('Q', new int[] {2,4});
        hm.put('R', new int[] {2,5});
        hm.put('S', new int[] {3,0});
        hm.put('T', new int[] {3,1});
        hm.put('U', new int[] {3,2});
        hm.put('V', new int[] {3,3});
        hm.put('W', new int[] {3,4});
        hm.put('X', new int[] {3,5});
        hm.put('Y', new int[] {4,0});
        hm.put('Z', new int[] {4,1});
        hm.put('0', new int[] {4,2});
        hm.put('1', new int[] {4,3});
        hm.put('2', new int[] {4,4});
        hm.put('3', new int[] {4,5});
        hm.put('4', new int[] {5,0});
        hm.put('5', new int[] {5,1});
        hm.put('6', new int[] {5,2});
        hm.put('7', new int[] {5,3});
        hm.put('8', new int[] {5,4});
        hm.put('9', new int[] {5,5});

    }
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        if((int) locationChar < 48 || ((int) locationChar > 57 && (int) locationChar < 65) || (int) locationChar > 91){
            // Whether location char in in the range
            return false;
        }


        if(placement.equals("")) { // Whether the location has a card
            return false;
        }


        char zhangyi = ' ';
        int count = 0;
        char country = ' ';
        for(int i = 0; i < placement.length(); i++){ // get the location of Zhangyi
            if (placement.charAt(i) == 'z'){
                zhangyi = placement.charAt(i + 2);

            }
            if ((i-2)%3 == 0){
                if(placement.charAt(i) == locationChar){
                    count += 1;
                    country = placement.charAt(i - 2);
                }
            }

        }
        if(count != 1){
            return false;
        }
        if (zhangyi == locationChar) {
            return false;
        }

        if(hm.get(zhangyi)[0] != hm.get(locationChar)[0] && hm.get(zhangyi)[1] != hm.get(locationChar)[1]){
            return false;
        } // Whether the location is in the same row or column of the Zhang Yi's grid




        String countries = "";
        for(int i = 0; i < placement.length(); i += 3){ // Get all the locations of the country above
            if(placement.charAt(i) == country){
                countries = countries + String.valueOf(placement.charAt(i + 2));
            }
        }


        // No same kingdom card in the same line that is further away from Zhang yi
        if(hm.get(zhangyi)[0] == hm.get(locationChar)[0]) { // Same column case
            if (hm.get(zhangyi)[1] > hm.get(locationChar)[1]) { // Up direction
                for (int i = 0; i < countries.length(); i++) {
                    char a = countries.charAt(i);
                    if (hm.get(a)[1] < hm.get(locationChar)[1] && hm.get(a)[0] == hm.get(locationChar)[0]) {
                        return false;
                    }
                }
            } else if (hm.get(zhangyi)[1] < hm.get(locationChar)[1]) { // Down direction
                for (int i = 0; i < countries.length(); i++) {
                    char a = countries.charAt(i);
                    if (hm.get(a)[1] > hm.get(locationChar)[1] && hm.get(a)[0] == hm.get(locationChar)[0]) {
                        return false;
                    }
                }
            }


        }else if (hm.get(zhangyi)[1] == hm.get(locationChar)[1]){ // Same row case
            if(hm.get(zhangyi)[0] > hm.get(locationChar)[0]){ // Right direction
                for(int i = 0; i < countries.length(); i++){
                    char a = countries.charAt(i);
                    if(hm.get(a)[0] < hm.get(locationChar)[0] && hm.get(a)[1] == hm.get(locationChar)[1]){
                        return false;
                    }
                }
            }else if(hm.get(zhangyi)[0] < hm.get(locationChar)[0]){ // Left direction
                for(int i = 0; i < countries.length(); i++){
                    char a = countries.charAt(i);
                    if(hm.get(a)[0] > hm.get(locationChar)[0] && hm.get(a)[1] == hm.get(locationChar)[1]){
                        return false;
                    }
                }
            }
        }

        return true;
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

        if(moveSequence.length()==0){
            return true;
        }else{
            // check whether the first move is valid
            if(isMoveLegal(setup,moveSequence.charAt(0))){ // Check whether the move legal
                char zhangyi = ' ';
                for(int i = 0; i < setup.length(); i++){ // Get the location of Zhangyi and move the location of Zhangyi
                    if (setup.charAt(i) == 'z'){
                        zhangyi = setup.charAt(i + 2);
                    }
                }
                if (zhangyi == moveSequence.charAt(0)){ // Check duplicate
                    return false;
                }

                setup = updateBoard(setup, moveSequence);
                moveSequence = moveSequence.substring(1,moveSequence.length());
                return isMoveSequenceValid(setup,moveSequence);// recursively check
            }else{
                return false;
            }
        }
    }

    public static String updateBoard(String setup, String moveSequence) {
        StringBuilder setupbuilder = new StringBuilder(setup);
        char country = ' ';
        for(int i = 2; i < setup.length(); i += 3){ // Get the country of moveSequence
            if(setup.charAt(i) == moveSequence.charAt(0)){
                country = setup.charAt(i - 2);
            }
        }

        String countries = "";
        for(int i = 0; i < setup.length(); i += 3 ){ // Get all the locations of the country above
            if(setup.charAt(i) == country){
                countries = countries + String.valueOf(setup.charAt(i + 2));
            }
        }

        char zhangyi = ' ';
        for(int i = 0; i < setup.length(); i++){ // Get the location of Zhangyi and move the location of Zhangyi
            if (setup.charAt(i) == 'z'){
                zhangyi = setup.charAt(i + 2);
                setupbuilder.replace(i + 2,i + 3,String.valueOf(moveSequence.charAt(0)));
            }
        }

        //delete country
        if(hm.get(zhangyi)[0] == hm.get(moveSequence.charAt(0))[0]) { // Same column case
            if (hm.get(zhangyi)[1] > hm.get(moveSequence.charAt(0))[1]) { // Up direction
                for (int i = 0; i < countries.length(); i++) {
                    char a = countries.charAt(i);
                    if (hm.get(a)[1] >= hm.get(moveSequence.charAt(0))[1] &&
                            hm.get(a)[0] == hm.get(moveSequence.charAt(0))[0] &&
                            hm.get(a)[1] < hm.get(zhangyi)[1]) {
                        for (int j = setupbuilder.length() - 1; j > 0 ; j -= 3) {
                            if (setupbuilder.charAt(j) == a) {
                                setupbuilder.delete(j - 2,j + 1);
                            }
                        }
                    }
                }
            } else if (hm.get(zhangyi)[1] < hm.get(moveSequence.charAt(0))[1]) { // Down direction
                for (int i = 0; i < countries.length(); i++) {
                    char a = countries.charAt(i);
                    if (hm.get(a)[1] <= hm.get(moveSequence.charAt(0))[1] &&
                            hm.get(a)[0] == hm.get(moveSequence.charAt(0))[0] &&
                            hm.get(a)[1] > hm.get(zhangyi)[1]) {
                        for (int j = setupbuilder.length() - 1; j > 0 ; j -= 3) {
                            if (setupbuilder.charAt(j) == a) {
                                setupbuilder.delete(j - 2, j + 1);
                            }
                        }
                    }

                }
            }

        }else if (hm.get(zhangyi)[1] == hm.get(moveSequence.charAt(0))[1]){ // Same row case
            if(hm.get(zhangyi)[0] > hm.get(moveSequence.charAt(0))[0]){ // Right direction
                for(int i = 0; i < countries.length(); i++){
                    char a = countries.charAt(i);
                    if (hm.get(a)[0] >= hm.get(moveSequence.charAt(0))[0] &&
                            hm.get(a)[1] == hm.get(moveSequence.charAt(0))[1] &&
                            hm.get(a)[0] < hm.get(zhangyi)[0]) {
                        for (int j = setupbuilder.length() - 1; j > 0 ; j -= 3) {
                            if (setupbuilder.charAt(j) == a) {
                                setupbuilder.delete(j - 2, j + 1);
                            }
                        }
                    }

                }
            }else if(hm.get(zhangyi)[0] < hm.get(moveSequence.charAt(0))[0]){ // Left direction
                for(int i = 0; i < countries.length(); i++){
                    char a = countries.charAt(i);
                    if (hm.get(a)[0] <= hm.get(moveSequence.charAt(0))[0] &&
                            hm.get(a)[1] == hm.get(moveSequence.charAt(0))[1] &&
                            hm.get(a)[0] > hm.get(zhangyi)[0]) {
                        for (int j = setupbuilder.length() - 1; j > 0 ; j -= 3) {
                            if (setupbuilder.charAt(j) == a) {
                                setupbuilder.delete(j - 2, j + 1);
                            }
                        }
                    }
                }
            }
        }
        //update the placement string
        setup = setupbuilder.toString();
        setup += "z9" + moveSequence.charAt(0);
        return setup;
    }

    public static String sortSetup(String setup) {
        StringBuffer boardPosition = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        String output = "";
        while (boardPosition.length() > 0) {
            for (int i = 0; i < setup.length(); i += 3) {
                if (boardPosition.charAt(0) == setup.charAt(i + 2)) {
                    output += setup.substring(i, i + 3);
                    boardPosition.deleteCharAt(0);
                    break;
                }
                if (i == setup.length() - 1) {
                    boardPosition.deleteCharAt(0);
                }
            }
        }
        return output;
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
        // check which move is corresponding to the player
        List<Integer> playerMoves = new ArrayList<>();

        for (int i = playerId + 1 ; i <= moveSequence.length(); i += numPlayers){
            playerMoves.add(i-1);
        }

        // get currentPosition of z9 and store it
        char currentPosition = '\0';
        for (int j = 0; j < setup.length(); j += 3) {
            if (setup.substring(j, j + 2).equals("z9")) {
                currentPosition = setup.charAt(j + 2);
                break;
            }
        }


        //assume it is already correct
        String supporters = "";

        // simulate the game, get the card at that player's round, add to the string

        for (int i = 0; i < moveSequence.length(); i ++) {
            //the idea is to update the board each move, and get the difference of the board between each move
            //this gives the support
            String[] setupList = setup.split("(?<=\\G...)");
            char movePosition = moveSequence.charAt(i);

            String updatedSetup = updateBoard(setup, String.valueOf(movePosition));

            //creat the String list for compare

            String[] updatedSetupList = updatedSetup.split("(?<=\\G...)");

            //find the difference

            for (int j = 0; j < setupList.length ; j++){
                if (Arrays.asList(updatedSetupList).contains(setupList[j]) || setupList[j].substring(0,2).equals("z9")){
                    setupList[j] = "";
                }
                else{
                    setupList[j] = setupList[j].substring(0,2);
                }
            }

            // arrange the order of the supporter input
            String finalSupporterString = String.join("", setupList);
            int[] currentPositionCoor = hm.get(currentPosition);
            int[] movePositionCoor = hm.get(movePosition);
            if (currentPositionCoor[1] > movePositionCoor[1] || currentPositionCoor[0] > movePositionCoor[0]) {
                String newString = "";
                for (int j = 0; j < finalSupporterString.length(); j += 2) {
                    newString = finalSupporterString.substring(j, j + 2) + newString;
                }
                finalSupporterString = newString;
            }

            currentPosition = movePosition;

            // store the difference for particular player p

            if (playerMoves.contains(i)) {
                supporters = supporters.concat(finalSupporterString);
            }
            setup = updatedSetup;
        }
        return supporters;

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
        setup = sortSetup(setup);
        String countryList = "abcdefg";
        int[] flagArray = new int[]{-1,-1,-1,-1,-1,-1,-1}; // init the flag array
        String[] supporterArray = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            supporterArray[i] = getSupporters(setup, moveSequence, numPlayers, i);
        }
        //start to check the supporters' country one by one
        String[] countryRecord = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            int countA=0, countB=0, countC=0, countD=0, countE=0, countF=0, countG = 0;
            for (int j = 0; j < supporterArray[i].length(); j += 2) {
                switch (supporterArray[i].charAt(j)) {
                    case 'a':
                        countA += 1;
                        break;
                    case 'b':
                        countB += 1;
                        break;
                    case 'c':
                        countC += 1;
                        break;
                    case 'd':
                        countD += 1;
                        break;
                    case 'e':
                        countE += 1;
                        break;
                    case 'f':
                        countF += 1;
                        break;
                    case 'g':
                        countG += 1;
                        break;
                }
                }
            countryRecord[i] = Integer.toString(countA) + Integer.toString(countB) + Integer.toString(countC) + Integer.toString(countD) + Integer.toString(countE) + Integer.toString(countF) + Integer.toString(countG);
       }
            // now it is the time to check who get the flag
        for (int i = 0; i < 7; i++) {
            char theSelectedCountry = countryList.charAt(i);
            int[] countryCompare = new int[numPlayers];
            for (int j = 0; j < numPlayers; j++) {
                countryCompare[j] = Character.getNumericValue(countryRecord[j].charAt(i));
            }
            int currentWinner = -1;
            int max = -5;
            for (int j = 0; j < countryCompare.length; j++) {
                if (countryCompare[j] == 0) {
                    continue;
                }
                if (max < countryCompare[j]) {
                    currentWinner = j;
                    max = countryCompare[j];
                }else if (max == countryCompare[j]) { // now find who is the last move
                    // find old winner's last supporter of this country
                    String oldWinnerSupporter = "";
                    char oldWinnerSupporterLocation = '\0';
                    for (int k = supporterArray[currentWinner].length() - 2; k >= 0; k -= 2) {
                        if (supporterArray[currentWinner].charAt(k) == theSelectedCountry) {
                            oldWinnerSupporter = supporterArray[currentWinner].substring(k, k + 2);
                            break;
                        }
                    }
                    oldWinnerSupporterLocation = setup.charAt(setup.indexOf(oldWinnerSupporter) + 2);
                    // find new player's last supporter of this country
                    String newWinnerSupporter = "";
                    char newWinnerSupporterLocation = '\0';
                    for (int k = supporterArray[j].length() - 2; k >= 0; k -= 2) {
                        if (supporterArray[j].charAt(k) == theSelectedCountry) {
                            newWinnerSupporter = supporterArray[j].substring(k, k + 2);
                            break;
                        }
                    }
                    newWinnerSupporterLocation = setup.charAt(setup.indexOf(newWinnerSupporter) + 2);

                    // find out who is the last pick
                    if (moveSequence.indexOf(oldWinnerSupporterLocation) > moveSequence.indexOf(newWinnerSupporterLocation)) {
                        continue;
                    } else {
                        currentWinner = j;
                    }
                }
            }
            flagArray[i] = currentWinner;
        }

        return flagArray;
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
       if(!generateAllLegalMove(placement).isEmpty()) {
            Random ran = new Random();
            int index = ran.nextInt(generateAllLegalMove(placement).size());
            return generateAllLegalMove(placement).get(index);
        }
        // randomly choose one legal move
        //for task 12, adjust generateMove to return a valuable move.

        return '\0';
    }

    public static ArrayList<Character> generateAllLegalMove(String placement) {// generate all legal moves for zhang yi
        //TODO
        ArrayList<Character> output = new ArrayList<>();
        for(int i = 2; i < placement.length(); i += 3){
            if(isMoveLegal(placement,placement.charAt(i))){
                output.add(placement.charAt(i));
            }
        }
        return output;
    }

}
