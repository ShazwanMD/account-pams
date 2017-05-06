package my.edu.umk.pams.account.web.module.common.controller;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
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
}
