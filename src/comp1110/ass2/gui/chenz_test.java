package comp1110.ass2.gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thinkpad on 2018/3/18.
 */
public class chenz_test {
    public static void main(String[] args) {
        String testplacement = "a0Aa1Ba2Ca3Da4Ea5Fa6Ga7Hb0Ib1Jb2Kb3Lb4Mb5Nb6Oc0Pc1Qc2Rc3Sc4Tc5Ud0Vd1Wd2Xd3Yd4Ze01e12e23e34f05f16f27g08g19Z90";
        System.out.println(isPlacementWellFormed(testplacement));
    }

    static boolean isCardPlacementWellFormed(String cardPlacement) {
        // FIXME Task 2: determine whether a card placement is well-formed
        ArrayList<String> country = new ArrayList<String>();

        for (int i = 97; i <= 103; i++) {
            String alpha = String.valueOf((char) i);
            country.add(alpha);
        }

        ArrayList<String> place = new ArrayList<String>();


        for (int i = 65; i <= 90; i++) {
            String p = String.valueOf((char) i);
            place.add(p);
        }
        for (int i = 0; i <= 9; i++) {
            String p = String.valueOf(i);
            place.add(p);
        }

        ArrayList<String> vaildCombination = new ArrayList<>();
        int character = 7;
        for (String c : country) {
            String vaild = "";
            String vaildc = vaild.concat(c);
            for (int i = 0; i <= character; i++) {
                String vaildcn = vaildc.concat(String.valueOf(i));
                for (String p : place) {
                    String vaildcnp = vaildcn.concat(p);
                    vaildCombination.add(vaildcnp);
                }
            }
            character--;
        }

        String ZhangYi = "Z9";
        for (String p : place) {
            String vaildcnp = ZhangYi.concat(p);
            vaildCombination.add(vaildcnp);
        }

        boolean tOrf = false;

        if (vaildCombination.contains(cardPlacement)) {
            tOrf = true;
        }

        return tOrf;
    }

    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        boolean tOrf = true;

        ArrayList<String> placementArray = new ArrayList<>();
        int index = 0;
        while (index < placement.length()) {
            placementArray.add(placement.substring(index, Math.min(index + 3,placement.length())));
            index += 3;
        }
        int sum=0 ;
        for (String p : placementArray){
            if (isCardPlacementWellFormed(p)){
                sum ++;
            }
        }

        if (placement.length() != 36*3){
            tOrf = false;
        }

        if (sum<36){
            tOrf = false;
        }
        if (placement.length() != 36*3){
            tOrf = false;
        }
        else {
            ArrayList<Character> place = new ArrayList<>();
            for (int i = 2; i <= 36 * 3; i = i + 3) {
                place.add(placement.charAt(i));
            }
            Set<Character> placeSet = new HashSet<>(place);
            if (placeSet.size() < place.size()) {
                tOrf = false;
            }

            ArrayList<String> card = new ArrayList<>();
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
}
