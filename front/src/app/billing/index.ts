import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {BillingPage} from "./billing.page";
import {BillingService} from "../../services/billing.service";
import {InvoiceTaskState, invoiceTaskReducer} from "./invoices/invoice-task.reducer";
import {InvoiceState, invoiceReducer} from "./invoices/invoice.reducer";
import {InvoiceSubModule} from "./invoices/index";
import {InvoiceTask} from "./invoices/invoice-task.interface";
import {Invoice} from "./invoices/invoice.interface";
import {receiptTaskReducer, ReceiptTaskState} from "./receipts/receipt-task.reducer";
import {receiptReducer, ReceiptState} from "./receipts/receipt.reducer";
import {ReceiptSubModule} from "./receipts/index";
import {ReceiptTask} from "./receipts/receipt-task.interface";
import {Receipt} from "./receipts/receipt.interface";
import {invoiceItemListReducer, InvoiceItemListState} from "./invoices/invoice-item-list.reducer";
import {archivedInvoiceListReducer, invoiceListReducer, InvoiceListState} from "./invoices/invoice-list.reducer";
import {DebitNoteTaskState, debitNoteTaskReducer} from "./debit-notes/debit-note-task.reducer";
import {debitNoteReducer, DebitNoteState} from "./debit-notes/debit-note.reducer";
import {
  assignedInvoiceTaskListReducer,
  InvoiceTaskListState,
  pooledInvoiceTaskListReducer
} from "./invoices/invoice-task-list.reducer";
import {
  assignedReceiptTaskListReducer, pooledReceiptTaskListReducer,
  ReceiptTaskListState
} from "./receipts/receipt-task-list.reducer";
import {DebitNoteSubModule} from "./debit-notes/index";
import {CreditNoteSubModule} from "./credit-notes/index";
import {receiptItemListReducer, ReceiptItemListState} from "./receipts/receipt-item-list.reducer";
import {DebitNoteTask} from "./debit-notes/debit-note-task.interface";
import {DebitNote} from "./debit-notes/debit-note.interface";
import {CreditNoteTaskState, creditNoteTaskReducer} from "./credit-notes/credit-note-task.reducer";
import {CreditNoteState, creditNoteReducer} from "./credit-notes/credit-note.reducer";
import {
  DebitNoteListState,
  archivedDebitNoteListReducer
} from "./debit-notes/debit-note-list.reducer";
import {CreditNoteTask} from "./credit-notes/credit-note-task.interface";
import {CreditNote} from "./credit-notes/credit-note.interface";
import {
  DebitNoteTaskListState,
  assignedDebitNoteTaskListReducer,
  pooledDebitNoteTaskListReducer
} from "./debit-notes/debit-note-task-list.reducer";
import {
  assignedCreditNoteTaskListReducer, CreditNoteTaskListState,
  pooledCreditNoteTaskListReducer
} from "./credit-notes/credit-note-task-list.reducer";
import { archivedCreditNoteListReducer, CreditNoteListState } from "./credit-notes/credit-note-list.reducer";
import { debitNoteItemListReducer, DebitNoteItemListState } from "./debit-notes/debit-note-item-list.reducer";

export interface BillingModuleState {
  assignedInvoiceTasks: InvoiceTaskListState;
  pooledInvoiceTasks: InvoiceTaskListState;
  archivedInvoices: InvoiceListState;
  invoiceTask: InvoiceTaskState;
  invoices: InvoiceListState;
  invoice: InvoiceState;
  invoiceItems: InvoiceItemListState;
  assignedReceiptTasks: ReceiptTaskListState;
  pooledReceiptTasks: ReceiptTaskListState;
  receiptTask: ReceiptTaskState;
  receipt: ReceiptState;
  receiptItems: ReceiptItemListState;
  assignedDebitNoteTasks: DebitNoteTaskListState;
  pooledDebitNoteTasks: DebitNoteTaskListState;
  archivedDebitNotes: DebitNoteListState;
  debitNote: DebitNoteState;
  debitNoteTask: DebitNoteTaskState;
  debitNoteItems: DebitNoteItemListState;
  assignedCreditNoteTasks: CreditNoteTaskListState;
  pooledCreditNoteTasks: CreditNoteTaskListState;
  archivedCreditNotes: CreditNoteListState;
  creditNote: CreditNoteState;
  creditNoteTask: CreditNoteTaskState;
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
    debitNoteList: [],
    assignedReceiptTasks: [],
    pooledReceiptTasks: [],
    receiptTask: <ReceiptTask>{},
    receipt: <Receipt>{},
    receiptItems: [],
    assignedDebitNoteTasks: [],
    pooledDebitNoteTasks: [],
    archivedDebitNotes: [],
    debitNoteTask: <DebitNoteTask>{},
    debitNote: <DebitNote>{},
    debitNoteItems: [],
    assignedCreditNoteTasks: [],
    pooledCreditNoteTasks: [],
    archivedCreditNotes: [],
    creditNoteTask: <CreditNoteTask>{},
    creditNote: <CreditNote>{},
  };

export const billingModuleReducers = {
  assignedInvoiceTasks: assignedInvoiceTaskListReducer,
  pooledInvoiceTasks: pooledInvoiceTaskListReducer,
  archivedInvoices: archivedInvoiceListReducer,
  invoiceTask: invoiceTaskReducer,
  invoices: invoiceListReducer,
  invoice: invoiceReducer,
  invoiceItems: invoiceItemListReducer,
  assignedReceiptTasks: assignedReceiptTaskListReducer,
  pooledReceiptTasks: pooledReceiptTaskListReducer,
  receiptTask: receiptTaskReducer,
  receipts: receiptReducer,
  receiptItems: receiptItemListReducer,
  assignedDebitNoteTasks: assignedDebitNoteTaskListReducer,
  pooledDebitNoteTasks: pooledDebitNoteTaskListReducer,
  archivedDebitNotes: archivedDebitNoteListReducer,
  debitNoteTask: debitNoteTaskReducer,
  debitNote: debitNoteReducer,
  debitNoteItems: debitNoteItemListReducer,
  assignedCreditNoteTasks: assignedCreditNoteTaskListReducer,
  pooledCreditNoteTasks: pooledCreditNoteTaskListReducer,
  archivedCreditNotes: archivedCreditNoteListReducer,
  creditNoteTask: creditNoteTaskReducer,
  creditNote: creditNoteReducer,
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
