import java.util.ArrayList;
import java.util.Arrays;

public interface Client extends Iterable<Account>, Comparable<Client> {
    public String getTitle();

    public void setTitle(String title);

    public boolean add(Account account);

    public boolean add(Account account, int index);

    public Account get(int index);

    public Account get(String number);

    public boolean hasAccountWithNumber(String number);

    public Account set(Account account, int index);

    public Account remove(int index);

    public Account remove(String number);

    public boolean remove(Account account);

    public int size();

    public Account[] getAccounts();

    public int indexOf(String number);

    public int indexOf(Account account);

    public int getCreditScore();

    public void addCreditScore(int creditScore);

    public default Account[] getCreditAccounts(){
        ArrayList<Account> acs = new ArrayList<>();
        for(Account account:this){
            if(account instanceof CreditAccount) acs.add(account);
        }
        return (Account[]) acs.toArray();
    }

    public default Account[] getSortedAccountsByBalance(){
        Account[] buf = getAccounts().clone();
        Arrays.sort(buf);
        return buf;
    }

    public default double getTotalBalance(){
        double totalBalance = 0;
        for (Account account : this) {
            totalBalance+=account.getBalance();
        }
        return totalBalance;
    }

    public default double debtTotal(){
        double totalDebt = 0;
        for(Account account:this){
            if(account.getBalance()<0) totalDebt+=account.getBalance();
        }
        return Math.abs(totalDebt);
    }

    public default ClientStatus getStatus() {
        if (getCreditScore() >= ClientStatus.PLATINUM.getCreditScoreBound()) return ClientStatus.PLATINUM;
        if (getCreditScore() >= ClientStatus.GOLD.getCreditScoreBound()) return ClientStatus.GOLD;
        if (getCreditScore() >= ClientStatus.GOOD.getCreditScoreBound()) return ClientStatus.GOOD;
        if (getCreditScore() >= ClientStatus.RISKY.getCreditScoreBound()) return ClientStatus.RISKY;
        else return ClientStatus.BAD;
    }
}
