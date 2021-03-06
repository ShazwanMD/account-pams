import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../services';

import {BillingPage} from './billing.page';
import {BillingService} from '../../../services/billing.service';
import {invoiceTaskReducer, InvoiceTaskState} from './invoices/invoice-task.reducer';
import {invoiceReducer, InvoiceState} from './invoices/invoice.reducer';
import {InvoiceSubModule} from './invoices/index';
import {InvoiceTask} from '../../shared/model/billing/invoice-task.interface';
import {Invoice} from '../../shared/model/billing/invoice.interface';
import {receiptTaskReducer, ReceiptTaskState} from './receipts/receipt-task.reducer';
import {receiptReducer, ReceiptState} from './receipts/receipt.reducer';
import {ReceiptSubModule} from './receipts/index';
import {ReceiptTask} from '../../shared/model/billing/receipt-task.interface';
import {Receipt} from '../../shared/model/billing/receipt.interface';
import {invoiceItemListReducer, InvoiceItemListState} from './invoices/invoice-item-list.reducer';
import {archivedInvoiceListReducer, invoiceListReducer, InvoiceListState} from './invoices/invoice-list.reducer';
import {debitNoteTaskReducer, DebitNoteTaskState} from './debit-notes/debit-note-task.reducer';
import {debitNoteReducer, DebitNoteState} from './debit-notes/debit-note.reducer';
import {DebitNoteItemListState, debitNoteItemListReducer} from './debit-notes/debit-note-item-list.reducer';
import {
  assignedInvoiceTaskListReducer,
  InvoiceTaskListState,
  pooledInvoiceTaskListReducer
} from './invoices/invoice-task-list.reducer';
import {
  assignedReceiptTaskListReducer,
  pooledReceiptTaskListReducer,
  ReceiptTaskListState
} from './receipts/receipt-task-list.reducer';
import {DebitNoteSubModule} from './debit-notes/index';
import {CreditNoteSubModule} from './credit-notes/index';
import {receiptItemListReducer, ReceiptItemListState} from './receipts/receipt-item-list.reducer';
import {DebitNoteTask} from '../../shared/model/billing/debit-note-task.interface';
import {DebitNote} from '../../shared/model/billing/debit-note.interface';
import {creditNoteTaskReducer, CreditNoteTaskState} from './credit-notes/credit-note-task.reducer';
import {creditNoteReducer, CreditNoteState} from './credit-notes/credit-note.reducer';
import {archivedDebitNoteListReducer,debitNoteListReducer, DebitNoteListState, dbtListReducer} from './debit-notes/debit-note-list.reducer';
import {CreditNoteTask} from '../../shared/model/billing/credit-note-task.interface';
import {CreditNote} from '../../shared/model/billing/credit-note.interface';
import {
  assignedDebitNoteTaskListReducer,
  DebitNoteTaskListState,
  pooledDebitNoteTaskListReducer
} from './debit-notes/debit-note-task-list.reducer';
import {
  assignedCreditNoteTaskListReducer,
  CreditNoteTaskListState,
  pooledCreditNoteTaskListReducer
} from './credit-notes/credit-note-task-list.reducer';
import {archivedCreditNoteListReducer, CreditNoteListState} from './credit-notes/credit-note-list.reducer';
import {creditNoteItemListReducer, CreditNoteItemListState} from './credit-notes/credit-note-item-list.reducer';
import {PipeModule} from '../../app.pipe.module';
import {invoiceCreditNoteListReducer, InvoiceCreditNoteListState} from './invoices/invoice-credit-note-list.reducer';
import {invoiceDebitNoteListReducer, InvoiceDebitNoteListState} from './invoices/invoice-debit-note-list.reducer';
import {archivedReceiptListReducer, ReceiptListState, receiptListReducer} from './receipts/receipt-list.reducer';
import {receiptInvoiceListReducer, ReceiptInvoiceListState} from './receipts/receipt-invoice.reducer';
import {receiptAccountChargeListReducer, ReceiptAccountChargeListState} from './receipts/receipt-account-charge.reducer';
import {receiptDebitNoteListReducer, ReceiptDebitNoteListState} from './receipts/receipt-debit-note.reducer';
import { AdvancePaymentSubModule } from "./advance-payments/index";
import {advancePaymentListReducer, AdvancePaymentListState} from './advance-payments/advance-payment-list.reducer';
import {AdvancePayment} from '../../shared/model/billing/advance-payment.interface';
import { Knockoff } from "../../shared/model/billing/knockoff.interface";
import {knockoffReducer, KnockoffState} from './knockoffs/knockoff.reducer';
import {knockoffListReducer, KnockoffListState, archivedKnockoffListReducer} from './knockoffs/knockoff-list.reducer';
import { KnockoffTask } from "../../shared/model/billing/knockoff-task.interface";
import { KnockoffSubModule } from "./knockoffs/index";
import { WaiverFinanceApplicationTask } from "../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplication } from "../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationTaskListState, pooledWaiverFinanceApplicationTaskListReducer, assignedWaiverFinanceApplicationTaskListReducer } from "./waiver-finance-applications/waiver-finance-application-task-list.reducer";
import { WaiverFinanceApplicationListState, archivedWaiverFinanceApplicationListReducer, waiverFinanceApplicationListReducer } from "./waiver-finance-applications/waiver-finance-application-list.reducer";
import { WaiverFinanceApplicationTaskState, waiverFinanceApplicationTaskReducer } from "./waiver-finance-applications/waiver-finance-application-task.reducer";
import { waiverFinanceApplicationReducer, WaiverFinanceApplicationState } from "./waiver-finance-applications/waiver-finance-application.reducer";
import {KnockoffTaskListState, assignedKnockoffTaskListReducer, pooledKnockoffTaskListReducer} from './knockoffs/knockoff-task-list.reducer';
import {KnockoffTaskState, knockoffTaskReducer} from './knockoffs/knockoff-task.reducer';
import { WaiverFinanceApplicationSubModule } from "./waiver-finance-applications/index";
import { RefundPaymentState, refundPaymentReducer } from "./refund-payments/refund-payment.reducer";
import { RefundPaymentSubModule } from "./refund-payments/index";
import { RefundPaymentListState, refundPaymentListReducer, archivedRefundPaymentListReducer } from "./refund-payments/refund-payment-list.reducer";
import { RefundPayment } from "../../shared/model/billing/refund-payment.interface";
import { RefundPaymentTaskState, refundPaymentTaskReducer } from "./refund-payments/refund-payment-task.reducer";
import { RefundPaymentTask } from "../../shared/model/billing/refund-payment-task.interface";
import { RefundPaymentTaskListState, assignedRefundPaymentTaskListReducer, pooledRefundPaymentTaskListReducer } from "./refund-payments/refund-payment-task-list.reducer";
import { KnockoffInvoiceListState, knockoffInvoiceListReducer } from "./knockoffs/knockoff-invoice.reducer";
import { KnockoffAccountChargeListState, knockoffAccountChargeListReducer } from "./knockoffs/knockoff-account-charge.reducer";
import { AccountChargeListState, accountChargeListReducer } from '../account/accounts/account-charge-list.reducer';
import { KnockoffItemListState, knockoffItemListReducer } from "./knockoffs/knockoff-item-list.reducer";
import { waiverInvoiceReducer, WaiverInvoiceState } from "./waiver-finance-applications/waiver-invoice.reducer";
import { WaiverItemListState, waiverItemListReducer } from "./waiver-finance-applications/waiver-item-list.reducer";
import { AccountCharge } from '../../shared/model/account/account-charge.interface';
import { KnockoffDebitNoteListState, knockoffDebitNoteListReducer } from "./knockoffs/knockoff-debit-note.reducer";
import { WaiverDebitNoteState, waiverDebitNoteReducer } from "./waiver-finance-applications/waiver-debit-note.reducer";
import { WaiverAccountChargeState, waiverAccountChargeReducer } from "./waiver-finance-applications/waiver-account-charge.reducer";
import { VoucherModule } from "./vouchers/index";

