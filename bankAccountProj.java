import java.util.ArrayList;
import java.util.Scanner;


public class bankAccountProj
{
    public static void main(String[] args)
    {
        //04/28/2008 9999 Seb       30     17 20

        //08/08/2004 3333 Mike      5      19 50

        //05/17/2016 5723 Samantha  42.17  27 70

        //09/08/1995 8136 Lucas     73.45  44 30

        //02/14/1983 6958 Emily     28.76  56 33

        //Date
        //SSN
        //Name
        //Balance
        //Account Number
        //Age
        //Withdrawal Limit



        Scanner scan = new Scanner(System.in);
        ArrayList<bankAccount> thresholdAccounts = null;
        bank bank = new bank();
        bankAccount bankAccount = null;
        int choice = 0;
        bankAccount currentAccount = null;
        final String filePath = "/Users/schmay/Downloads/BankAccountProj/BankAccounts.txt";
        // zero accounts in array problem
        // date syntax
        // nan response
        // random numbers generate

        // person class
       try
       {
           bank.load(filePath);
       }
       catch(Exception e)
       {
           System.out.println("Could not load accounts");
       }



        while(choice != 7)
        {
            System.out.println("Welcome to the Bank, what can we do for you today");
            System.out.println("1. Create a new Account");
            System.out.println("2. Perform Operations in an existing account");
            System.out.println("3. Delete an existing account");
            System.out.println("4. Display the average of all account balances");
            System.out.println("5. Display the maximum and minimum account balances");
            System.out.println("6. Display all accounts that have low balance");
            System.out.println("7. Quit");
            int accountNumber = 0;
            boolean generatedAcctNM = bank.validateAccountNumber();
            for(int i = 0; i < 30; i++)
            {

                accountNumber = bank.getGeneratedAccountNumber();
                System.out.println(accountNumber);
            }



            choice = scan.nextInt();
            if(choice == 1)
            {

                System.out.println("In order to create an account we need personal info, please enter the" +
                        " Date, your SSN, Name, Minimum Deposit, Age and Withdrawal Limit. " + "\n" +"When entering your SSN" +
                        " please make sure it begins with" +
                        " a digit that is 1000 or greater and it has 4 digits");

                String dateCreated = scan.next();
                int SSN = scan.nextInt();
                String name = scan.next();
                double minimumDeposit = scan.nextDouble();
                int age = scan.nextInt();
                double withdrawalLimit = scan.nextDouble();




                // generates a random number and validates it while it is not equal


                bankAccount = new bankAccount(dateCreated, SSN, name, minimumDeposit,accountNumber, age, withdrawalLimit);
                // set up top variable to null because if variable is set here all the methods
                // have an error because they can't point anywhere
                bank.addBankAccount(bankAccount);
                System.out.println(bankAccount.showAccounts());
            }
            else if(choice == 2)
            {
                System.out.println("Please enter the account number in which you would like to use");
                int searchAccountNumber = scan.nextInt();
                currentAccount = bank.findAccount(searchAccountNumber);
                if (currentAccount != null) {
                    System.out.println("Welcome \n1) Check Balance \n" +
                            "2) Deposit Money\n3) Withdraw Money");
                    choice = scan.nextInt();
                    if (choice == 1) {
                        System.out.println("Current balance is: " + currentAccount.getCurrentBalance());
                    } else if (choice == 2) {
                        System.out.println("How much money would you like to deposit: ");
                        double depositAmount = scan.nextDouble();
                        boolean success = currentAccount.depositMoney(depositAmount);

                        if (success) {
                            System.out.println("Current Balance is: " + currentAccount.getCurrentBalance());
                        } else {
                            System.out.println("Invalid Deposit amount!");
                        }


                    } else if (choice == 3) {
                        System.out.println("How much money would you like to withdraw: ");
                        double withdrawAmount = scan.nextDouble();
                        boolean success = currentAccount.withdrawMoney(withdrawAmount);
                        if (success) {
                            System.out.println("Current Balance is: " + currentAccount.getCurrentBalance());
                        } else {
                            System.out.println("WITHDRAWAL AMOUNT TO HIGH");
                        }
                    }
                }
                else
                {
                    System.out.println("Account was not found please try again");
                }
            }
            else if(choice == 3)
            {
                System.out.println("Please enter account number of which you want to delete");
                int searchAccountNumber = scan.nextInt();
                boolean success = bank.deleteAccount(searchAccountNumber);
                if(success)
                {
                    System.out.println("Account was successfully deleted");
                }
                else
                    System.out.println("Account Was Not Found");
            }
            else if(choice == 4)
            {
                double averageBalance = bank.findAverageBalance();
                System.out.println("Average Balances of All Accounts is: " + averageBalance);
            }
            else if(choice == 5)
            {
                double minimumBalance = bank.findMinCurrentBalance();
                double maximumBalance = bank.findMaxCurrentBalance();
                System.out.println("Maximum Balance:" + maximumBalance + " Minimum Balance: " + minimumBalance);
            }
            else if(choice == 6)
            {
                System.out.println("Accounts with balances under Threshold limit are: ");
                thresholdAccounts = bank.thresholdAccounts();
                for(int i = 0; i < thresholdAccounts.size(); i++)
                {
                    bankAccount exAccount = thresholdAccounts.get(i);
                    System.out.println("Account Name: " + exAccount.getName());
                    System.out.println("Account Number: " + exAccount.getAccountNumber());
                    System.out.println("Account Current Balance: " + exAccount.getCurrentBalance());
                }
            }
            else if(choice == 7)
            {
                System.out.println("Thank you for using the Bank!");
                try
                {
                    bank.save(filePath);
                }
                catch(Exception e)
                {
                    System.out.println("Unable to save bank account to file");
                }
                break;
            }
        }

    }
}
