package com.cryptotal.service.core.domain.messagequeue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionMessage implements java.io.Serializable {
    private List<TransactionDetail> transactionDetailList;
    private Integer batchId;
    private Boolean endOfBatch;
    private Integer accountId;

}