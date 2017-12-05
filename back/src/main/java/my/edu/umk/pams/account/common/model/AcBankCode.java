package my.edu.umk.pams.account.common.model;

public interface AcBankCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getSwiftCode();

    void setSwiftCode(String swiftCode);

//    String getIbgCode();
//
//    void setIbgCode(String ibgCode);

    String getName();

    void setName(String description);
}
