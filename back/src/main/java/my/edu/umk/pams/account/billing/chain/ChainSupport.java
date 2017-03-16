package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.dao.AcAccountChargeDao;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ChainSupport<C extends ChainContext> implements Command {

    @Autowired
    protected AcAccountChargeDao accountChargeDao;

    @Autowired
    protected AcInvoiceDao invoiceDao;

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public boolean execute(Context context) throws Exception {
        return process((C) context);
    }

    public abstract boolean process(C context) throws Exception;

}
