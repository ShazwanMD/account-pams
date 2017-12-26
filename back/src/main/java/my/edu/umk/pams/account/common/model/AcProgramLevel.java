package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcProgramLevel extends AcMetaObject {
	
    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

	String getPrefix();

	void setPrefix(String prefix);

}
