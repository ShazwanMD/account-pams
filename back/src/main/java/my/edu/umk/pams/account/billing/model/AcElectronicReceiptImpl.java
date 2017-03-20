package my.edu.umk.pams.account.billing.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author PAMS
 */
@Entity(name = "AcElectronicReceipt")
@Table(name = "AC_ELTC_RCPT")
public class AcElectronicReceiptImpl extends AcReceiptImpl implements AcElectronicReceipt {

}
