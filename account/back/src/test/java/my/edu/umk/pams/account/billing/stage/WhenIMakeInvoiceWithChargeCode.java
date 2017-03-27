package my.edu.umk.pams.account.billing.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcStudent;

public class WhenIMakeInvoiceWithChargeCode extends Stage<WhenIMakeInvoiceWithChargeCode>{

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;
    
    public WhenIMakeInvoiceWithChargeCode I_make_invoice_given_charge_code(){
		return self();}

}
