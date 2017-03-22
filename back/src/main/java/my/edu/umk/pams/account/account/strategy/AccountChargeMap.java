package my.edu.umk.pams.account.account.strategy;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeType;
import my.edu.umk.pams.account.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PAMS
 */
@Component
public class AccountChargeMap {

    private static Map<AcChargeCodeType, String> map = new HashMap<AcChargeCodeType, String>();
    static {
        map.put(AcChargeCodeType.ACADEMIC, "TMGSEB-MBA-00-H79331");
        map.put(AcChargeCodeType.HOSTEL, "TMGSEB-MBA-00-H79331");
        map.put(AcChargeCodeType.MISCELLANEOUS, "TMGSEB-MBA-00-H79331");
    }

    @Autowired
    private AccountService accountService;

    public AcChargeCode getChargeCode(AcChargeCodeType chargeCodeType) {
        return accountService.findChargeCodeByCode(map.get(chargeCodeType));
    }
}
