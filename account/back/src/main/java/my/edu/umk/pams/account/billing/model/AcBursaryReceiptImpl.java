package my.edu.umk.pams.account.billing.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author PAMS
 */
@Entity(name = "AcBursaryReceipt")
@Table(name = "AC_BSRY_RCPT")
public class AcBursaryReceiptImpl extends AcReceiptImpl implements AcBursaryReceipt {

}
