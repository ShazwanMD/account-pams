package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;

/**
 * @author PAMS
 */
@Entity(name = "AcSponsorship")
@Table(name = "AC_SPHP")
public class AcSponsorshipImpl implements AcSponsorship {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_SPHP")
    @SequenceGenerator(name = "SQ_AC_SPHP", sequenceName = "SQ_AC_SPHP", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = AcStudentImpl.class)
    @JoinColumn(name = "STUDENT_ID")
    private AcStudent student;

    @ManyToOne(targetEntity = AcSponsorImpl.class)
    @JoinColumn(name = "SPONSOR_ID")
    private AcSponsor sponsor;

    @Embedded
    private AcMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcStudent getStudent() {
        return student;
    }

    public void setStudent(AcStudent student) {
        this.student = student;
    }

    public AcSponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(AcSponsor sponsor) {
        this.sponsor = sponsor;
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
        return AcSponsorship.class;
    }
}
