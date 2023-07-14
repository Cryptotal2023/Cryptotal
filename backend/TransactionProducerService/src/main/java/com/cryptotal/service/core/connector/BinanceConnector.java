package com.cryptotal.service.core.connector;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.cryptotal.service.core.domain.TransactionService.CryptoTradePairDataDetail;
import com.cryptotal.service.core.domain.binance.BinanceExchangeInfoDetail;
import com.cryptotal.service.core.domain.binance.BinanceSymbol;
import com.cryptotal.service.core.domain.binance.BinanceTransactionDetail;
import com.cryptotal.service.core.domain.binance.BinanceUserAssetDetail;
import com.cryptotal.service.core.domain.messagequeue.TransactionDetail;
import com.cryptotal.service.core.entity.AccountService.AccountDetail;
import com.cryptotal.service.core.util.CoinUtils;
import com.cryptotal.service.core.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Getter
public class BinanceConnector implements ExchangeConnector {

    private final List<String> defaultTradePairList = Arrays.asList("BTC", "USDT", "USDC", "BUSD", "ETH");
    private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private AccountDetail accountDetail;
    private String apiKey;
    private String secretKey;
    @Setter
    String baseUrl = "https://api.binance.com";

    SpotClient spotClient;
    public BinanceConnector(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
        this.apiKey = accountDetail.getApiKey();
        this.secretKey = accountDetail.getSecretKey();
        spotClient = new SpotClientImpl(this.apiKey, this.secretKey, this.baseUrl);
    }

    @Override
    public boolean checkAccountStatus() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        StringBuilder sb = new StringBuilder();

