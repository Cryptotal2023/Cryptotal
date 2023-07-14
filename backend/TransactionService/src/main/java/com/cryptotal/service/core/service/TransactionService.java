package com.cryptotal.service.core.service;

import com.cryptotal.service.core.dao.TransactionDao;
import com.cryptotal.service.core.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionService {

    private TransactionDao transactionDao;

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
    @Transactional
    public Transaction getTransactionById(Long id) {
        return transactionDao.getTransactionById(id);
    }
    @Transactional
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        return transactionDao.getTransactionsByAccountId(accountId);
    }
    @Transactional
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }
    @Transactional
    public Long addNewTransaction(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }
    @Transactional
    public void removeTransaction(int id) {
        transactionDao.removeTransaction(id);
    }
    @Transactional
    public void updateTransaction(Transaction transaction) {
        transactionDao.updateTransaction(transaction);
    }
}
