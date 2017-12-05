package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcBankCode")
@Table(name = "AC_BANK_CODE")
public class AcBankCodeImpl implements AcBankCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_BANK_CODE")
    @SequenceGenerator(name = "SQ_AC_BANK_CODE", sequenceName = "SQ_AC_BANK_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "SWIFT_CODE", unique = true, nullable = false)
    private String swiftCode;

//    @NotNull
//    @Column(name = "IBG_CODE", unique = true, nullable = false)
//    private String ibgCode;

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
    public String getSwiftCode() {
        return swiftCode;
    }

    @Override
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

//    @Override
//    public String getIbgCode() {
//        return ibgCode;
//    }
//
//    @Override
//    public void setIbgCode(String ibgCode) {
//        this.ibgCode = ibgCode;
//    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String description) {
        this.name = description;
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
        return AcBankCode.class;
    }
}
