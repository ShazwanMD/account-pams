package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcAccountActivity extends AcMetaObject {

    String getSourceNo();

    void setSourceNo(String sourceNo);

    AcAccountTransactionCode getTransactionCode();

    void setTransactionCode(AcAccountTransactionCode transactionCode);

    Integer getTransactionCodeOrdinal();

    void setTransactionCodeOrdinal(Integer transactionCodeOrdinal);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);
}
