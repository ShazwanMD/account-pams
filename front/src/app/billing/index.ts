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
import {ActionReducer, combineReducers} from "@ngrx/store";
import {InvoiceSubModule} from "./invoices/index";
import {InvoiceTask} from "./invoices/invoice-task.interface";
import {Invoice} from "./invoices/invoice.interface";
import {receiptTaskReducer} from "./receipts/receipt-task.reducer";
import {receiptReducer} from "./receipts/receipt.reducer";
import {receiptTaskListReducer} from "./receipts/receipt-task-list.reducer";

export interface BillingModuleState {
  invoiceTasks: InvoiceTaskListState;
  invoiceTask: InvoiceTaskState;
  invoice: InvoiceState;
  receiptTasks: InvoiceTaskListState;
  receiptTask: InvoiceTaskState;
  receipt: InvoiceState;
}
;

export const INITIAL_BILLING_STATE: BillingModuleState =
  <BillingModuleState>{
    invoiceTasks: [],
    invoiceTask: <InvoiceTask>{},
    invoice: <Invoice>{},
    receiptTasks: [],
    receiptTask: <InvoiceTask>{},
    receipt: <Invoice>{}
  };

export const billingModuleReducers = {
  invoiceTasks: invoiceTaskListReducer,
  invoiceTask: invoiceTaskReducer,
  invoices: invoiceReducer,
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
