package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcCreditNoteItem;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

@Component("creditNoteListener")
public class CreditNoteListener implements ApplicationListener<CreditNoteEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AccessListener.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AcInvoiceDao invoiceDao;

	@Autowired
	private SystemService systemService;

	@Override
	public void onApplicationEvent(CreditNoteEvent event) {// a
		if (event instanceof CreditNoteApprovedEvent) {// b
			AcCreditNote creditNote = event.getCreditNote();
			AcInvoice invoice = creditNote.getInvoice();

			LOG.debug("Invoice for creditNote {}", invoice.getReferenceNo());

			List<AcCreditNoteItem> creditNoteItems = billingService.findCreditNoteItems(creditNote);
			for (AcCreditNoteItem creditNoteItem : creditNoteItems) {// c

				LOG.debug("creditNote charge code {}", creditNoteItem.getChargeCode().getCode());

				List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
				BigDecimal sumBalanceAmount = BigDecimal.ZERO;
				for (AcInvoiceItem invoiceItem : invoiceItems) {// d

					BigDecimal total = BigDecimal.ZERO;
					LOG.debug("invoiceItem charge code {}", invoiceItem.getChargeCode().getCode());

					Boolean charge = billingService.hasInvoiceItem(creditNoteItem.getChargeCode());

					if (charge == true) {// e
						// Sekiranya charge code sama dengan charge code dlm
						// invoice
						if (creditNoteItem.getChargeCode().getId().equals(invoiceItem.getChargeCode().getId())) {
							LOG.debug("invoiceItem equals to cdt note charge code {} {}",
									invoiceItem.getChargeCode().getId(), creditNoteItem.getChargeCode().getId());

							// Sekiranya chargecode lebih kecil atau sama dengan
							// invc item

							if (invoiceItem.getBalanceAmount().compareTo(BigDecimal.ZERO) > 0) {
								if (creditNoteItem.getBalanceAmount().compareTo(invoiceItem.getBalanceAmount()) <= 0) {

									BigDecimal balanceAmount = invoiceItem.getBalanceAmount()
											.subtract(creditNoteItem.getBalanceAmount());

									invoiceItem.setBalanceAmount(balanceAmount);
									billingService.updateInvoiceItem(invoice, invoiceItem);

									LOG.debug("creditNote charge code same after subtract {}",
											invoiceItem.getBalanceAmount());

									creditNoteItem.setBalanceAmount(BigDecimal.ZERO);
									billingService.updateCreditNoteItem(creditNote, creditNoteItem);
								}

								// Sekiranya chargecode lebih besar dari invc
								// item
								else if (creditNoteItem.getBalanceAmount()
										.compareTo(invoiceItem.getBalanceAmount()) > 0) {
									BigDecimal balanceAmount = invoiceItem.getBalanceAmount()
											.subtract(creditNoteItem.getBalanceAmount());

									invoiceItem.setBalanceAmount(balanceAmount);
									billingService.updateInvoiceItem(invoice, invoiceItem);

									LOG.debug("creditNote charge code same 2 after subtract {}",
											invoiceItem.getBalanceAmount());

									creditNoteItem.setBalanceAmount(balanceAmount);
									billingService.updateCreditNoteItem(creditNote, creditNoteItem);
								}
							}
							
							else if (invoiceItem.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
								BigDecimal balanceAmount = creditNoteItem.getBalanceAmount()
										.subtract(invoiceItem.getBalanceAmount());

								invoiceItem.setBalanceAmount(balanceAmount);
								billingService.updateInvoiceItem(invoice, invoiceItem);

								LOG.debug("creditNote charge code same 2 after subtract {}",
										invoiceItem.getBalanceAmount());

								creditNoteItem.setBalanceAmount(BigDecimal.ZERO);
								billingService.updateCreditNoteItem(creditNote, creditNoteItem);
							}
						}

					} // e

					else {
						if (!creditNoteItem.getChargeCode().getId().equals(invoiceItem.getChargeCode().getId())) {

							if (invoiceItem.getBalanceAmount().compareTo(BigDecimal.ZERO) > 0) {
								if (creditNoteItem.getBalanceAmount().compareTo(invoiceItem.getBalanceAmount()) <= 0) {
									BigDecimal balanceAmount = invoiceItem.getBalanceAmount()
											.subtract(creditNoteItem.getBalanceAmount());

									invoiceItem.setBalanceAmount(balanceAmount);
									billingService.updateInvoiceItem(invoice, invoiceItem);

									LOG.debug("creditNote charge code same after subtract {}",
											invoiceItem.getBalanceAmount());

									creditNoteItem.setBalanceAmount(BigDecimal.ZERO);
									billingService.updateCreditNoteItem(creditNote, creditNoteItem);

								}

								else if (creditNoteItem.getBalanceAmount()
										.compareTo(invoiceItem.getBalanceAmount()) > 0) {
									BigDecimal balanceAmount = creditNoteItem.getBalanceAmount()
											.subtract(invoiceItem.getBalanceAmount());

									invoiceItem.setBalanceAmount(balanceAmount);
									billingService.updateInvoiceItem(invoice, invoiceItem);

									LOG.debug("creditNote charge code same 2 after subtract {}",
											invoiceItem.getBalanceAmount());

									creditNoteItem.setBalanceAmount(balanceAmount);
									billingService.updateCreditNoteItem(creditNote, creditNoteItem);

								}
							}

							else if (invoiceItem.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
								BigDecimal balanceAmount = creditNoteItem.getBalanceAmount()
										.subtract(invoiceItem.getBalanceAmount());

								invoiceItem.setBalanceAmount(balanceAmount);
								billingService.updateInvoiceItem(invoice, invoiceItem);

								LOG.debug("creditNote charge code same 2 after subtract {}",
										invoiceItem.getBalanceAmount());

								creditNoteItem.setBalanceAmount(BigDecimal.ZERO);
								billingService.updateCreditNoteItem(creditNote, creditNoteItem);
							}
						}
					}

					sumBalanceAmount = invoiceDao.sumBalanceAmount(invoice, invoiceItem,
							securityService.getCurrentUser());

				} // d
				
				billingService.post(creditNote);

				// Hantar total (berjaya)
				if (invoice.isPaid() == false) {// ii
					invoice.setBalanceAmount(sumBalanceAmount);

					if (invoice.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
						invoice.setPaid(true);
						billingService.updateInvoice(invoice);
					}
					
					
				} // ii

				else {// i

					invoice.setBalanceAmount(BigDecimal.ZERO);

					String referenceNo = systemService
							.generateReferenceNo(AccountConstants.ADVANCE_PAYMENT_REFRENCE_NO);
					LOG.debug("Processing application with refNo {}", referenceNo);

					AcAdvancePayment advancePayment = new AcAdvancePaymentImpl();
					advancePayment.setReferenceNo(referenceNo);
					advancePayment.setAmount(sumBalanceAmount.abs());
					advancePayment.setBalanceAmount(sumBalanceAmount.abs());
					advancePayment.setDescription("Advance Payment " + referenceNo);
					advancePayment.setSourceNo(creditNote.getReferenceNo());
					advancePayment.setStatus(false);
					advancePayment.setAccount(invoice.getAccount());
					advancePayment.setSession(invoice.getSession());
					billingService.addAdvancePayment(advancePayment, securityService.getCurrentUser());
				} // i
			} // c

		} // b
	}// a
}
