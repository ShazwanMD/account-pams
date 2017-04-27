import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {BillingPage} from "./billing.page";
import {BillingService} from "../../services/billing.service";
import {InvoiceTaskListState, invoiceTaskListReducer} from "./invoices/invoice-task-list.reducer";
import {InvoiceTaskState, invoiceTaskReducer} from "./invoices/invoice-task.reducer";
import {InvoiceState, invoiceReducer} from "./invoices/invoice.reducer";
import {InvoiceSubModule} from "./invoices/index";
import {InvoiceTask} from "./invoices/invoice-task.interface";
import {Invoice} from "./invoices/invoice.interface";
import {receiptTaskReducer, ReceiptTaskState} from "./receipts/receipt-task.reducer";
import {receiptReducer, ReceiptState} from "./receipts/receipt.reducer";
import {receiptTaskListReducer, ReceiptTaskListState} from "./receipts/receipt-task-list.reducer";
import {ReceiptSubModule} from "./receipts/index";
import {ReceiptTask} from "./receipts/receipt-task.interface";
import {Receipt} from "./receipts/receipt.interface";
import {invoiceItemListReducer, InvoiceItemListState} from "./invoices/invoice-item-list.reducer";

export interface BillingModuleState {
  invoiceTasks: InvoiceTaskListState;
  invoiceTask: InvoiceTaskState;
  invoice: InvoiceState;
  invoiceItems: InvoiceItemListState;
  receiptTasks: ReceiptTaskListState;
  receiptTask: ReceiptTaskState;
  receipt: ReceiptState;
}
;

export const INITIAL_BILLING_STATE: BillingModuleState =
  <BillingModuleState>{
    invoiceTasks: [],
    invoiceTask: <InvoiceTask>{},
    invoice: <Invoice>{},
    invoiceItems: [],
    receiptTasks: [],
    receiptTask: <ReceiptTask>{},
    receipt: <Receipt>{}
  };

export const billingModuleReducers = {
  invoiceTasks: invoiceTaskListReducer,
  invoiceTask: invoiceTaskReducer,
  invoices: invoiceReducer,
  invoiceItems: invoiceItemListReducer,
  receiptTasks: receiptTaskListReducer,
  receiptTask: receiptTaskReducer,
  receipts: receiptReducer
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    InvoiceSubModule.forRoot(),
    ReceiptSubModule.forRoot(),
  ],
  declarations: [
    // page
    BillingPage,
  ],
  exports: [],
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
