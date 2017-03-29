package my.edu.umk.pams.account.marketing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcPromoCodeItem extends AcMetaObject{

    Long getId();
    
    void setId(Long id);
    
    AcPromoCode getPromoCode();
    
    void setPromoCode(AcPromoCode promoCode);
	
    String getSourceNo();
    
    void setSourceNo(String sourceNo);
    
    boolean getApplied();
    
    void setApplied(boolean applied);
    
    AcAccount getAccount();
    
    void setAccount(AcAccount account);
}
