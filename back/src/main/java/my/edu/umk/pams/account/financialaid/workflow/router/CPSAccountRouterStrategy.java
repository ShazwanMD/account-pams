 package my.edu.umk.pams.account.financialaid.workflow.router;

import java.util.Arrays;
import java.util.List;

import my.edu.umk.pams.account.common.router.RouterStrategySupport;


public class CPSAccountRouterStrategy extends RouterStrategySupport {
	
      
    @Override
    public List<String> findRegistererCandidates() {
        return Arrays.asList("GRP_PEN_PGW_PTJ_CPS", "GRP_ADM");   //pen-pegawai-ptj-cps, root
    }
    
    @Override
    public List<String> findVerifierCandidates() {
        return Arrays.asList("GRP_PGW_PTJ_CPS", "GRP_ADM");   //cps-kerani , root
    }
    

    
    
}
