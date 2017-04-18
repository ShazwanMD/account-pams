package my.edu.umk.pams.account.web.module.marketing.controller;

import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.web.module.account.controller.AccountTransformer;
import my.edu.umk.pams.account.web.module.core.vo.MetaState;
import my.edu.umk.pams.account.web.module.marketing.vo.PromoCode;
import my.edu.umk.pams.account.web.module.marketing.vo.PromoCodeItem;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("marketingTransformer")
public class MarketingTransformer {

    @Autowired
    private AccountTransformer accountTransformer;

    @Autowired
    private IdentityTransformer identityTransformer;

    public PromoCode toSimplePromoCodeVo(AcPromoCode e) {
        PromoCode vo = new PromoCode();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public PromoCode toPromoCodeVo(AcPromoCode e) {
        PromoCode vo = new PromoCode();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setValue(e.getValue());
        vo.setQuantity(e.getQuantity());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }
    public PromoCodeItem toPromoCodeItemVo(AcPromoCodeItem e) {
        PromoCodeItem vo = new PromoCodeItem();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setSourceNo(e.getSourceNo());
        vo.setApplied(e.getApplied());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public List<PromoCode> toPromoCodeVos(List<AcPromoCode> journals) {
        return journals.stream()
                .map((task) -> toPromoCodeVo(task))
                .collect(toCollection(() -> new ArrayList<PromoCode>()));
    }

    public List<PromoCodeItem> toPromoCodeItemVos(List<AcPromoCodeItem> entries) {
        return entries.stream()
                .map((entry) -> toPromoCodeItemVo(entry))
                .collect(toCollection(() -> new ArrayList<PromoCodeItem>()));
    }
}
