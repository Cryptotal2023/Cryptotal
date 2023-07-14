package com.cryptotal.service.core.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CoinUtils {

//    private static final Set<String> commonTradePairSet =
//            new HashSet<>(Arrays.asList("BTC","ETH","BNB", "USDT","BUSD","USDC","TUSD",
//                    "PAX","RUB", "EUR", "GBP", "JPY", "USD", "AUD", "CAD", "CHF", "CNY", "HKD", "IDR", "INR", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "SGD", "THB", "TRY", "VND", "ZAR"));
    private static final Set<String> commonTradePairSet =
            new HashSet<>(Arrays.asList("BTC","ETH","BNB", "USDT","BUSD","USDC","TUSD"));
    public static boolean isCommonTradePairSet(String coin) {
        if (commonTradePairSet.contains(coin)) {
            return true;
        }
        return false;
    }
}
