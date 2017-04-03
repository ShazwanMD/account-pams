package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.web.module.account.controller.AccountTransformer;
import my.edu.umk.pams.account.web.module.core.vo.MetaState;
import my.edu.umk.pams.account.web.module.financialaid.vo.Settlement;
import my.edu.umk.pams.account.web.module.financialaid.vo.SettlementItem;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("financialAidTransformer")
public class FinancialAidTransformer {

    @Autowired
    private AccountTransformer accountTransformer;

    @Autowired
    private IdentityTransformer identityTransformer;

    public Settlement toSimpleSettlementVo(AcSettlement e) {
        Settlement vo = new Settlement();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setSponsor(identityTransformer.toSponsorVo(e.getSponsor()));
        vo.setAcademicSession(accountTransformer.toAcademicSessionVo(e.getSession()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public Settlement toSettlementVo(AcSettlement e) {
        Settlement vo = new Settlement();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }
    public SettlementItem toSettlementItemVo(AcSettlementItem e) {
        // todo(uda): more properties
        SettlementItem vo = new SettlementItem();
        vo.setId(e.getId());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public List<Settlement> toSettlementVos(List<AcSettlement> journals) {
        return journals.stream()
                .map((task) -> toSettlementVo(task))
                .collect(toCollection(() -> new ArrayList<Settlement>()));
    }

    public List<SettlementItem> toSettlementItemVos(List<AcSettlementItem> entries) {
        return entries.stream()
                .map((entry) -> toSettlementItemVo(entry))
                .collect(toCollection(() -> new ArrayList<SettlementItem>()));
    }
}
