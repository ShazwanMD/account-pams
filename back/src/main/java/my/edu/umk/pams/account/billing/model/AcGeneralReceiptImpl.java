package my.edu.umk.pams.account.billing.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author PAMS
 */
@Entity(name = "AcGeneralReceipt")
@Table(name = "AC_GNRL_RCPT")
public class AcGeneralReceiptImpl extends AcReceiptImpl implements AcGeneralReceipt {

}
