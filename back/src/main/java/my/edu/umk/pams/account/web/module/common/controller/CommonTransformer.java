package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import my.edu.umk.pams.account.web.module.common.vo.FacultyCode;
import my.edu.umk.pams.account.web.module.common.vo.StudyMode;
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
    // STUDY MODE
    //====================================================================================================

    public StudyMode toStudyModeVo(AcStudyMode e) {
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

}
