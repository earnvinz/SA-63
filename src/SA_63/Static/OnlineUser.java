package SA_63.Static;

import SA_63.Model.Member;

public class OnlineUser {
    private static Member online;

    public static Member getOnline() {
        return online;
    }

    public static void setOnline(Member online) {
        OnlineUser.online = online;
    }
}