export interface BillingModuleState {
  assignedInvoiceTasks: InvoiceTaskListState;
  pooledInvoiceTasks: InvoiceTaskListState;
  archivedInvoices: InvoiceListState;
  invoiceTask: InvoiceTaskState;
  invoices: InvoiceListState;
  invoice: InvoiceState;
  invoiceItems: InvoiceItemListState;
  invoiceDebitNotes: InvoiceDebitNoteListState;
  invoiceCreditNotes: InvoiceCreditNoteListState;
  assignedReceiptTasks: ReceiptTaskListState;
  pooledReceiptTasks: ReceiptTaskListState;
  archivedReceipts: ReceiptListState;
  receiptTask: ReceiptTaskState;
  receipt: ReceiptState;
  receipts: ReceiptListState;
  receiptItems: ReceiptItemListState;
  receiptInvoice: ReceiptInvoiceListState;
  receiptAccountCharge: ReceiptAccountChargeListState;
  receiptDebitNote: ReceiptDebitNoteListState;
  // selectedInvoice: // previewed invoice to be applied
  // selectedInvoiceItems: // previewed invoice item to be applied
  assignedDebitNoteTasks: DebitNoteTaskListState;
  pooledDebitNoteTasks: DebitNoteTaskListState;
  archivedDebitNotes: DebitNoteListState;
  debitNotes: DebitNoteListState;
  debitNote: DebitNoteState;
  debitNoteTask: DebitNoteTaskState;
  debitNoteList: DebitNoteListState;
  debitNoteItems: DebitNoteItemListState;
  assignedCreditNoteTasks: CreditNoteTaskListState;
  pooledCreditNoteTasks: CreditNoteTaskListState;
  archivedCreditNotes: CreditNoteListState;
  creditNote: CreditNoteState;
  creditNoteTask: CreditNoteTaskState;
  creditNoteItems: CreditNoteItemListState;
  advancePayments: AdvancePaymentListState;
  knockoff: KnockoffState;
  knockoffs: KnockoffListState;
  knockoffTask: KnockoffTaskState;
  knockoffInvoice: KnockoffInvoiceListState;
  knockoffAccountCharge: KnockoffAccountChargeListState;
  knockoffDebitNote: KnockoffDebitNoteListState;
  knockoffItems: KnockoffItemListState;
  assignedKnockoffTasks: KnockoffTaskListState;
  pooledKnockoffTasks: KnockoffTaskListState;
  archivedKnockoffs: KnockoffListState;
  assignedWaiverFinanceApplicationTasks: WaiverFinanceApplicationTaskListState;
  pooledWaiverFinanceApplicationTasks: WaiverFinanceApplicationTaskListState;
  archivedWaiverFinanceApplications: WaiverFinanceApplicationListState;
  waiverFinanceApplicationTask: WaiverFinanceApplicationTaskState;
  waiverFinanceApplication: WaiverFinanceApplicationState;
  waiverFinanceApplications: WaiverFinanceApplicationListState;
  waiverInvoice: WaiverInvoiceState;
  waiverItem: WaiverItemListState;
  waiverDebitNote: WaiverDebitNoteState;
  waiverAccountCharge: WaiverAccountChargeState;
  refundPayment: RefundPaymentState;
  refundPayments: RefundPaymentListState;
  refundPaymentTask: RefundPaymentTaskState;
  assignedRefundPaymentTasks: RefundPaymentTaskListState;
  pooledRefundPaymentTasks: RefundPaymentTaskListState;
  archivedRefundPayments: RefundPaymentListState;
  accountCharges: AccountChargeListState;
}
;

