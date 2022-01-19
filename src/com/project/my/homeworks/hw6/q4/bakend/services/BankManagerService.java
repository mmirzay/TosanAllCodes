package com.project.my.homeworks.hw6.q4.bakend.services;

import com.project.my.homeworks.hw6.q4.bakend.api.BankManagerInterface;
import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.BankException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.ServiceException;
import com.project.my.homeworks.hw6.q4.bakend.repositories.*;

import java.util.List;

public class BankManagerService implements BankManagerInterface {
    private final BankRepository bankRepository = new BankRepository();

    @Override
    public List<Bank> getAllBanksList() {
        return bankRepository.fetchAllBanks();
    }

    @Override
    public Bank getBankByCode(int bankCode) throws ServiceException {
        try {
            return bankRepository.fetchBankByCode(bankCode);
        } catch (BankException e) {
            throw new ServiceException(e);
        }
    }
}