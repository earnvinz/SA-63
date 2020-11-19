package SA_63.Static;

import SA_63.Model.Deposit_Withdraw;

import java.util.ArrayList;

public class Count_history_transaction {
    private static int count;
    private static ArrayList<Deposit_Withdraw> history;
    private static ArrayList<Deposit_Withdraw> back;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Count_history_transaction.count = count;
    }

    public static ArrayList<Deposit_Withdraw> getHistory() {
        return history;
    }

    public static ArrayList<Deposit_Withdraw> getBack() {
        return back;
    }

    public static void setBack(ArrayList<Deposit_Withdraw> back) {
        Count_history_transaction.back = back;
    }

    public static void setHistory(ArrayList<Deposit_Withdraw> history) {
        Count_history_transaction.history = history;
    }
}