export const INITIAL_BILLING_STATE: BillingModuleState =
  <BillingModuleState>{
    assignedInvoiceTasks: [],
    pooledInvoiceTasks: [],
    archivedInvoices: [],
    invoiceTask: <InvoiceTask>{},
    invoices: <Invoice[]>[],
    invoice: <Invoice>{},
    accountCharge: <AccountCharge>{},
    invoiceItems: [],
    invoiceDebitNotes: [],
    invoiceCreditNotes: [],
    assignedReceiptTasks: [],
    pooledReceiptTasks: [],
    archivedReceipts: [],
    receiptTask: <ReceiptTask>{},
    receipt: <Receipt>{},
    receipts: <Receipt[]>[],
    receiptItems: [],
    receiptInvoice: [],
    receiptAccountCharge: [],
    receiptDebitNote: [],
    assignedDebitNoteTasks: [],
    pooledDebitNoteTasks: [],
    archivedDebitNotes: [],
    debitNoteTask: <DebitNoteTask>{},
    debitNote: <DebitNote>{},
    debitNotes: [],
    debitNoteList: [],
    debitNoteItems: [],
    assignedCreditNoteTasks: [],
    pooledCreditNoteTasks: [],
    archivedCreditNotes: [],
    creditNoteTask: <CreditNoteTask>{},
    creditNote: <CreditNote>{},
    creditNoteItems: [],
    advancePayments: <AdvancePayment[]>[],
    knockoff: <Knockoff>{},
    knockoffs: <Knockoff[]>[],
    knockoffTask: <KnockoffTask>{},
    knockoffInvoice: [],
    knockoffAccountCharge: [],
    knockoffItems: [],
    knockoffDebitNote: [],
    assignedKnockoffTasks: [],
    pooledKnockoffTasks: [],
    archivedKnockoffs: [],
    assignedWaiverFinanceApplicationTasks: [],
    pooledWaiverFinanceApplicationTasks: [],
    archivedWaiverFinanceApplications: [],
    waiverFinanceApplicationTask: <WaiverFinanceApplicationTask>{},
    waiverFinanceApplication: <WaiverFinanceApplicationState>{},
    waiverFinanceApplications: <WaiverFinanceApplication[]>[],
    waiverInvoice: [],
    waiverItem: [],
    waiverDebitNote: [],
    waiverAccountCharge: [],
    refundPayment: <RefundPaymentState>{},
    refundPayments: <RefundPayment[]>[],
    refundPaymentTask: <RefundPaymentTask>{},
    assignedRefundPaymentTasks: [],
    pooledRefundPaymentTasks: [],
    archivedRefundPayments: [],
    accountCharges: [],
  };

