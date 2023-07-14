package com.cryptotal.service.core.dao;

import com.cryptotal.service.core.entity.CryptoTradePairData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CryptoTradePairDataDao extends  AbstractHibernateDao<CryptoTradePairData> {

    public CryptoTradePairDataDao() {
        setClassName(CryptoTradePairData.class);
    }

    public Optional<CryptoTradePairData> getCryptoTradePairDataByIds(int accountId, String cryptoAsset, String tradePairAsset) {
        return getAllCryptoTradePairData().stream()
                .filter(cryptoTradePairData -> (cryptoTradePairData.getAccountId() == accountId
                        && cryptoTradePairData.getCryptoAsset().equals(cryptoAsset)
                        && cryptoTradePairData.getTradePairAsset().equals(tradePairAsset)))
                .findAny();
    }

    public List<CryptoTradePairData> getCryptoTradePairDataByAccountId(int accountId) {
        return getAllCryptoTradePairData().stream()
                .filter(cryptoTradePairData -> cryptoTradePairData.getAccountId() == accountId)
                .collect(Collectors.toList());
    }

    public List<CryptoTradePairData> getAllCryptoTradePairData() {
        return this.getAll();
    }

    public Integer addCryptoTradePairData(CryptoTradePairData cryptoTradePairData) {
        return this.add(cryptoTradePairData);
    }

    public void removeCryptoTradePairData(int id) {
        this.remove(this.findById(id));
    }

    public void updateCryptoTradePairData(CryptoTradePairData cryptoTradePairData) {
        this.update(cryptoTradePairData);
    }
}
