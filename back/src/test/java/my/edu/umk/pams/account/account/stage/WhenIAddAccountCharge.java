package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIAddAccountCharge extends Stage<WhenIAddAccountCharge> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddAccountCharge.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private IdentityService identityService;

    @ExpectedScenarioState
    AcAcademicSession academicSession;

    @ProvidedScenarioState
    AcAccount account;

    @ProvidedScenarioState
    AcAccountCharge accountCharge;

    @ProvidedScenarioState
    AcAcademicCharge academicCharge;

    @Autowired
    private SessionFactory sessionFactory;

    // todo: use $ placeholder
    public void I_add_account_charge_for_account_$(String code) {
        LOG.debug("when i add account charge on " + academicSession.getCode());

        account = accountService.findAccountByCode(code);

        // charge
        AcAcademicCharge charge = new AcAcademicChargeImpl();
        charge.setReferenceNo("abc123");
        charge.setSourceNo("abc123");
        charge.setAmount(BigDecimal.valueOf(200.00));
        charge.setDescription("This is a test");
        charge.setSession(academicSession);
        charge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
        accountService.addAccountCharge(account, charge);
    }

    public void I_create_student_account_and_add_account_charge_of_$(double chargeAmount) {
        Assert.notNull(academicSession.getCode(), "academic session record is a prerequite");

        // create student
        AcStudent student = new AcStudentImpl();
        student.setIdentityNo("STDN-" + System.currentTimeMillis());
        student.setName("Rafizan Baharum");
        student.setEmail("rafizan.baharum@umk.edu.my");
        student.setMobile("123456789");
        student.setPhone("123456789");
        student.setFax("123456789");
        identityService.saveStudent(student);

        // create account
        account = new AcAccountImpl();
        account.setCode(student.getMatricNo());
        account.setDescription(student.getMatricNo() + ";" + student.getEmail());
        account.setActor(student);
        accountService.saveAccount(account);

        // accountCharge
        accountCharge = new AcAccountChargeImpl();
        accountCharge.setReferenceNo("CHRG-"  + System.currentTimeMillis());
        accountCharge.setSourceNo("abc123");
        accountCharge.setAmount(BigDecimal.valueOf(chargeAmount));
        accountCharge.setDescription("This is a test");
        accountCharge.setSession(academicSession);
        accountCharge.setChargeType(AcAccountChargeType.STUDENT_AFFAIRS);
//        accountCharge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
        accountService.addAccountCharge(account, accountCharge);
    }

    public void I_create_student_account_and_add_academic_charge() {
//        LOG.debug("when i add account charge on " + academicSession.getCode());

        // create student
        AcStudent student = new AcStudentImpl();
        student.setIdentityNo("STND-" + System.currentTimeMillis());
        student.setName("Rafizan Baharum");
        student.setEmail("rafizan.baharum@umk.edu.my");
        student.setMobile("123456789");
        student.setPhone("123456789");
        student.setFax("123456789");
        identityService.saveStudent(student);

        // create account
        account = new AcAccountImpl();
        account.setCode(student.getMatricNo());
        account.setDescription(student.getMatricNo() + ";" + student.getEmail());
        account.setActor(student);
        accountService.saveAccount(account);

        // charge
        AcAcademicCharge charge = new AcAcademicChargeImpl();
        charge.setReferenceNo("CHRG-" + System.currentTimeMillis());
        charge.setSourceNo("abc123");
        charge.setAmount(BigDecimal.valueOf(200.00));
        charge.setDescription("This is a test");
        charge.setSession(academicSession);
        charge.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
        accountService.addAccountCharge(account, charge);
    }
}
