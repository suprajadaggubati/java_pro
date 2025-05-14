import java.text.DecimalFormat;

public class Account {
    private double checkingBalance = 0;
    private double savingBalance = 0;
    private final DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public String getFormattedCheckingBalance() {
        return moneyFormat.format(checkingBalance);
    }

    public String getFormattedSavingBalance() {
        return moneyFormat.format(savingBalance);
    }

    public boolean withdrawFromChecking(double amount) {
        if (checkingBalance >= amount) {
            checkingBalance -= amount;
            return true;
        }
        return false;
    }

    public boolean withdrawFromSaving(double amount) {
        if (savingBalance >= amount) {
            savingBalance -= amount;
            return true;
        }
        return false;
    }

    public void depositToChecking(double amount) {
        checkingBalance += amount;
    }

    public void depositToSaving(double amount) {
        savingBalance += amount;
    }
}
