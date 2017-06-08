import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService} from '../../services';
import {IdentityService, BillingService} from '../../services';
import { DashboardPage } from "./dashboard.page";
import {TaskListComponent} from "./component/task-list.component";



@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
//    InvoiceSubModule.forRoot(),
//    ReceiptSubModule.forRoot(),
//    DebitNoteSubModule.forRoot(),
//    CreditNoteSubModule.forRoot(),
  ],
  declarations: [
    // page
    DashboardPage,
    
    TaskListComponent,
  ],
  exports: [],
  entryComponents: [],
})
export class DashboardModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: DashboardModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
      ],
    };
  }
}
