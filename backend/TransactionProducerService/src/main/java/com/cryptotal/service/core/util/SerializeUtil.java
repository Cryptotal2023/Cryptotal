package com.cryptotal.service.core.util;

import com.cryptotal.service.core.domain.messagequeue.TransactionDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;


public class SerializeUtil {

    private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static String serialize(List<TransactionDetail> transactionDetailList){
        String result = null;
        try {
            result = objectMapper.writeValueAsString(transactionDetailList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static List<TransactionDetail> deSerializeTransactionDetail(byte[] input){

        List<TransactionDetail> result = null;
        try {
            result = objectMapper.readValue(input, new TypeReference<List<TransactionDetail>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.err.println("Occur error during parsing data to json: " + e);
            return "";
        }
    }

    public static <T> T toObject(String json, Class<T> objectClass) {
        try {
            return objectMapper.readValue(json, objectClass);
        } catch (IOException e) {
            System.err.println("Occur error during mapping json to an object: " + e);
            return null;
        }
    }

}
