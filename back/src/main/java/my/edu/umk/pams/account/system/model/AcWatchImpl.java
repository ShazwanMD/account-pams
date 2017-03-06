package my.edu.umk.pams.account.system.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author canang.technologies
 * @since 7/26/14
 */
@Entity(name = "AcWatch")
@Table(name = "AC_WTCH")
public class AcWatchImpl implements AcWatch {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_WTCH")
    @SequenceGenerator(name = "SQ_AC_WTCH", sequenceName = "SQ_AC_WTCH", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "OBJECT_ID", nullable = false)
    private Long objectId;

    @NotNull
    @Column(name = "OBJECT_CLASS", nullable = false)
    private String objectClass;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Embedded
    private my.edu.umk.pams.account.core.AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public my.edu.umk.pams.account.core.AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(my.edu.umk.pams.account.core.AcMetadata metadata) {
        this.metadata = metadata;
    }
}
