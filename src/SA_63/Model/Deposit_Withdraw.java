package SA_63.Model;

public class Deposit_Withdraw implements Comparable{
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
            cdp_cwd();
            result = total + Double.parseDouble(this.amount);
            setFinal_balance(Double.toString(result));

            return result;

        }
        else{
            cdp_cwd();
            result = total - Double.parseDouble(this.amount);
            setFinal_balance(Double.toString(result));

            return result;
        }


    }

    public String getFinal_balance() {
        return final_balance;
    }



    public void cdp_cwd(){
        if(this.getStatus().equals("CDP")){
            this.status = status+"( + )";
        }
        else{
            this.status = status+"( - )";
        }
    }
    @Override
    public int compareTo(Object o) {
        Deposit_Withdraw deposit_withdraw = (Deposit_Withdraw)o;



        if(Integer.parseInt(getDepWith_ID()) < Integer.parseInt(deposit_withdraw.getDepWith_ID())){
            return -1;
        }
        if(Integer.parseInt(getDepWith_ID()) > Integer.parseInt(deposit_withdraw.getDepWith_ID())){
            return 1;
        }
        return 0;

    }
}
