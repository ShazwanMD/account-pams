package my.edu.umk.pams.account.marketing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Entity(name = "AcPromoCodeItem")
@Table(name = "AC_PRMO_CODE_ITEM")
public class AcPromoCodeItemImpl implements AcPromoCodeItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_PRMO_CODE_ITEM")
    @SequenceGenerator(name = "SQ_AC_PRMO_CODE_ITEM", sequenceName = "SQ_AC_PRMO_CODE_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "SOURCE_NO", nullable = true)
    private String sourceNo;

    @NotNull
    @Column(name = "APPLIED")
    private boolean applied = false;

    @NotNull
    @ManyToOne(targetEntity = AcPromoCodeImpl.class)
    @JoinColumn(name = "PROMO_CODE_ID", nullable = false)
    private AcPromoCode promoCode;

    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID", nullable = true)
    private AcAccount account;

    @Embedded
    private AcMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getSourceNo() {
        return sourceNo;
    }

    @Override
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    @Override
    public boolean getApplied() {
        return applied;
    }

    @Override
    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    @Override
    public AcMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcPromoCode.class;
    }

    @Override
    public AcPromoCode getPromoCode() {
        return promoCode;
    }

    @Override
    public void setPromoCode(AcPromoCode promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public AcAccount getAccount() {
        return account;
    }

    @Override
    public void setAccount(AcAccount account) {
        this.account = account;
    }

}
