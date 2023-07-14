package com.cryptotal.service.core.service;

import com.cryptotal.service.core.dao.CryptoTradePairDataDao;
import com.cryptotal.service.core.entity.CryptoTradePairData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoTradePairDataService {

    private CryptoTradePairDataDao cryptoTradePairDataDao;

    @Autowired
    public void setCryptoTradePairDataDao(CryptoTradePairDataDao cryptoTradePairDataDao) {
        this.cryptoTradePairDataDao = cryptoTradePairDataDao;
    }
    @Transactional
    public Optional<CryptoTradePairData> getCryptoTradePairDataByIds(int accountId, String cryptoAsset, String tradePairAsset) {
        return cryptoTradePairDataDao.getCryptoTradePairDataByIds(accountId, cryptoAsset, tradePairAsset);
    }
    @Transactional
    public List<CryptoTradePairData> getCryptoTradePairDataByAccountId(int accountId) {
        return cryptoTradePairDataDao.getCryptoTradePairDataByAccountId(accountId);
    }
    @Transactional
    public List<CryptoTradePairData> getAllCryptoTradePairData() {
        return cryptoTradePairDataDao.getAllCryptoTradePairData();
    }
    @Transactional
    public Integer addNewCryptoTradePairData(CryptoTradePairData cryptoTradePairData) {
        return cryptoTradePairDataDao.addCryptoTradePairData(cryptoTradePairData);
    }
    @Transactional
    public void removeCryptoTradePairData(int id) {
        cryptoTradePairDataDao.removeCryptoTradePairData(id);
    }
    @Transactional
    public void updateCryptoTradePairData(CryptoTradePairData cryptoTradePairData) {
        cryptoTradePairDataDao.updateCryptoTradePairData(cryptoTradePairData);
    }
}
