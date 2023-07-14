package com.cryptotal.service.core.dao;

import com.cryptotal.service.core.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionDao extends  AbstractHibernateDao<Transaction> {

    public TransactionDao() {
        setClassName(Transaction.class);
    }

    public Transaction getTransactionById(Long id) {
        return this.findByLongId(id);
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        return getAllTransactions().stream()
                .filter(transaction -> transaction.getAccountId() == accountId)
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllTransactions() {
        return this.getAll();
    }

    public Long addTransaction(Transaction transaction) {
        try {
            Long id = (Long) getCurrentSession().save(transaction);
            return id;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void removeTransaction(int id) {
        this.remove(this.findById(id));
    }

    public void updateTransaction(Transaction transaction) {
        this.update(transaction);
    }
}