        try {
            sb.append(spotClient.createTrade().account(parameters));
            System.out.println(sb);
            return true;
        } catch (BinanceConnectorException e) {
            sb.append(String.format(String.format("fullErrMessage: %s", e.getMessage(), e)));
            System.err.println(sb);
            return false;
        } catch (BinanceClientException e) {
            sb.append(String.format("fullErrMessage: %s \nerrMessage: %s \nerrCode: %d \nHTTPStatusCode: %d",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode()));
            System.err.println(sb);
            return false;
        } finally {
            sb.setLength(0);
        }
    }

    @Override
    public List<TransactionDetail> getTransactionHistory(String asset, String tradePair, Long fromId) {
        Map<String, Object> parameters = new LinkedHashMap<>();

        String symbol = asset + tradePair;
        parameters.put("symbol", symbol);
        parameters.put("fromId", fromId);

        try {
            String result = spotClient.createTrade().myTrades(parameters);

            System.out.println(result);

            List<BinanceTransactionDetail> binanceTransactionDetails =
                    objectMapper.readValue(result, new TypeReference<List<BinanceTransactionDetail>>() {});
            //System.out.println(binanceTransactionDetails);
            List<TransactionDetail> transactionDetails = mapTransactionDetails(asset, tradePair, binanceTransactionDetails);
            //System.out.println(transactionDetails);
            return transactionDetails;

        } catch (BinanceConnectorException | JsonProcessingException e) {
            System.err.println((String) String.format("fullErrMessage: %s", e.getMessage()));
        } catch (BinanceClientException e) {
            System.err.println((String) String.format("FullErrMessage: %s TradeSymbol: %s \nHTTPStatusCode: %d",
                    e.getMessage(), symbol, e.getHttpStatusCode()));
        }
        return new ArrayList<TransactionDetail>();
    }

    @Override
    public List<String> getUserAssetList() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        try {
            String result = spotClient.createWallet().getUserAsset(parameters);

            //System.out.println(result);

            List<BinanceUserAssetDetail> binanceUserAssetDetails =
                    objectMapper.readValue(result, new TypeReference<List<BinanceUserAssetDetail>>() {});
            //System.out.println(binanceTransactionDetails);
            List<String> assetDetails = mapUserAssetDetails(binanceUserAssetDetails);
            //System.out.println(assetDetails);
            return assetDetails;

        } catch (BinanceConnectorException | JsonProcessingException e) {
            System.err.println(String.format("fullErrMessage: %s", e.getMessage()));
        } catch (BinanceClientException e) {
            System.err.println(String.format("fullErrMessage: %s \nerrMessage: %s \nerrCode: %d \nHTTPStatusCode: %d",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode()));
        }
        return new ArrayList<>();
    }

    public List<String> getDefaultTradePairAssetList() {
        return defaultTradePairList;
    }

    // TODO: Maintain CryptoTradePair efficiently : keep track of real asset amount and look for changes
    @Override
    public List<CryptoTradePairDataDetail> getDefaultCryptoTradePairDataDetailList() {
        List<String> userAssetList = this.getUserAssetList();
        BinanceExchangeInfoDetail binanceExchangeInfoDetails = this.getTradePairAssetExchangeInfo();
        List<BinanceSymbol> binanceSymbols = binanceExchangeInfoDetails.getSymbols().stream()
                .filter(s -> userAssetList.contains(s.getBaseAsset()) && CoinUtils.isCommonTradePairSet(s.getQuoteAsset())
                )
                .collect(Collectors.toList());
        System.out.println(userAssetList);
        System.out.println(userAssetList.size());
        System.out.println(binanceSymbols);
        System.out.println(binanceSymbols.size());

        List<CryptoTradePairDataDetail> cryptoTradePairDataDetailList = new ArrayList<>();
        for (BinanceSymbol binanceSymbol : binanceSymbols) {
            CryptoTradePairDataDetail cryptoTradePairDataDetail = new CryptoTradePairDataDetail();
            cryptoTradePairDataDetail.setCryptoAsset(binanceSymbol.getBaseAsset());
            cryptoTradePairDataDetail.setTradePairAsset(binanceSymbol.getQuoteAsset());
            cryptoTradePairDataDetail.setTradeId(0L);
            cryptoTradePairDataDetailList.add(cryptoTradePairDataDetail);
        }
        return cryptoTradePairDataDetailList;
    }
    @Override
    public Map<String, CryptoTradePairDataDetail> getDefaultCryptoTradePairDataDetailMap() {
        // TODO: uncomment only when fetching REAL-TIME data (suffer from IP limit, consider use websocket)

        return getFakeCryptoTradePairDataDetailMap();
    }
    public Map<String, CryptoTradePairDataDetail> getFakeCryptoTradePairDataDetailMap() {
        Integer accountId = accountDetail.getId();
        CryptoTradePairDataDetail cd1 = new CryptoTradePairDataDetail(accountId, "BTC", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd2 = new CryptoTradePairDataDetail(accountId, "ETH", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd3 = new CryptoTradePairDataDetail(accountId, "ETH", "BTC", 0L, 0L);
        CryptoTradePairDataDetail cd4 = new CryptoTradePairDataDetail(accountId, "BNB", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd5 = new CryptoTradePairDataDetail(accountId, "XRP", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd6 = new CryptoTradePairDataDetail(accountId, "DOT", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd7 = new CryptoTradePairDataDetail(accountId, "SXP", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd8 = new CryptoTradePairDataDetail(accountId, "SOL", "USDT", 0L, 0L);
        CryptoTradePairDataDetail cd9 = new CryptoTradePairDataDetail(accountId, "FLOW", "USDT", 0L, 0L);
        List<CryptoTradePairDataDetail> cryptoTradePairDataDetailList = new ArrayList<CryptoTradePairDataDetail>();
        cryptoTradePairDataDetailList.add(cd1);
        cryptoTradePairDataDetailList.add(cd2);
        cryptoTradePairDataDetailList.add(cd3);
        cryptoTradePairDataDetailList.add(cd4);
        cryptoTradePairDataDetailList.add(cd5);
        cryptoTradePairDataDetailList.add(cd6);
        cryptoTradePairDataDetailList.add(cd7);
        cryptoTradePairDataDetailList.add(cd8);
        cryptoTradePairDataDetailList.add(cd9);

        return cryptoTradePairDataDetailList.stream()
                .collect(Collectors.toMap(s -> s.getCryptoAsset() + s.getTradePairAsset(), s -> s));
    }
    @Override
    public AccountDetail getAccountDetail() {
        return accountDetail;
    }

    @Cacheable("myCache")
    public BinanceExchangeInfoDetail getTradePairAssetExchangeInfo() {
        Map<String, Object> parameters = new LinkedHashMap<>();


        parameters.put("permissions", new ArrayList<>(Collections.singletonList("SPOT")));
        try {
            String result = spotClient.createMarket().exchangeInfo(parameters);

            //System.out.println(result);

            BinanceExchangeInfoDetail binanceExchangeInfoDetails =
                    objectMapper.readValue(result, new TypeReference<BinanceExchangeInfoDetail>() {});
            return binanceExchangeInfoDetails;

        } catch (BinanceConnectorException | JsonProcessingException e) {
            System.err.println(String.format("fullErrMessage: %s", e.getMessage()));
        } catch (BinanceClientException e) {
            System.err.println(String.format("fullErrMessage: %s \nerrMessage: %s \nerrCode: %d \nHTTPStatusCode: %d",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode()));
        }
        return new BinanceExchangeInfoDetail();
    }

    private List<String> mapUserAssetDetails(List<BinanceUserAssetDetail> binanceUserAssetDetails) {
        return binanceUserAssetDetails.stream().map(BinanceUserAssetDetail::getAsset).collect(Collectors.toList());
    }

    private List<TransactionDetail> mapTransactionDetails(String asset, String tradePair, List<BinanceTransactionDetail> binanceTransactionDetails) {

        return binanceTransactionDetails.stream().map( detail ->
                TransactionDetail.builder()
                        .accountId(accountDetail.getId())
                        .cryptoAsset(asset)
                        .tradePairAsset(tradePair)
                        .tradeId(detail.getId())
                        .orderId(detail.getOrderId())
                        .orderListId(detail.getOrderListId())
                        .price(detail.getPrice().doubleValue())
                        .quantity(detail.getQty().doubleValue())
                        .tradePairQuantity(detail.getQuoteQty().doubleValue())
                        .commission(detail.getCommission().doubleValue())
                        .commissionAsset(detail.getCommissionAsset())
                        .isBuyer(detail.getIsBuyer())
                        .isMaker(detail.getIsMaker())
                        .isBestMatch(detail.getIsBestMatch())
                        .time(Utils.convertStringToLocalDateTime(detail.getTime()))
                        .createdAt(LocalDateTime.now())
                        .isBuyer(detail.getIsBuyer())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
