package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "AcSettlementItem")
@Table(name = "AC_STLT_ITEM")
public class AcSettlementItemImpl implements AcSettlementItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_STLT_ITEM")
    @SequenceGenerator(name = "SQ_AC_STLT_ITEM", sequenceName = "SQ_AC_STLT_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = AcSettlementImpl.class)
    @JoinColumn(name = "SETTLEMENT_ID")
    private AcSettlement settlement;

    @NotNull
    @OneToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @Column(name = "SETTLEMENT_STATUS", nullable = false)
    private AcSettlementStatus status = AcSettlementStatus.NEW;

    @Column(name = "BALANCE_AMOUNT", nullable = false)
    private BigDecimal balanceAmount;

    @ManyToOne(targetEntity = AcStudentImpl.class)
    @JoinColumn(name = "STUDENT_ID")
    private AcStudent student;

    @OneToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;

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
    public AcSettlement getSettlement() {
        return settlement;
    }

    @Override
    public void setSettlement(AcSettlement settlement) {
        this.settlement = settlement;
    }

    @Override
    public AcAccount getAccount() {
        return account;
    }

    public void setAccount(AcAccount account) {
        this.account = account;
    }

    @Override
    public AcSettlementStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(AcSettlementStatus status) {
        this.status = status;
    }

    @Override
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    @Override
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Override
    public AcStudent getStudent() {
        return student;
    }

    @Override
    public void setStudent(AcStudent student) {
        this.student = student;
    }

    @Override
    public AcInvoice getInvoice() {
        return invoice;
    }

    @Override
    public void setInvoice(AcInvoice invoice) {
        this.invoice = invoice;
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
        return AcSettlementItem.class;
    }
}
