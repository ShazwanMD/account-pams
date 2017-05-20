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
import {invoiceListReducer, InvoiceListState} from "./invoices/invoice-list.reducer";
import {
  assignedInvoiceTaskListReducer,
  InvoiceTaskListState,
  pooledInvoiceTaskListReducer
} from "./invoices/invoice-task-list.reducer";
import {
  assignedReceiptTaskListReducer, pooledReceiptTaskListReducer,
  ReceiptTaskListState
} from "./receipts/receipt-task-list.reducer";
import { DebitNoteSubModule } from "./debit-notes/index";
import { CreditNoteSubModule } from "./credit-notes/index";
import { InvoiceTaskListComponent } from "./invoices/component/invoice-task-list.component";
import {receiptItemListReducer, ReceiptItemListState} from "./receipts/receipt-item-list.reducer";

export interface BillingModuleState {
  assignedInvoiceTasks: InvoiceTaskListState;
  pooledInvoiceTasks: InvoiceTaskListState;
  invoiceTask: InvoiceTaskState;
  invoices:InvoiceListState;
  invoice: InvoiceState;
  invoiceItems: InvoiceItemListState;
  assignedReceiptTasks: ReceiptTaskListState;
  pooledReceiptTasks: ReceiptTaskListState;
  receiptTask: ReceiptTaskState;
  receipt: ReceiptState;
  receiptItems: ReceiptItemListState;
}
;

export const INITIAL_BILLING_STATE: BillingModuleState =
  <BillingModuleState>{
    assignedInvoiceTasks: [],
    pooledInvoiceTasks: [],
    invoiceTask: <InvoiceTask>{},
    invoices:<Invoice[]>[],
    invoice: <Invoice>{},
    invoiceItems: [],
    assignedReceiptTasks: [],
    pooledReceiptTasks: [],
    receiptTask: <ReceiptTask>{},
    receipt: <Receipt>{},
    receiptItems: [],
  };

export const billingModuleReducers = {
  assignedInvoiceTasks: assignedInvoiceTaskListReducer,
  pooledInvoiceTasks: pooledInvoiceTaskListReducer,
  invoiceTask: invoiceTaskReducer,
  invoices: invoiceListReducer,
  invoice:invoiceReducer,
  invoiceItems: invoiceItemListReducer,
  assignedReceiptTasks: assignedReceiptTaskListReducer,
  pooledReceiptTasks: pooledReceiptTaskListReducer,
  receiptTask: receiptTaskReducer,
  receipts: receiptReducer,
  receiptItems: receiptItemListReducer,
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
