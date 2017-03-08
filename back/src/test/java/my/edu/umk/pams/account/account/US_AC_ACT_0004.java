package my.edu.umk.pams.account.account;

import my.edu.umk.pams.account.TestSupport;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * As a Bursary, I want to list student charges by account,  so that I can view student's charges
 *
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0004 extends TestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0004.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SessionFactory sessionFactory;

    private AcAcademicSession academicSession;

    @Before
    public void before() {
        // login
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("root", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

        // current academic session
        academicSession = accountService.findAcademicSessionByCode("201720181");
    }

    @After
    public void after() {
    }


    @Test
    @Rollback(true)
    public void findStudentCharges() {
        // create student
        AcStudent student = new AcStudentImpl();
        student.setIdentityNo("ABC001");
        student.setName("Rafizan Baharum");
        student.setEmail("rafizan.baharum@umk.edu.my");
        student.setMobile("123456789");
        student.setPhone("123456789");
        student.setFax("123456789");
        identityService.saveStudent(student);

        // create account
        AcAccount account = new AcAccountImpl();
        account.setCode(student.getMatricNo());
        account.setDescription(student.getMatricNo() + ";" + student.getEmail());
        account.setActor(student);
        // todo
//        accountService.saveAccount(account);

        // add charges
        AcChargeCode chargeCode =  accountService.findChargeCodeByCode("YYYY");
        AcAcademicCharge charge = new AcAcademicChargeImpl();
        charge.setReferenceNo("ABC123");
        charge.setSourceNo("ABC123");
        charge.setDescription("This is an academic charges");
        charge.setAmount(BigDecimal.valueOf(200.00));
        charge.setChargeCode(chargeCode);
        charge.setAccount(account);
        charge.setSession(academicSession);

        // todo
//        accountService.addAccountCharge(account, charge);
    }
}

