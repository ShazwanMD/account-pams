package my.edu.umk.pams.account.system.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author canang technologies
 * @since 4/18/14
 */
public interface AcSubModule extends AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    AcModule getModule();

    void setModule(AcModule module);
}
