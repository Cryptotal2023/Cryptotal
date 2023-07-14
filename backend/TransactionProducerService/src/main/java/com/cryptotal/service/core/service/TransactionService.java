package com.cryptotal.service.core.service;

import com.cryptotal.service.core.domain.TransactionService.CryptoTradePairDataDetail;
import com.cryptotal.service.core.domain.response.GeneralResponse;

import com.cryptotal.service.core.service.remote.RemoteTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private RemoteTransactionService remoteTransactionService;

    @Autowired
    public void setRemoteAccountService(RemoteTransactionService remoteTransactionService) {
        this.remoteTransactionService = remoteTransactionService;
    }
    public List<CryptoTradePairDataDetail> getCryptoTradePairDataDetailListByAccountId(Integer accountId) {

        GeneralResponse<List<CryptoTradePairDataDetail>> response = remoteTransactionService.getCryptoTradePairDataByAccountId(accountId);
        if (response.getStatus().isSuccess()) {
            System.out.println("[TransactionService] resp: " + response.getBody());
            return response.getBody();
        }

        return new ArrayList<CryptoTradePairDataDetail>();
        /*CryptoTradePairDataDetail cd1 = new CryptoTradePairDataDetail("BTC", "USDT", 1000L);
        CryptoTradePairDataDetail cd2 = new CryptoTradePairDataDetail("ETH", "USDT", 8787L);
        CryptoTradePairDataDetail cd3 = new CryptoTradePairDataDetail("XRP", "USDT", 1234L);
        List<CryptoTradePairDataDetail> cryptoTradePairDataDetailList= new ArrayList<CryptoTradePairDataDetail>();
        cryptoTradePairDataDetailList.add(cd1);
        cryptoTradePairDataDetailList.add(cd2);
        cryptoTradePairDataDetailList.add(cd3);
        return cryptoTradePairDataDetailList;*/
    }

}
