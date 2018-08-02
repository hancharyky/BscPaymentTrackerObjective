package bsc.example.objective.service;

import bsc.example.objective.model.BankAccount;
import bsc.example.objective.model.Payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Outputs list of non-zero amounts in bank account.
 *
 * @author Yahor
 */
public class OutputProcessorServiceImpl implements OutputProcessorService {

    private BankAccountServiceImpl bankAccountService;

    public OutputProcessorServiceImpl(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void printOut() {
        Collection<Payment> myAccount = bankAccountService.getBankAccount().values();
        myAccount.stream().filter(x -> !x.getAmount().equals(BigDecimal.ZERO)).forEach(System.out::println);
    }


}
