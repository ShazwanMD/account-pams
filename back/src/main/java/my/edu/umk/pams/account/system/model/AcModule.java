package my.edu.umk.pams.account.system.model;

/**
 * @author canang technologies
 * @since 4/18/14
 */
public interface AcModule extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getCanonicalCode();

    void setCanonicalCode(String canonicalCode);

    String getDescription();

    void setDescription(String description);

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    boolean isEnabled();

    void setEnabled(boolean enabled);
}