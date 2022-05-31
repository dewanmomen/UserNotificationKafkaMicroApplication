package com.dewan.event;

import lombok.Getter;
import lombok.ToString;
import com.dewan.enums.TransactionStatus;

@ToString
@Getter
public class TransactionEvent implements Event {
    private static final String EVENT = "Transaction";

    private Long userid;
    private TransactionStatus status;

    public TransactionEvent() {
    }

    public TransactionEvent setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public TransactionEvent setStatus(TransactionStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String getEvent() {
        return EVENT;
    }
}
