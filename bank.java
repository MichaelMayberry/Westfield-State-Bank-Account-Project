import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class bank
{
    private ArrayList<bankAccount> bankAccounts;
    private Random random = new Random();
    private int generatedAccountNumber;
    private final double BALANCE_THRESHOLD = 10;

    public bank()
    {

        bankAccounts = new ArrayList<bankAccount>();
        generatedAccountNumber = random.nextInt(1) + 1902;
    }


    public int getGeneratedAccountNumber() {return generatedAccountNumber;}

    public void setGeneratedAccountNumber(int generatedAccountNumber)
    {
        if(generatedAccountNumber > 999)
        {
            this.generatedAccountNumber = generatedAccountNumber;
        }
    }

    public void addBankAccount(bankAccount bankAccount){bankAccounts.add(bankAccount);}
    // in main search for each SSN and account number, so they don't duplicate;

    public bankAccount findAccount(int accountNumber)
    {
        bankAccount foundAccount = null;

        for(int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            // bankAccount exAccount is being set to the object in the array position of i
            // if account has the same number as the accountNumber than that is the searched account
            // the if statement checks is the numbers match and then allocates it to the null object

            if(exAccount.getAccountNumber() == accountNumber)
            {
                foundAccount = exAccount;
                break;
                // break is used to stop the for loop otherwise it will just keep searching until
                // the end of the array
            }
        }

        return foundAccount;
    }
    public boolean deleteAccount(int accountNumber)
    {
        boolean success = false;
        int accountPosition = -1;  // not working prop

        for(int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            // bankAccount exAccount is being set to the object in the array position of i
            // if account has the same number as the accountNumber than that is the searched account
            // the if statement checks is the numbers match and then allocates it to the null object

            if(exAccount.getAccountNumber() == accountNumber)
            {
                accountPosition = i;

                // break is used to stop the for loop otherwise it will just keep searching until
                // the end of the array
            }

            if(accountPosition != -1)
            {
                bankAccounts.remove(accountPosition);
                success = true;
                break;
                // removes bank account object at its position in the array
            }

        }

        return success;
    }

    public double findAverageBalance()
    {
        int numberOfAccounts = bankAccounts.size();
        double answer = 0;
        double totalMoney = 0;

        for(int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            // sets exAccount object equal to current object in array at I position
            totalMoney = totalMoney + exAccount.getCurrentBalance();
            // finds total amount of money currently in bank
        }
        answer = totalMoney / numberOfAccounts;
        return answer;
    }

    public double findMaxCurrentBalance()
    {
        double highestCurrentBalance = 0;

        for(int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            // sets exAccount object equal to current object in array at I position
            if(exAccount.getCurrentBalance() > highestCurrentBalance)
            {
                // replaces variable everytime a new highest account balanced is reached
                highestCurrentBalance = exAccount.getCurrentBalance();
            }
        }
        return highestCurrentBalance;

    }
    public double findMinCurrentBalance()
    {
        double lowestCurrentBalance = Integer.MAX_VALUE;

        for(int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            // sets exAccount object equal to current object in array at I position
            if(exAccount.getCurrentBalance() < lowestCurrentBalance)
            {
                // replaces variable everytime a new lowest account balanced is reached
                lowestCurrentBalance = exAccount.getCurrentBalance();
            }
        }
        return lowestCurrentBalance;

    }
    public ArrayList<bankAccount> thresholdAccounts()
    {
        ArrayList<bankAccount> thresholdAccounts = new ArrayList<bankAccount>();

        for(int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            // sets exAccount object equal to current object in array at I position
            if(exAccount.getCurrentBalance() < BALANCE_THRESHOLD)
            {
                thresholdAccounts.add(exAccount);
            }
        }
        return thresholdAccounts;
    }
    public boolean validateAccountNumber()
    {
        int exAccountNumber;
        boolean success = true;
        for (int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            exAccountNumber = exAccount.getAccountNumber();
            if(exAccountNumber == generatedAccountNumber)
            {
                success = false;
                generatedAccountNumber = random.nextInt(1) + 1902;
            }

        }
        return success;
    }

    public void save(String filePath) throws Exception
    {

        FileOutputStream fos = new FileOutputStream(filePath);
        // finds file
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        //writes to file using for loop
        for (int i = 0; i < bankAccounts.size(); i++)
        {
            bankAccount exAccount = bankAccounts.get(i);
            osw.write(exAccount.showAccounts());
        }
        osw.close();
        fos.close();
    }
    public void load(String filePath) throws Exception
    {

        FileInputStream fis = new FileInputStream(filePath);
        Scanner fileScan = new Scanner(fis);
        String text = "";
        String line = "";

        while (fileScan.hasNextLine())
        {
            line = fileScan.nextLine();

            if (line.equals("------------------------------------------------------------------"))
            {

                bankAccount EXbankAccount = new bankAccount(" ", 0, " ", 0, 0, 0, 0);
                EXbankAccount.loadAccount(text);
                bankAccounts.add(EXbankAccount);
                text = "";
            }
            else
                text += line + "\n";
        }


    }
}
