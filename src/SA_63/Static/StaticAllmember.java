package SA_63.Static;

import SA_63.Model.Member;

import java.util.ArrayList;

public class StaticAllmember {
    private static ArrayList<Member> static_allmember;
    private static int countloop;
    public static ArrayList<Member> getStatic_allmember() {
        return static_allmember;
    }

    public static void setStatic_allmember(ArrayList<Member> static_allmember) {
        StaticAllmember.static_allmember = static_allmember;
    }

    public static int getCountloop() {
        return countloop;
    }

    public static void setCountloop(int countloop) {
        StaticAllmember.countloop = countloop;
    }
}