export const billingModuleReducers = {
  assignedInvoiceTasks: assignedInvoiceTaskListReducer,
  pooledInvoiceTasks: pooledInvoiceTaskListReducer,
  archivedInvoices: archivedInvoiceListReducer,
  invoiceTask: invoiceTaskReducer,
  invoices: invoiceListReducer,
  invoice: invoiceReducer,
  invoiceItems: invoiceItemListReducer,
  invoiceDebitNotes: invoiceDebitNoteListReducer,
  invoiceCreditNotes: invoiceCreditNoteListReducer,
  assignedReceiptTasks: assignedReceiptTaskListReducer,
  pooledReceiptTasks: pooledReceiptTaskListReducer,
  archivedReceipts: archivedReceiptListReducer,
  receiptTask: receiptTaskReducer,
  receipt: receiptReducer,
  receipts: receiptListReducer,
  receiptItems: receiptItemListReducer,
  receiptInvoice: receiptInvoiceListReducer,
  receiptAccountCharge: receiptAccountChargeListReducer,
  receiptDebitNote: receiptDebitNoteListReducer,
  assignedDebitNoteTasks: assignedDebitNoteTaskListReducer,
  pooledDebitNoteTasks: pooledDebitNoteTaskListReducer,
  archivedDebitNotes: archivedDebitNoteListReducer,
  debitNoteTask: debitNoteTaskReducer,
  debitNotes: debitNoteListReducer,
  debitNote: debitNoteReducer,
  debitNoteList: dbtListReducer,
  debitNoteItems: debitNoteItemListReducer,
  assignedCreditNoteTasks: assignedCreditNoteTaskListReducer,
  pooledCreditNoteTasks: pooledCreditNoteTaskListReducer,
  archivedCreditNotes: archivedCreditNoteListReducer,
  creditNoteTask: creditNoteTaskReducer,
  creditNote: creditNoteReducer,
  creditNoteItems: creditNoteItemListReducer,
  advancePayments: advancePaymentListReducer,
  knockoff: knockoffReducer,
  knockoffs: knockoffListReducer,
  knockoffTask: knockoffTaskReducer,
  knockoffInvoice: knockoffInvoiceListReducer,
  knockoffAccountCharge: knockoffAccountChargeListReducer,
  knockoffItems: knockoffItemListReducer,
  knockoffDebitNote: knockoffDebitNoteListReducer,
  assignedKnockoffTasks: assignedKnockoffTaskListReducer,
  pooledKnockoffTasks: pooledKnockoffTaskListReducer,
  archivedKnockoffs: archivedKnockoffListReducer,
  assignedWaiverFinanceApplicationTasks: assignedWaiverFinanceApplicationTaskListReducer,
  pooledWaiverFinanceApplicationTasks: pooledWaiverFinanceApplicationTaskListReducer,
  archivedWaiverFinanceApplications: archivedWaiverFinanceApplicationListReducer,
  waiverFinanceApplicationTask: waiverFinanceApplicationTaskReducer,
  waiverFinanceApplication: waiverFinanceApplicationReducer,
  waiverFinanceApplications: waiverFinanceApplicationListReducer,
  waiverInvoice: waiverInvoiceReducer,
  waiverItem: waiverItemListReducer,
  waiverDebitNote: waiverDebitNoteReducer,
  waiverAccountCharge: waiverAccountChargeReducer,
  refundPayment: refundPaymentReducer,
  refundPayments: refundPaymentListReducer,
  refundPaymentTask: refundPaymentTaskReducer,
  assignedRefundPaymentTasks: assignedRefundPaymentTaskListReducer,
  pooledRefundPaymentTasks: pooledRefundPaymentTaskListReducer,
  archivedRefundPayments: archivedRefundPaymentListReducer,
  accountCharges: accountChargeListReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    InvoiceSubModule.forRoot(),
    ReceiptSubModule.forRoot(),
    DebitNoteSubModule.forRoot(),
    CreditNoteSubModule.forRoot(),
    KnockoffSubModule.forRoot(),
    AdvancePaymentSubModule.forRoot(),
    WaiverFinanceApplicationSubModule.forRoot(),
    RefundPaymentSubModule.forRoot(),
    VoucherModule.forRoot(),
    PipeModule,
  ],
  declarations: [
    // page
    BillingPage,
  ],
  exports: [],
  entryComponents: [],
})
export class BillingModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: BillingModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
      ],
    };
  }
}
