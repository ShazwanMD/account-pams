package my.edu.umk.pams.account.marketing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcPromoCodeItem extends AcMetaObject {

    /**
     *
     * @return
     */
    String getCode();

    void setCode(String code);

    /**
     *
     * @return
     */
    String getSourceNo();

    void setSourceNo(String sourceNo);

    /**
     *
     * @return
     */
    boolean getApplied();

    void setApplied(boolean applied);

    /**
     *
     * @return
     */
    AcAccount getAccount();

    void setAccount(AcAccount account);

    /**
     *
     * @return
     */
    AcPromoCode getPromoCode();

    void setPromoCode(AcPromoCode promoCode);

}
