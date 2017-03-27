package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenUpdateStudentCompound extends Stage<WhenUpdateStudentCompound> {
    private static final Logger LOG = LoggerFactory.getLogger(WhenUpdateStudentCompound.class);

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;

    @ExpectedScenarioState
    private AcAcademicSession academicSession;

    @Autowired
    private CommonService commonService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AccountService accountService;


    public WhenUpdateStudentCompound I_want_to_update_student_compound_payment() {

        AcChargeCode code = new AcChargeCodeImpl();

        //find compound by id
        code = accountService.findChargeCodeByCode("AC-0004");
        LOG.debug("test", code);
        // update charge

        code.setCode("AC-0001");
        code.setDescription("azieta");
        code.setChargeType(AcChargeCodeType.HOSTEL);
        code.setPriority(3);


        // use account service to update charge
        accountService.updateChargeCode(code);

        return self();
    }

}
