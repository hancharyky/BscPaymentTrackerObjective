package bsc.example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class represent multi-currency bank account which store information about state of client net amounts
 *
 * @author Yahor
 */
public class BankAccount {
    private final Map<String, BigDecimal> myAccounts;

    public BankAccount() {
        myAccounts = new HashMap<>();

        Arrays.stream(Currency.values()).forEach(currency -> {
            myAccounts.put(currency.toString(), BigDecimal.valueOf(0L));
        });
    }

    public Map<String, BigDecimal> getMyAccounts() {
        return myAccounts;
    }

    /**
     *
     * Method execute main logic of adding or substruction to an account.
     * If there is no account with such currency code, it will be created
     *
     * @param accountCurrency
     * @param amountToAdd
     */
    public synchronized void transaction(String accountCurrency, BigDecimal amountToAdd){
        BigDecimal previousAmount = myAccounts.get(accountCurrency);
        if(previousAmount == null){
            myAccounts.put(accountCurrency, amountToAdd);
        } else{
            BigDecimal newAccountAmount = previousAmount.add(amountToAdd);
            if(newAccountAmount.compareTo(BigDecimal.ZERO) < 0){
                System.out.println("There is no sufficient amount of money in your account. Please change the payment amount");
            } else {
                myAccounts.put(accountCurrency, newAccountAmount);
            }
        }


    }

    /**
     *
     * @return String representation of bank account without empty accounts
     */
    @Override
    public String toString() {
        return ""+
                myAccounts.entrySet().stream()
                .filter(x -> !x.getValue().equals(BigDecimal.ZERO))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
