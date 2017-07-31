package my.edu.umk.pams.account.web.module.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author PAMS
 */
public abstract class MetaObject {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date modifiedDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date deletedDate;
    
    
    // transient
	private String creatorUsername;
	private String modifierUsername;

    private MetaState metaState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public MetaState getMetaState() {
        return metaState;
    }

    public void setMetaState(MetaState metaState) {
        this.metaState = metaState;
    }

	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}

	public String getModifierUsername() {
		return modifierUsername;
	}

	public void setModifierUsername(String modifierUsername) {
		this.modifierUsername = modifierUsername;
	}
    
}
