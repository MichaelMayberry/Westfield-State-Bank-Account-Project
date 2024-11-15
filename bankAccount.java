import java.util.Scanner;
public class bankAccount
{
private int accountNumber;
private String dateCreated;
private double currentBalance;
private double withdrawLimit = 30;
private double totalWithdrawn;
private int age;
private String name;
private int SSN;
private double minDeposit;

public bankAccount(String dateCreated, int SSN, String name, double minDeposit, int accountNumber, int age, double withdrawLimit)
{
this.accountNumber = accountNumber;
this.dateCreated = dateCreated;
currentBalance = 0.0 + minDeposit;
totalWithdrawn = 0;
this.SSN = SSN;
this.name = name;
this.minDeposit = minDeposit;
this.age = age;
this.withdrawLimit = withdrawLimit;
}
    public String getName(){return name;}

    public void setName(String name)
    {
        if (!name.equals(""))
            this.name = name;
    }
    public int getAge()
{
    return age;
}
    public void setAge(int newAge) {
        if (newAge >= 0)
            age = newAge;
    }

    public double getMinDeposit() {return minDeposit;}
    public void setMinDeposit(double minDeposit)
    {
        if(minDeposit > 0)
        {
            this.minDeposit = minDeposit;
        }
    }

    public double getSSN(){return SSN;}
    public void setSSN(int SSN)
    {
        if(SSN > 999)
        {
            this.SSN = SSN;
        }
    }


    public int getAccountNumber() {return accountNumber;}
    public void setAccountNumber(int accountNumber)
    {
        if(accountNumber > 999)
        {
            this.accountNumber = accountNumber;
        }
    }

    public String getDateCreated(){return dateCreated;}
    public void setDateCreated(String dateCreated)
    {
        if(!dateCreated.isEmpty())
        {
            this.dateCreated = dateCreated;
        }
    }
    public double getCurrentBalance(){return currentBalance;}
    public void setCurrentBalance(double currentBalance)
    {
        if(currentBalance != 0.00)
        {
            this.currentBalance = currentBalance;
        }
    }
    public boolean depositMoney(double depositAmount)
    {
        boolean depositMoneySuccess = false;
        if(depositAmount > 0.0)
        {
            currentBalance += depositAmount;
            depositMoneySuccess = true;
        }
        return depositMoneySuccess;
    }
    public boolean withdrawMoney(double withdrawAmount)
    {
        boolean withdrawMoneySuccess = false;
        if(withdrawLimit > totalWithdrawn + withdrawAmount && withdrawAmount < currentBalance && withdrawAmount <= withdrawLimit)
        {
            totalWithdrawn += withdrawAmount;
            currentBalance -= withdrawAmount;
            withdrawMoneySuccess = true;
        }
        return withdrawMoneySuccess;
    }

    public String toString()
    {
        String bankInfo = "Account Number = " + accountNumber + "\n" +
                "Date Created: " + dateCreated +
                "\n" + "Current Balance = " + currentBalance;
        return bankInfo;

    }
    //04/28/2008 9999 Seb       30    6789 16  200
    public String showAccounts()
    {
        String text = "";
        text += "\n" +  dateCreated + "\n" +  SSN +
                "\n" +  name + "\n" +  currentBalance + "\n"
                +  accountNumber + "\n" +   age + "\n" +  withdrawLimit + "\n";
        text += "------------------------------------------------------------------";
        return text;
        // how account information should appear in BankAccounts.txt

    }
    public void loadAccount(String text)
    {
        Scanner strScan = new Scanner(text);

        dateCreated = strScan.next();
        SSN = strScan.nextInt();
        name = strScan.next();
        currentBalance = strScan.nextDouble();
        accountNumber = strScan.nextInt();
        age = strScan.nextInt();
        withdrawLimit = strScan.nextDouble();


        // loads the information from the account in the selected position in the array
    }


}

