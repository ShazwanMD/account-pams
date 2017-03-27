package my.edu.umk.pams.account.web.module.identity.controller;

import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStaff;
import my.edu.umk.pams.account.web.module.identity.vo.Actor;
import my.edu.umk.pams.account.web.module.identity.vo.ActorType;
import my.edu.umk.pams.account.web.module.identity.vo.Sponsor;
import my.edu.umk.pams.account.web.module.identity.vo.Staff;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author PAMS
 */
@Component("identityTransformer")
public class IdentityTransformer {

    public Staff toStaffVo(AcStaff staff) {
        Staff m = new Staff();
        m.setId(staff.getId());
        m.setIdentityNo(staff.getIdentityNo());
        m.setName(staff.getName());
        m.setEmail(staff.getEmail());
        m.setMobile(staff.getMobile());
        m.setPhone(staff.getPhone());
        m.setFax(staff.getFax());
        return m;
    }

    public List<Staff> toStaffVos(List<AcStaff> staffs) {
        List<Staff> vos = staffs.stream()
                .map((staff) -> toStaffVo(staff))
                .collect(toList());
        return vos;
    }

    public Sponsor toSponsorVo(AcSponsor e) {
        Sponsor m = new Sponsor();
        m.setId(e.getId());
        m.setIdentityNo(e.getIdentityNo());
        m.setName(e.getName());
        m.setEmail(e.getEmail());
        m.setMobile(e.getMobile());
        m.setPhone(e.getPhone());
        m.setFax(e.getFax());
        m.setActorType(ActorType.get(e.getActorType().ordinal()));
        return m;
    }

    public List<Sponsor> toSponsorVos(List<AcSponsor> sponsors) {
        List<Sponsor> vos = sponsors.stream()
                .map((sponsor) -> toSponsorVo(sponsor))
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
        else return null;
    }
}
