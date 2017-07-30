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
import {archivedDebitNoteListReducer, DebitNoteListState} from './debit-notes/debit-note-list.reducer';
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
import {archivedReceiptListReducer, ReceiptListState} from './receipts/receipt-list.reducer';

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
  receiptItems: ReceiptItemListState;
  // selectedInvoice: // previewed invoice to be applied
  // selectedInvoiceItems: // previewed invoice item to be applied
  assignedDebitNoteTasks: DebitNoteTaskListState;
  pooledDebitNoteTasks: DebitNoteTaskListState;
  archivedDebitNotes: DebitNoteListState;
  debitNote: DebitNoteState;
  debitNoteTask: DebitNoteTaskState;
  // debitNoteItems: DebitNoteItemListState;
  assignedCreditNoteTasks: CreditNoteTaskListState;
  pooledCreditNoteTasks: CreditNoteTaskListState;
  archivedCreditNotes: CreditNoteListState;
  creditNote: CreditNoteState;
  creditNoteTask: CreditNoteTaskState;
  // creditNoteItems: CreditNoteItemListState;
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
    invoiceItems: [],
    invoiceDebitNotes: [],
    invoiceCreditNotes: [],
    debitNoteList: [],
    assignedReceiptTasks: [],
    pooledReceiptTasks: [],
    archivedReceipts: [],
    receiptTask: <ReceiptTask>{},
    receipt: <Receipt>{},
    receiptItems: [],
    assignedDebitNoteTasks: [],
    pooledDebitNoteTasks: [],
    archivedDebitNotes: [],
    debitNoteTask: <DebitNoteTask>{},
    debitNote: <DebitNote>{},
    // debitNoteItems: [],
    assignedCreditNoteTasks: [],
    pooledCreditNoteTasks: [],
    archivedCreditNotes: [],
    creditNoteTask: <CreditNoteTask>{},
    creditNote: <CreditNote>{},
    // creditNoteItems: [],
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
  receipts: receiptReducer,
  receiptItems: receiptItemListReducer,
  assignedDebitNoteTasks: assignedDebitNoteTaskListReducer,
  pooledDebitNoteTasks: pooledDebitNoteTaskListReducer,
  archivedDebitNotes: archivedDebitNoteListReducer,
  debitNoteTask: debitNoteTaskReducer,
  debitNote: debitNoteReducer,
  // debitNoteItems: debitNoteItemListReducer,
  assignedCreditNoteTasks: assignedCreditNoteTaskListReducer,
  pooledCreditNoteTasks: pooledCreditNoteTaskListReducer,
  archivedCreditNotes: archivedCreditNoteListReducer,
  creditNoteTask: creditNoteTaskReducer,
  creditNote: creditNoteReducer,
  // creditNoteItems: creditNoteItemListReducer,
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
