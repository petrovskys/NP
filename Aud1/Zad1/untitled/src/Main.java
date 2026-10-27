

public class Main {
    interface InterestBearingAccount{
        void addInterest();
    }

    abstract class Account{
        private String name;
        private int number;
        private double balance;

        public Account(String name, int number, double balance){
            this.name = name;
            this.number = number;
            this.balance = balance;
        }
        public double getBalance(){
            return balance;
        }
        public void addBalance(double balance){
            this.balance += balance;
        }
        public void removeBalance(double balance){
            this.balance -= balance;
        }
    }

    class NonInterestCheckingAccount  extends Account{
        public NonInterestCheckingAccount(String name, int number, double balance){
            super(name, number, balance);
        }
    }

    class InterestCheckingAccount  extends Account implements InterestBearingAccount{
        public static final double currentInterestRate = 0.03;
        public InterestCheckingAccount(String name, int number, double balance){
            super(name, number, balance);
        }
        @Override
        public void addInterest(){
            this.addBalance(this.getBalance()*currentInterestRate);
        }
    }

    class PlatinumCheckingAccount extends InterestCheckingAccount{
        public  PlatinumCheckingAccount(String name, int number, double balance){
            super(name,number,balance);
        }
        @Override
        public void addInterest(){
            this.addBalance(this.getBalance()*(currentInterestRate*2));
        }
    }

    class Bank{
        Account[] accounts;
        private int totalAccounts;
        private int max;
        public Bank(int max){
            this.max = max;
            this.totalAccounts = 0;
            this.accounts = new Account[max];
        }


        public double totalAssets(){
            double total = 0;
            for(Account account : accounts){
                total += account.getBalance();
            }
            return total;
        }
        public void addingInterest(){
            for(Account account : accounts){
                if (account instanceof InterestBearingAccount){
                    ((InterestBearingAccount) account).addInterest();
                }
            }
        }
    }


    public static void main(String[] args) {}
}