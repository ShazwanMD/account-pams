package my.edu.umk.pams.account.core;

import java.io.Serializable;

/**
 * @author canang technologies
 */
public interface AcMetaObject extends Serializable {

    /**
     * entity id
     *
     * @return
     */
    Long getId();

    /**
     * metadata
     *
     * @return
     */
    AcMetadata getMetadata();

    void setMetadata(AcMetadata metadata);

    /**
     * implementing interface
     *
     * @return
     */
    Class<?> getInterfaceClass();
}

