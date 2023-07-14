package com.cryptotal.service.core.service;

import com.cryptotal.service.core.dao.AccountCryptoDataDao;
import com.cryptotal.service.core.entity.AccountCryptoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountCryptoDataService {

    private AccountCryptoDataDao accountCryptoDataDao;

    @Autowired
    public void setAccountCryptoDataDao(AccountCryptoDataDao accountCryptoDataDao) {
        this.accountCryptoDataDao = accountCryptoDataDao;
    }
    @Transactional
    public Optional<AccountCryptoData> getAccountCryptoDataByIds(int accountId, String cryptoAsset) {
        return accountCryptoDataDao.getAccountCryptoDataByIds(accountId, cryptoAsset);
    }
    @Transactional
    public List<AccountCryptoData> getAccountCryptoDataByAccountId(int accountId) {
        return accountCryptoDataDao.getAccountCryptoDataByAccountId(accountId);
    }
    @Transactional
    public List<AccountCryptoData> getAllAccountCryptoData() {
        return accountCryptoDataDao.getAllAccountCryptoData();
    }
    @Transactional
    public Integer addNewAccountCryptoData(AccountCryptoData accountCryptoData) {
        return accountCryptoDataDao.addAccountCryptoData(accountCryptoData);
    }
    @Transactional
    public void removeAccountCryptoData(int id) {
        accountCryptoDataDao.removeAccountCryptoData(id);
    }
    @Transactional
    public void updateAccountCryptoData(AccountCryptoData accountCryptoData) {
        accountCryptoDataDao.updateAccountCryptoData(accountCryptoData);
    }
}
