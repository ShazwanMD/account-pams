package my.edu.umk.pams.account.web.module.identity.controller;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.account.vo.AccountCharge;
import my.edu.umk.pams.account.web.module.identity.vo.Actor;
import my.edu.umk.pams.account.web.module.identity.vo.Sponsor;
import my.edu.umk.pams.account.web.module.identity.vo.Sponsorship;
import my.edu.umk.pams.account.web.module.identity.vo.Staff;
import my.edu.umk.pams.account.web.module.identity.vo.Student;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author PAMS
 */
@RestController
@Transactional
@RequestMapping("/api/identity")
public class IdentityController {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityController.class);

    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private IdentityTransformer identityTransformer;

    @Autowired
    AuthenticationManager authenticationManager;

    // ==================================================================================================== //
    // ACTOR
    // ==================================================================================================== //

    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public ResponseEntity<List<Actor>> findActors() {
        return new ResponseEntity<List<Actor>>(identityTransformer
                .toActorVos(identityService.findActors(0, 100)), HttpStatus.OK);
    }

    @RequestMapping(value = "/actors/{identityNo}", method = RequestMethod.GET)
    public ResponseEntity<Actor> findActorByIdentityNo(@PathVariable String identityNo) {
        return new ResponseEntity<Actor>(identityTransformer
                .toActorVo(identityService.findActorByIdentityNo(identityNo)), HttpStatus.OK);
    }

    // ==================================================================================================== //
    // SPONSOR
    // ==================================================================================================== //

    @RequestMapping(value = "/sponsors", method = RequestMethod.GET)
    public ResponseEntity<List<Sponsor>> findSponsors() {
        return new ResponseEntity<List<Sponsor>>(identityTransformer
                .toSponsorVos(identityService.findSponsors(0, 100)), HttpStatus.OK);
    }

    @RequestMapping(value = "/sponsors/{identityNo}", method = RequestMethod.GET)
    public ResponseEntity<Sponsor> findSponsorByIdentityNo(@PathVariable String identityNo) {
        return new ResponseEntity<Sponsor>(identityTransformer
                .toSponsorVo(identityService.findSponsorBySponsorNo(identityNo)), HttpStatus.OK);
    }

    // ==================================================================================================== //
    // STAFF
    // ==================================================================================================== //

    @RequestMapping(value = "/staffs", method = RequestMethod.GET)
    public ResponseEntity<List<Staff>> findStaffs() {
        return new ResponseEntity<List<Staff>>(identityTransformer
                .toStaffVos(identityService.findStaffs(0, 100)), HttpStatus.OK);
    }

    @RequestMapping(value = "/staffs/{identityNo}", method = RequestMethod.GET)
    public ResponseEntity<Staff> findStaffByIdentityNo(@PathVariable String identityNo) {
        return new ResponseEntity<Staff>(identityTransformer
                .toStaffVo(identityService.findStaffByStaffNo(identityNo)), HttpStatus.OK);
    }

    // ==================================================================================================== //
    // STUDENT
    // ==================================================================================================== //

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> findStudents() {
        return new ResponseEntity<List<Student>>(identityTransformer
                .toStudentVos(identityService.findStudents(0, 100)), HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{identityNo}", method = RequestMethod.GET)
    public ResponseEntity<Student> findSponsorshipByIdentityNo(@PathVariable String identityNo) {
        return new ResponseEntity<Student>(identityTransformer
                .toStudentVo(identityService.findStudentByMatricNo(identityNo)), HttpStatus.OK);
    }
    
    
 // ==================================================================================================== //
    // SPONSORSHIP
    // ==================================================================================================== //

    @RequestMapping(value = "/sponsorships", method = RequestMethod.GET)
    public ResponseEntity<List<Sponsorship>> findSponsorships() {
        return new ResponseEntity<List<Sponsorship>>(identityTransformer
                .toSponsorshipVos(identityService.findSponsorships(0, 100)), HttpStatus.OK);
    }

//    @RequestMapping(value = "/sponsorships/{identityNo}", method = RequestMethod.GET)
//    public ResponseEntity<Student> findStudentByIdentityNo(@PathVariable String identityNo) {
//        return new ResponseEntity<Student>(identityTransformer
//                .toStudentVo(identityService.findStudentByMatricNo(identityNo)), HttpStatus.OK);
//    }
    
//	@RequestMapping(value = "/accounts/{code}/sponsorships", method = RequestMethod.GET)
//	public ResponseEntity<List<Sponsorship>> findAccountSponsorships(@PathVariable String code) {
//		AcAccount account = accountService.findAccountByCode(code);
//		return new ResponseEntity<List<Sponsorship>>(
//				 identityTransformer.toSponsorshipVos(identityService.findSponsorships(account)), HttpStatus.OK);
//	}


	@RequestMapping(value = "/sponsorships", method = RequestMethod.POST)
	public ResponseEntity<String> saveSponsorship(@RequestBody Sponsorship vo) {

		AcSponsorship sponsorship = new AcSponsorshipImpl();
		sponsorship.setReferenceNo(vo.getReferenceNo());
		sponsorship.setAccountNo(vo.getAccountNo());
		if (null != vo.getStudent())
			sponsorship.setStudent(identityService.findStudentById(vo.getId()));
		sponsorship.setAmount(vo.getAmount());
		sponsorship.setActive(vo.getActive());
		sponsorship.setStartDate(vo.getStartDate());
		sponsorship.setEndDate(vo.getEndDate());
	    identityService.saveSponsorship(sponsorship);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sponsorships/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateSponsorship(@PathVariable String code, @RequestBody Sponsorship vo) {

        // what can we update?
		AcSponsorship sponsorship = identityService.findSponsorshipById(vo.getId());
		sponsorship.setReferenceNo(vo.getReferenceNo());
		sponsorship.setAccountNo(vo.getAccountNo());
		if (null != vo.getStudent())
			sponsorship.setStudent(identityService.findStudentById(vo.getId()));
		sponsorship.setAmount(vo.getAmount());
		sponsorship.setActive(vo.getActive());
		sponsorship.setStartDate(vo.getStartDate());
		sponsorship.setEndDate(vo.getEndDate());
        identityService.updateSponsorship(sponsorship);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

}
    

