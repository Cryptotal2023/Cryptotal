package com.cryptotal.service.core.connector;

import com.cryptotal.service.core.domain.TransactionService.CryptoTradePairDataDetail;
import com.cryptotal.service.core.domain.messagequeue.TransactionDetail;
import com.cryptotal.service.core.entity.AccountService.AccountDetail;

import java.util.List;
import java.util.Map;

public interface ExchangeConnector {
    AccountDetail getAccountDetail();

    boolean checkAccountStatus();

    List<TransactionDetail> getTransactionHistory(String asset, String tradePair, Long fromId);

    List<String> getUserAssetList();

    List<CryptoTradePairDataDetail> getDefaultCryptoTradePairDataDetailList();

    Map<String, CryptoTradePairDataDetail> getDefaultCryptoTradePairDataDetailMap();

}
