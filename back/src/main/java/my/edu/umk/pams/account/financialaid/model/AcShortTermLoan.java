package my.edu.umk.pams.account.financialaid.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.core.model.AcDocument;

public interface AcShortTermLoan extends AcDocument{

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getDescription();

    void setDescription(String description);
    
    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);
    
    AcAccount getAccount();

    void setAccount(AcAccount account);
    
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);
    
    AcShortTermLoanStatus getStatus();
    
    void setStatus(AcShortTermLoanStatus status);

	void setId(Long id);

}
