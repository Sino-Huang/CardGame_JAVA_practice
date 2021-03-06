package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static comp1110.ass2.TestUtility.PLACEMENTS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UpdateBoardTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    private static String []CorrectString = {
            "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",
            "b4Bc0Cb1Dd4Ed0Fg0Ha4Ia7Jf2Kc2Lc5Mb2Nf0Oe3Pb6Qa6Re0Sf1Tc1Uc4Vg1Wa3Xa0Yb0Zc30e11a22b33b54a15d26a57d18d39z9A",
            "e2Ab4Bc0Cb1Dd4Ed0Fg0Ha4Ia7Jf2Kc2Lc5Mb2Nf0Oe3Pb6Qa6Rf1Tc1Uc4Vg1Wa3Xa0Yb0Zc30e11a22b33b54a15d26a57d18d39z9S",
            "e2Ab4Bc0Cb1Dd4Ed0Fg0Ha4Ia7Jf2Kc2Lc5Mb2Nf0Oe3Pb6Qa6Rf1Tc1Uc4Vg1Wa3Xa0Yb0Zc30e11a22b33b54a15d26a57d18d39z9S",
            "g1Ab2Ba4Ce2Dd4Eb4Fc3Gf1Ha2Ig0Jc2Kd2Le1Ma1Nb6Oc0Pc1Qe0Rf0Sf2Tb3Uc4Vc5Wb5Xd1Ya7Za00d02b03a54a65d36e38a39z97",
            "g1Ab2Ba4Cd4Eb4Fc3Gf1Ha2Ig0Jc2Kd2Le1Ma1Nb6Oc0Pc1Qe0Rf0Sf2Tb3Uc4Vc5Wb5Xd1Ya7Za00d02b03a54a65d36b17e38a39z9D",
            "g1Ab2Ba4Ce2Dd4Eb4Fc3Gf1Ha2Ig0Jc2Kd2Le1Ma1Nb6Oc1Qe0Rf0Sf2Tb3Uc5Wb5Xd1Ya7Za00d02b03a54a65d36b17e38a39z9P",
            "g1Ab2Ba4Ce2Dd4Eb4Fc3Gf1Ha2Ig0Jc2Kd2Le1Ma1Nb6Oc0Pc1Qe0Rf0Sf2Tb3Uc4Vc5Wb5Xa7Za00d02b03a54a65d36b17e38a39z9Y",
            "g1Ab2Ba4Ce2Dd4Eb4Fc3Gf1Ha2Ig0Jc2Kd2Le1Ma1Nb6Oc0Pc1Qe0Rf0Sf2Tb3Uc4Vc5Wb5Xa7Za00d02b03a54a65d36b17e38a39z9Y",
            "b4Aa2Bf1Dd0Ea7Ff0Gb0Hd4Jd2Kf2Lc3Mc4Nd1Oa0Pa1Qa4Re2Se1Tc5Uc0Vg0Wb6Xb1Ya3Za60d31c22a53b24e35g16e07b38c19z9I",
            "b4Aa2Bf1Dd0Ea7Ff0Gb0Hb5Id4Jd2Kf2Lc3Mc4Nd1Oa0Pa1Qa4Re2Se1Tc5Uc0Vg0Wb6Xb1Ya3Za60d31c22a53b24e35e07b38c19z96",
            "a2Bf1Dd0Ea7Ff0Gb0Hb5Id4Jd2Kf2Lc3Mc4Nd1Oa0Pa1Qa4Re2Se1Tc5Uc0Vg0Wb6Xb1Ya3Za60d31c22a53b24e35g16e07b38c19z9A",
            "b4Aa2Bf1Dd0Ea7Ff0Gb0Hb5Id4Jd2Kf2Lc3Mc4Nd1Oa0Pa1Qa4Re2Se1Tc0Vg0Wb6Xb1Ya3Za60d31c22a53b24e35g16e07b38c19z9U",
            "b4Aa2Bf1Dd0Ea7Ff0Gb0Hb5Id4Jd2Kf2Lc3Mc4Na0Pa1Qa4Re2Se1Tc5Uc0Vg0Wb6Xb1Ya3Za60d31c22a53b24e35g16e07b38c19z9O",
            "c5Aa6Bf0Cb0Da2Ec0Gb2Ha3Ib6Jd4Kb3Lb1Mc1Nc4Od3Pg0Qd1Re3Se2Ta0Ud2Ve1Wd0Ye0Zf20a11c22a73f14b55c36g17b48a49z9F",
            "c5Aa6Bf0Cb0Da2Ea5Fc0Gb2Ha3Ib6Jd4Kb3Lb1Mc1Nc4Od3Pg0Qd1Re3Se2Ta0Ud2Ve1Wd0Ye0Zf20a11c22f14b55c36g17b48z99",
            "c5Aa6Bf0Cb0Da2Ea5Fc0Gb2Ha3Ib6Jd4Kb1Mc1Nc4Od3Pg0Qd1Re3Se2Ta0Ud2Ve1Wd0Ye0Zf20a11c22a73f14b55c36g17b48a49z9L",
            "c5Aa6Bf0Cb0Da2Ea5Fc0Gb2Ha3Ib6Jd4Kb3Lb1Mc1Nc4Od3Pg0Qd1Re3Se2Ta0Ud2Ve1Wd0Ye0Zf20a11c22f14b55c36g17b48z99",
            "c5Aa6Bf0Cb0Da2Ea5Fc0Gb2Ha3Ib6Jd4Kb3Lb1Mc1Nc4Od3Pg0Qe3Se2Ta0Ud2Ve1Wd0Ye0Zf20a11c22a73f14b55c36g17b48a49z9R",
            "c2Ab4Cb2Dc1Ea6Fa7Ga4Hg0Ia1Jd1Ke0Lf0Mb1Nc0Of1Pd0Qg1Rd3Sc4Te2Ub5Vf2We1Xb0Ya5Zb30d21a32b63a04c36c57e38a29z95",
            "c2Ab4Cb2Dc1Ea6Fa7Gg0Ia1Jd1Ke0Lf0Mb1Nc0Of1Pd0Qg1Rd3Sc4Te2Ub5Vf2We1Xb0Yb30d21a32b63a04d45c36c57e38a29z9Z",
            "c2Ab4Cb2Dc1Ea6Fa7Ga4Hg0Ia1Jd1Ke0Lf0Mc0Of1Pd0Qg1Rd3Sc4Te2Ub5Vf2We1Xb0Ya5Zb30d21a32b63a04d45c36c57e38a29z9N",
            "c2Ab4Cb2Dc1Ea6Fa7Ga4Hg0Ia1Jd1Ke0Lf0Mb1Nc0Of1Pd0Qg1Rd3Se2Ub5Vf2We1Xb0Ya5Zb30d21a32b63a04d45c36c57e38a29z9T",
            "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",
            "b3Ab0Bd2Ce2Da7Ea4Ff0Gd4He1Ia0Jg0Kb6Lc5Mc0Oe3Pe0Qa3Rb4Sf2Ug1Vc1Wc4Xa1Yc2Za50f11c32b23d14d05d36b57a68b19z9T",
            "b3Ab0Bd2Ce2Da7Ea4Ff0Gd4He1Ia0Jg0Kb6Lc5Mc0Oe3Pe0Qa3Rb4Sa2Tf2Ug1Vc1Wc4Xa1Ya50f11c32b23d14d05d36b57a68b19z9Z",
            "b3Ab0Bd2Ce2Da7Ea4Ff0Gd4He1Ia0Jg0Kb6Lc5Mc0Oe3Pe0Qa3Rb4Sa2Tf2Ug1Vc1Wc4Xa1Yc2Za50f11c32b23d14d36b57a68b19z95",
            "b3Ad2Ce2Da7Ea4Ff0Gd4He1Ia0Jg0Kb6Lc5Mc0Oe3Pe0Qa3Rb4Sa2Tf2Ug1Vc1Wc4Xa1Yc2Za50f11c32b23d14d05d36b57a68b19z9B",
            "f1Aa7Ba0Cb6Da5Ec3Fb0Gc2Hg0Ia6Kc4La4Mf2Ne1Of0Pd2Qb3Rd3Sb2Tb1Ue2Wc0Xd1Yc5Zb40d01b52a33d44a15c16a28g19z9J",
            "f1Aa7Ba0Cb6Da5Ec3Fb0Gc2Hg0Ie3Ja6Kc4La4Mf2Ne1Od2Qb3Rd3Sb2Tb1Ue0Ve2Wc0Xd1Yc5Zb40d01b52a33d44a15c16a28g19z9P",
            "f1Aa7Ba0Cb6Da5Ec3Fb0Gc2Hg0Ie3Ja6Kc4La4Mf2Ne1Of0Pd2Qb3Rd3Sb2Tb1Ue0Ve2Wc0Xd1Yc5Zb40d01b52a33d44a15c16g19z98",
            "f1Aa7Ba0Cb6Da5Ec3Fb0Gc2Hg0Ia6Kc4La4Mf2Ne1Of0Pd2Qb3Rd3Sb2Tb1Ue2Wc0Xd1Yc5Zb40d01b52a33d44a15c16a28g19z9J",
            "f1Aa7Ba0Cb6Da5Ec3Fb0Gc2Hg0Ia6Kc4La4Mf2Ne1Of0Pd2Qb3Rd3Sb2Tb1Ue2Wc0Xd1Yc5Zb40d01b52a33d44a15c16a28g19z9J",
            "e1Af2Bc4Ce0Da7Fa0Gg0Hc3Ib4Jd3Kc1Lb5Mc0Ne2Od1Pd2Qa2Rb3Sc5Td4Ub1Vf0Wb0Xa1Ya3Ze30a41c23a64b25a56b67f18d09z9E",
            "e1Af2Bc4Ce0Da7Fa0Gg0Hc3Ib4Jd3Kc1Lb5Mc0Ne2Od1Pd2Qa2Rb3Sc5Td4Ub1Vf0Wb0Xa1Ya3Ze30a41c23a64b25a56b67f18d09z9E",
            "e1Af2Bc4Ce0Dg1Ea7Fa0Gg0Hc3Ib4Jd3Kc1Lb5Mc0Ne2Od1Pd2Qa2Rb3Sc5Td4Ub1Vb0Xa1Ya3Ze30a41c23a64b25a56b67f18d09z9W",
            "e1Af2Bc4Ce0Dg1Ea7Fa0Gg0Hc3Ib4Jd3Kc1Lb5Mc0Ne2Od1Pd2Qa2Rb3Sc5Td4Ub1Vf0Wb0Xe30c23a64b25a56b67f18d09z9Y",
            "e1Af2Bc4Ce0Dg1Ea7Fa0Gg0Hc3Ib4Jd3Kc1Lb5Mc0Ne2Od1Pd2Qa2Rb3Sc5Td4Ub1Vb0Xa1Ya3Ze30a41c23a64b25a56b67f18d09z9W",
            "b0Ac0Bf1Cb4De1Ea3Fc2Gb3Ia5Jc5Ke2Lb1Mf2Nd2Og0Pf0Qc4Rb2Sg1Ta7Ub5Vd4Wc3Xd1Ye30c11a62a03d34a25b66a17a48d09z9Z",
            "b0Ac0Bf1Cb4De1Ea3Fc2Gb3Ia5Jc5Ke2Lb1Mf2Nd2Og0Pf0Qc4Rb2Sg1Ta7Ub5Vd4Wc3Xd1Ye30c11a62a03d34a25b66a17a48d09z9Z",
            "b0Ac0Bf1Cb4De1Ea3Fc2Gb3Ia5Jc5Ke2Lb1Mf2Nd2Og0Pf0Qc4Rb2Sg1Ta7Ub5Vd4Wc3Xd1Ye30c11a62a03d34a25b66a17a48d09z9Z",
            "b0Ac0Bf1Cb4De1Ea3Fc2Ga5Jc5Ke2Lb1Mf2Nd2Og0Pf0Qc4Rb2Sg1Ta7Ub5Vd4Wc3Xd1Ye0Ze30c11a62a03d34a25b66a17a48d09z9I",
            "b0Ac0Bf1Cb4De1Ea3Fc2Gb3Ia5Jc5Ke2Lb1Md2Og0Pf0Qc4Rb2Sg1Ta7Ub5Vd4Wc3Xd1Ye0Ze30c11a62a03d34a25b66a17a48d09z9N",
            "a7Aa0Bb5Cg1Dd0Ea6Fe3Ga4Hg0Ie2Je1Ka3Lb3Md1Nd2Ob4Qd4Rc3Sf1Tc4Ub2Wb1Xc1Yf0Zb60d31c52b03f24c25a26c08e09z97",
            "a7Aa0Bb5Cd0Ea6Fe3Ga4Hg0Ie2Je1Ka3Lb3Md1Nd2Ob4Qd4Rc3Sf1Tc4Ua5Vb2Wb1Xc1Yf0Zb60d31c52b03f24c25a26a17c08e09z9D",
            "a7Aa0Bb5Cg1Dd0Ea6Fe3Ga4Hg0Ie2Je1Ka3Lb3Md1Nd2Ob4Qd4Rc3Sf1Tc4Ua5Vb2Wb1Xc1Yf0Zb60c52b03f24c25a26a17c08e09z91",
            "a7Aa0Bb5Cg1Dd0Ea6Fe3Ga4Hg0Ie2Je1Ka3Lb3Md1Nd2Ob4Qd4Rc3Sf1Tc4Ua5Vb2Wb1Xc1Yf0Zb60c52b03f24c25a26a17c08e09z91",
            "a7Aa0Bb5Cg1Dd0Ea6Fe3Ga4Hg0Ie2Je1Ka3Ld1Nd2Ob4Qd4Rc3Sf1Tc4Ua5Vb2Wb1Xc1Yf0Zb60d31c52b03f24c25a26a17c08e09z9M",
            "e3Ad4Ba5Cd1Dc1Eb3Fc5Gd2Hg0Ie0Ja2Kb5Lf1Md3Na6Ob1Qc3Rf2Sc4Tb0Uc0Ve1Wd0Xg1Ye2Zb60a71a32a03b24a45b46c28a19z97",
            "e3Ad4Ba5Cc1Eb3Fc5Gd2Hg0Ie0Ja2Kb5Lf1Md3Na6Ob1Qc3Rf2Sc4Tb0Uc0Ve1Wd0Xg1Ye2Zb60a71a32a03b24a45b46f07c28a19z9D",
            "e3Ad4Ba5Cd1Dc1Eb3Fc5Gd2Hg0Ia2Kb5Lf1Md3Na6Ob1Qc3Rf2Sc4Tb0Uc0Ve1Wd0Xg1Ye2Zb60a71a32a03b24a45b46f07c28a19z9J",
            "e3Ad4Ba5Cd1Dc1Eb3Fc5Gd2Hg0Ie0Ja2Kb5Lf1Md3Na6Ob1Qc3Rf2Sc4Tb0Uc0Ve1Wd0Xg1Ye2Zb60a32a03b24a45b46f07c28a19z91",
            "e3Ad4Ba5Cd1Dc1Eb3Fc5Gd2Hg0Ia2Kb5Lf1Md3Na6Ob1Qc3Rf2Sc4Tb0Uc0Ve1Wd0Xg1Ye2Zb60a71a32a03b24a45b46f07c28a19z9J",
            "g0Ac1Bb4Ca5Da2Ea6Ff0Gb1Ha3Id3Ja0Kc5Mb0Nf1Od2Pe1Qc2Re3Sb6Td0Ub5Va1Wb2Xc3Yb3Zc00e21e02d14f25a46g17c48d49z93",
            "g0Ac1Bb4Ca5Da2Ea6Ff0Gb1Ha3Id3Ja0Kc5Mb0Nf1Od2Pe1Qc2Re3Sb6Td0Ub5Va1Wc3Yb3Zc00e21e02a73d14f25a46g17c48d49z9X",
            "g0Ac1Bb4Ca5Da2Ea6Ff0Gb1Ha3Id3Ja0Kc5Mb0Nf1Od2Pe1Qc2Re3Sb6Td0Ub5Va1Wb2Xc3Yb3Zc00e21e02a73d14f25a46g17c48z99",
            "g0Ac1Bb4Ca5Da2Ea6Ff0Gb1Ha3Ia0Kc5Mb0Nf1Od2Pe1Qc2Re3Sb6Td0Ub5Va1Wb2Xc3Yb3Zc00e21e02a73d14f25a46g17c48d49z9J",
            "g0Ac1Bb4Ca5Da2Ef0Gb1Ha3Id3Ja0Kc5Mb0Nf1Od2Pe1Qc2Re3Sb6Td0Ub5Va1Wb2Xc3Yb3Zc00e21e02a73d14f25a46g17c48d49z9F"
    };

    private static String[] CorrectFurther = {
            "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"
    };

    private ArrayList<HashSet<String>> Correct(){// Create correct hashset
        ArrayList<HashSet<String>> corre = new ArrayList<>();
        for(String cor : CorrectString){
            HashSet<String> a = new HashSet<>();
            for(int i = 2; i < cor.length(); i += 3){
                a.add(String.valueOf(cor.charAt(i)));
            }
            corre.add(a);
        }
        return corre;
    }

    private ArrayList<HashSet<String>> CorrectFurther(){// Create correct furhter hashset
        ArrayList<HashSet<String>> ss = new ArrayList<>();
        for(String s : CorrectFurther){
            HashSet<String> a = new HashSet<>();
            for(int i = 2; i < s.length(); i += 3){
                a.add(String.valueOf(s.charAt(i)));
            }
            ss.add(a);
        }
        return ss;
    }




    @Test
    public void testGood(){ // Test the good condition of updateBoard method
        for (int i = 0; i < PLACEMENTS.length ; i++) {
            String setup = TestUtility.shufflePlacement(PLACEMENTS[i]);
            for(int j = 0; j < TestUtility.MOVE_SEQUENCES[i].length; j++){
                String moveSequence = TestUtility.MOVE_SEQUENCES[i][j];
                String a = WarringStatesGame.updateBoard(setup,moveSequence);
                HashSet<String> b = new HashSet<>();
                for(int h = 2; h < a.length(); h += 3){
                    b.add(String.valueOf(a.charAt(h)));
                }
                assertTrue("The new placement String " + a + " is not correct.",Correct().get(5*i + j).equals(b));
            }
        }
    }

    @Test
    public void testSameRowColumn(){ // Test the movement is in the same row or column or not
        for(int i = 0; i < PLACEMENTS.length; i++) {
            String setup = TestUtility.shufflePlacement(PLACEMENTS[i]);
            for(int j = 0;  j < TestUtility.ILLEGAL_MOVE_ROW_COL[i].length; j++){
                String moveSequence = String.valueOf(TestUtility.ILLEGAL_MOVE_ROW_COL[i][j]);
                String a = WarringStatesGame.updateBoard(setup, moveSequence);
                assertFalse("The move " + moveSequence + " is not in the same row or column as Zhang Yi, but was accepted.",setup.length() >= a.length());
            }
        }
    }

    @Test
    public void testFurther(){ // Test the valid movement of the same country with further location
        ArrayList<HashSet<String>> c = new ArrayList<>();
        for(int i = 0; i < PLACEMENTS.length; i++) {
            String setup = TestUtility.shufflePlacement(PLACEMENTS[i]);
            for(int j = 0;  j < TestUtility.ILLEGAL_MOVE_FURTHER[i].length; j++){
                String moveSequence = String.valueOf(TestUtility.ILLEGAL_MOVE_FURTHER[i][j]);
                String a = WarringStatesGame.updateBoard(setup, moveSequence);
                HashSet<String> b = new HashSet<>();
                for(int h = 2; h < a.length(); h += 3){
                    b.add(String.valueOf(a.charAt(h)));
                }
                c.add(b);
            }
        }
        for(int i = 0; i < c.size(); i ++){
            assertTrue("The further movement is not correct.",c.get(i).equals(CorrectFurther().get(i)));
        }
    }

    @Test
    public void testEmptyMoveSequence(){ // Test the move sequence is empty or not
        for(int i = 0; i < PLACEMENTS.length; i++){
            String setup = TestUtility.shufflePlacement(PLACEMENTS[i]);
            String moveSequence = " ";
            assertFalse("The move sequence cannot be empty, but was accepted.",WarringStatesGame.updateBoard(setup,moveSequence).equals(setup));
        }
    }

}
