package my.edu.umk.pams.account.common.model;

import java.util.List;

public interface AcFacultyCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
    
    List<AcProgramCode> getProgramCode();
    
    void setProgramCode(List<AcProgramCode> programCode);
}
