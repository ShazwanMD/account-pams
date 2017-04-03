package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@JGivenStage
public class ThenDiscountCanAppliedToReceipt extends Stage<ThenDiscountCanAppliedToReceipt> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountToStudent.class);

    @ExpectedScenarioState
    private AcPromoCode promoCode;

    public ThenDiscountCanAppliedToReceipt discount_can_applied_to_receipt() {
        Assert.notNull(promoCode, "promoCode cannot be null");

        // todo (aida) Buat ape yg patut kat sini

        return self();
    }

}
