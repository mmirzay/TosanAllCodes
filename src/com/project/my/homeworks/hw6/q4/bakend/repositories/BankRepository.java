package com.project.my.homeworks.hw6.q4.bakend.repositories;

import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.BankException;

import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    private List<Bank> banks;

    public BankRepository() {
        banks = new ArrayList<>();
        initBanksList();
    }

    private void initBanksList() {
        banks.add(new Bank("Tejarat", 18));
        banks.add(new Bank("Mellat", 12));
        banks.add(new Bank("Sepah", 15));
        banks.add(new Bank("Saderat", 19));
    }

    public Bank getBankByCode(int code) throws BankException {
        return banks.stream()
                .filter(b -> b.getCode() == code)
                .findFirst().orElseThrow(() -> new BankException("bank code is not exist."));
    }

    public Bank getBankCodeByName(String name) throws BankException {
        return banks.stream()
                .filter(b -> b.getName().equals(name))
                .findFirst().orElseThrow(() -> new BankException("bank name is not exist."));
    }
}
