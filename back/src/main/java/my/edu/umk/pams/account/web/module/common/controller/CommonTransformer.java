package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.common.model.*;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.web.module.common.vo.*;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import my.edu.umk.pams.account.web.module.core.vo.MetaState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PAMS
 */
@Component("commonTransformer")
public class CommonTransformer {

	@Autowired
	private IdentityService identityService;
	
    //====================================================================================================
    // COHORT CODE
    //====================================================================================================

    public CohortCode toCohortCodeVo(AcCohortCode e) {
    	if(null == e) return null;
        CohortCode vo = new CohortCode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<CohortCode> toCohortCodeVos(List<AcCohortCode> e) {
        List<CohortCode> vos = e.stream()
                .map((e1) -> toCohortCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    //====================================================================================================
    // RESIDENCY CODE
    //====================================================================================================

    public ResidencyCode toResidencyCodeVo(AcResidencyCode e) {
        ResidencyCode vo = new ResidencyCode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<ResidencyCode> toResidencyCodeVos(List<AcResidencyCode> e) {
        List<ResidencyCode> vos = e.stream()
                .map((e1) -> toResidencyCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }
    
    //====================================================================================================
    // STUDY MODE
    //====================================================================================================

    public StudyMode toStudyModeVo(AcStudyMode e) {
    	if(null == e) return null;
        StudyMode vo = new StudyMode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescriptionMs(e.getDescriptionMs());
        vo.setDescriptionEn(e.getDescriptionEn());
        return vo;
    }

    public List<StudyMode> toStudyModeVos(List<AcStudyMode> e) {
        List<StudyMode> vos = e.stream()
                .map((e1) -> toStudyModeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }
    
	// ====================================================================================================
	// FACULTY CODE
	// ====================================================================================================

	public FacultyCode toFacultyCodeVo(AcFacultyCode e) {
		FacultyCode vo = new FacultyCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescription(e.getDescription());
		return vo;
	}

    public List<FacultyCode> toFacultyCodeVos(List<AcFacultyCode> e) {
        List<FacultyCode> vos = e.stream()
                .map((e1) -> toFacultyCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

	
    //====================================================================================================
    // BANK CODE
    //====================================================================================================
    public BankCode toBankCodeVo(AcBankCode e) {
    	if(null == e) return null;
        BankCode vo = new BankCode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setIbgCode(e.getIbgCode());
        vo.setSwiftCode(e.getSwiftCode());
        vo.setName(e.getName());
        return vo;
    }

    public List<BankCode> toBankCodeVos(List<AcBankCode> e) {
        List<BankCode> vos = e.stream()
                .map((e1) -> toBankCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }
    
    //====================================================================================================
    // TAX CODE
    //====================================================================================================

    public TaxCode toTaxCodeVo(AcTaxCode e) {
    	if(null == e) return null;
    	TaxCode vo = new TaxCode();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setTaxRate(e.getTaxRate());
        return vo;
    }

    public List<TaxCode> toTaxCodeVos(List<AcTaxCode> e) {
        List<TaxCode> vos = e.stream()
                .map((e1) -> toTaxCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }
    
    //====================================================================================================
    // SECURITY Charge CODE
    //====================================================================================================

    public SecurityChargeCode toSecurityChargeCodeVo(AcSecurityChargeCode e) {
    	if(null == e) return null;
    	SecurityChargeCode vo = new SecurityChargeCode();
        vo.setId(e.getId());
        vo.setSection(e.getSection());
        vo.setDescription(e.getDescription());
        vo.setOffense(e.getOffense());
        vo.setOffenseDescription(e.getOffenseDescription());
        vo.setAmount(e.getAmount());
        vo.setAmountDescription(e.getAmountDescription());
        vo.setTaxAmount(e.getTaxAmount());
        vo.setNetAmount(e.getNetAmount());
        vo.setTaxCode(this.toTaxCodeVo(e.getTaxCode()));
        vo.setInclusive(e.getInclusive());
        vo.setActive(e.getActive());
        return vo;
    }

    public List<SecurityChargeCode> toSecurityChargeCodeVos(List<AcSecurityChargeCode> e) {
        List<SecurityChargeCode> vos = e.stream()
                .map((e1) -> toSecurityChargeCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public void decorateMeta(AcMetaObject metaObject, MetaObject vo){
        vo.setMetaState(MetaState.get(metaObject.getMetadata().getState().ordinal()));
        vo.setCreatedDate(metaObject.getMetadata().getCreatedDate());
        vo.setDeletedDate(metaObject.getMetadata().getDeletedDate());
        
       
        AcUser userCreator = identityService.findUserById(metaObject.getMetadata().getCreatorId());
        System.out.println(userCreator.getRealName());
        vo.setCreatorUsername(userCreator.getRealName()); 
        vo.setModifierUsername(userCreator.getRealName());  
        
    }
}
