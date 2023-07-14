package com.cryptotal.service.core.service;
import com.cryptotal.service.core.domain.AccountService.SyncStatus;
import com.cryptotal.service.core.domain.TransactionService.CryptoTradePairDataDetail;
import com.cryptotal.service.core.domain.messagequeue.TransactionDetail;
import com.cryptotal.service.core.connector.ExchangeConnector;
import com.cryptotal.service.core.domain.messagequeue.TransactionMessage;
import com.cryptotal.service.core.entity.AccountService.AccountDetail;
import com.cryptotal.service.core.util.SerializeUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionProducerService {

    private final Integer BATCH_THRESHOLD = 200;

    private TransactionService transactionService;

    private AccountService accountService;
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Autowired
    public void setService(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }
    public boolean summaryAssetHistory(ExchangeConnector connector) {
        /*if (!connector.checkAccountStatus()) {
            return false;
        }*/
        Integer batchId = 0;
        Integer accountId = connector.getAccountDetail().getId();

        List<TransactionDetail> transactionDetailList = new ArrayList<>();

        // 1. Fetch TradePair either from database or exchange
        Map<String, CryptoTradePairDataDetail> cryptoTradePairDataDetailMap = getCryptoTradePairDataDetailListBySyncStatus(connector);
        System.out.println(cryptoTradePairDataDetailMap.get("ETHUSDT"));
        // 2. Traverse all TradePair and fetch transaction from exchange
        for (CryptoTradePairDataDetail detail : cryptoTradePairDataDetailMap.values()) {
            System.out.println("[TransactionProducerService] detail: " + detail);
            List<TransactionDetail> newTransactionDetailList =
                    connector.getTransactionHistory(detail.getCryptoAsset(), detail.getTradePairAsset(), detail.getTradeId());
            transactionDetailList.addAll(newTransactionDetailList);
            // 3. Package transactions into batches and send transaction to RabbitMQ
            if (transactionDetailList.size() > BATCH_THRESHOLD) {
                TransactionMessage transactionMessage = new TransactionMessage(transactionDetailList, batchId, false, accountId);
                sendTransactionsViaRabbitMQ(transactionMessage);
                batchId += 1;
                transactionDetailList.clear();
            }
        }
        if (transactionDetailList.size() > 0) {
            TransactionMessage transactionMessage = new TransactionMessage(transactionDetailList, batchId, true, accountId);
            sendTransactionsViaRabbitMQ(transactionMessage);
            batchId += 1;
        }
        // 4. Update account SyncStatus and BatchNumber
        accountService.updateSyncStatusAndBatchNumber(accountId, SyncStatus.INPROGRESS, batchId);
        return true;
    }

    private void sendTransactionsViaRabbitMQ(TransactionMessage transactionMessage) {
        String jsonMessage = SerializeUtil.toJson(transactionMessage);
        rabbitTemplate.convertAndSend("transaction.direct", "send", jsonMessage);
    }

    private Map<String, CryptoTradePairDataDetail> getCryptoTradePairDataDetailListBySyncStatus(ExchangeConnector connector) {
        AccountDetail account = connector.getAccountDetail();
        Map<String, CryptoTradePairDataDetail> details = connector.getDefaultCryptoTradePairDataDetailMap();

        // If not first time login
        if (account.getSyncStatus() != SyncStatus.INITIAL) {
            // account.getSyncStatus() == SyncStatus.PENDING
            List<CryptoTradePairDataDetail> pastDetails = transactionService.getCryptoTradePairDataDetailListByAccountId(account.getId());
            // System.out.println(details.get("ETHUSDT"));
            for (CryptoTradePairDataDetail pastDetail : pastDetails) {
                String symbol = pastDetail.getCryptoAsset() + pastDetail.getTradePairAsset();
                System.out.println(symbol);
                if (details.containsKey(symbol) && pastDetail.getTradeId() != null) {
                    //System.out.println("yes");
                    details.get(symbol).setTradeId(pastDetail.getTradeId());
                }
            }
        }
        return details;
    }


}
