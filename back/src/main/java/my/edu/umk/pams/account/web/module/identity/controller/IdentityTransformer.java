package my.edu.umk.pams.account.web.module.identity.controller;

import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.web.module.common.controller.CommonTransformer;
import my.edu.umk.pams.account.web.module.identity.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author PAMS
 */
@Component("identityTransformer")
public class IdentityTransformer {
	
    @Autowired
    private CommonTransformer commonTransformer;

    public Staff toStaffVo(AcStaff e) {
        Staff vo = new Staff();
        vo.setId(e.getId());
        vo.setActorType(ActorType.get(e.getActorType().ordinal()));
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setMobile(e.getMobile());
        vo.setPhone(e.getPhone());
        vo.setFax(e.getFax());
        return vo;
    }

    public List<Staff> toStaffVos(List<AcStaff> staffs) {
        List<Staff> vos = staffs.stream()
                .map((staff) -> toStaffVo(staff))
                .collect(toList());
        return vos;
    }

    public Student toStudentVo(AcStudent e) {
        Student vo = new Student();
        vo.setId(e.getId());
        vo.setActorType(ActorType.get(e.getActorType().ordinal()));
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setMobile(e.getMobile());
        vo.setPhone(e.getPhone());
        vo.setFax(e.getFax());
        vo.setResidencyCode(commonTransformer.toResidencyCodeVo(e.getResidencyCode()));
        vo.setCohortCode(commonTransformer.toCohortCodeVo(e.getCohortCode()));
        return vo;
    }

    public List<Student> toStudentVos(List<AcStudent> students) {
        List<Student> vos = students.stream()
                .map((student) -> toStudentVo(student))
                .collect(toList());
        return vos;
    }

    public Sponsor toSponsorVo(AcSponsor e) {
        Sponsor vo = new Sponsor();
        vo.setId(e.getId());
        vo.setActorType(ActorType.get(e.getActorType().ordinal()));
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setMobile(e.getMobile());
        vo.setPhone(e.getPhone());
        vo.setFax(e.getFax());
        vo.setActorType(ActorType.get(e.getActorType().ordinal()));
        return vo;
    }

    public List<Sponsor> toSponsorVos(List<AcSponsor> sponsors) {
        List<Sponsor> vos = sponsors.stream()
                .map((sponsor) -> toSponsorVo(sponsor))
                .collect(toList());
        return vos;
    }
    
    public Sponsorship toSponsorshipVo(AcSponsorship e) {
    	Sponsorship vo = new Sponsorship();
        vo.setId(e.getId());
//        vo.setActorType(ActorType.get(e.getActorType().ordinal()));
        vo.setReferenceNo(e.getReferenceNo());
        vo.setAccountNo(e.getAccountNo());
        vo.setEndDate(e.getStartDate());
        vo.setAmount(e.getAmount());
        vo.setStartDate(e.getStartDate());
        vo.setEndDate(e.getStartDate());
        vo.setActive(e.getActive());
        return vo;
    }

    public List<Sponsorship> toSponsorshipVos(List<AcSponsorship> sponsorships) {
        List<Sponsorship> vos = sponsorships.stream()
                .map((sponsorship) -> toSponsorshipVo(sponsorship))
                .collect(toList());
        return vos;
    }


    public List<Actor> toActorVos(List<AcActor> actors) {
        List<Actor> vos = actors.stream()
                .map((actor) -> toActorVo(actor))
                .collect(toList());
        return vos;
    }

    public Actor toActorVo(AcActor actor) {
        if(actor instanceof AcSponsor)
        return toSponsorVo((AcSponsor) actor);
        else if(actor instanceof AcStaff)
        return toStaffVo((AcStaff) actor);
        else if(actor instanceof AcStudent)
        return toStudentVo((AcStudent) actor);
        else return null;
    }
}
