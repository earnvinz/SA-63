package SA_63.Model;

public class Deposit_Withdraw implements Comparable<Deposit_Withdraw>{
    private String DepWith_ID;
    private String date;
    private String amount;
    private String account_ID;
    private String status;
    private String final_balance;
    public Deposit_Withdraw(String depWith_ID, String date, String amount, String account_ID, String status) {
        DepWith_ID = depWith_ID;
        this.date = date;

        this.amount = amount;
        this.account_ID = account_ID;
        this.status = status;

    }

    public String getDepWith_ID() {
        return DepWith_ID;
    }

    public void setDepWith_ID(String depWith_ID) {
        DepWith_ID = depWith_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(String account_ID) {
        this.account_ID = account_ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    public void setFinal_balance(String a){
        this.final_balance = a;
    }
    public Double find_total(double total){
        Double result;
        if(this.status.equals("CDP")) {
            result = total + Double.parseDouble(this.amount);
            setFinal_balance(Double.toString(result));
            this.amount = "+ "+amount;
            return result;
        }
        else{
            result = total - Double.parseDouble(this.amount);
            setFinal_balance(Double.toString(result));
            this.amount = "- "+amount;
            return result;
        }


    }

    public String getFinal_balance() {
        return final_balance;
    }

    @Override
    public int compareTo(Deposit_Withdraw o) {
        int thisdep = Integer.parseInt(this.getDepWith_ID());
        int thatdep = Integer.parseInt(o.getDepWith_ID());
        if(thisdep < thisdep){
            return -1;
        }
        else if(thisdep > thatdep){
            return 1;
        }
        return 0;
    }
}
