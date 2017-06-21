package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.common.model.*;
import my.edu.umk.pams.account.web.module.common.vo.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PAMS
 */
@Component("commonTransformer")
public class CommonTransformer {

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
		List<FacultyCode> vos = e.stream().map((e1) -> toFacultyCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	
    //====================================================================================================
    // BANK CODE
    //====================================================================================================
    public BankCode toBankCodeVo(AcBankCode e) {

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
    // RESIDENCY CODE
    //====================================================================================================

    public TaxCode toTaxCodeVo(AcTaxCode e) {
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

}
