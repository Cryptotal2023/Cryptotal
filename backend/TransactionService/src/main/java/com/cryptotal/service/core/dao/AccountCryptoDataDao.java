package com.cryptotal.service.core.dao;

import com.cryptotal.service.core.entity.AccountCryptoData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountCryptoDataDao extends  AbstractHibernateDao<AccountCryptoData> {

    public AccountCryptoDataDao() {
        setClassName(AccountCryptoData.class);
    }

    public Optional<AccountCryptoData> getAccountCryptoDataByIds(int accountId, String cryptoAsset) {
        return getAllAccountCryptoData().stream()
                .filter(accountCryptoData -> (accountCryptoData.getAccountId() == accountId && accountCryptoData.getCryptoAsset().equals(cryptoAsset)))
                .findAny();
    }

    public List<AccountCryptoData> getAccountCryptoDataByAccountId(int accountId) {
        return getAllAccountCryptoData().stream()
                .filter(accountCryptoData -> accountCryptoData.getAccountId() == accountId)
                .collect(Collectors.toList());
    }

    public List<AccountCryptoData> getAllAccountCryptoData() {
        return this.getAll();
    }

    public Integer addAccountCryptoData(AccountCryptoData accountCryptoData) {
        return this.add(accountCryptoData);
    }

    public void removeAccountCryptoData(int id) {
        this.remove(this.findById(id));
    }

    public void updateAccountCryptoData(AccountCryptoData accountCryptoData) {
        this.update(accountCryptoData);
    }
}
