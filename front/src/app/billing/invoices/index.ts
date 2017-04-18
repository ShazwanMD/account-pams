import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {BillingService} from "../../../services/billing.service";
import {InvoiceEffects} from "./invoice.effect";
import {EffectsModule} from "@ngrx/effects";
import {InvoiceCenterPage} from "./invoice-center.page";
import {InvoiceTaskListComponent} from "./component/invoice-task-list.component";
import {InvoiceActions} from "./invoice.action";
import {InvoiceDraftTaskPanel} from "./panel/invoice-draft-task.panel";
import {InvoiceRegisterTaskPanel} from "./panel/invoice-register-task.panel";
import {InvoiceItemEditorDialog} from "./dialog/invoice-item-editor.dialog";
import {InvoiceTaskWorkflowPanel} from "./panel/invoice-task-workflow.panel";
import {InvoiceTaskViewPage} from "./invoice-task-view.page";
import {InvoiceTaskCreatorDialog} from "./dialog/invoice-task-creator.dialog";
import {AccountSubModule} from "../../account/accounts/index";
import {AccountModule} from "../../account/index";
import {AccountService} from "../../../services/account.service";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(InvoiceEffects),
    AccountSubModule.forRoot(),
  ],
  declarations: [
    // page
    InvoiceCenterPage,
    InvoiceTaskViewPage,

    // components
    InvoiceTaskListComponent,
    InvoiceTaskWorkflowPanel,
    InvoiceDraftTaskPanel,
    InvoiceRegisterTaskPanel,
    InvoiceItemEditorDialog,
    InvoiceTaskCreatorDialog,
  ],
  exports: [],
  entryComponents: [
    InvoiceDraftTaskPanel,
    InvoiceRegisterTaskPanel,
    InvoiceItemEditorDialog,
    InvoiceTaskCreatorDialog,
  ],

})
export class InvoiceSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: InvoiceSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        BillingService,
        InvoiceActions,
      ],
    };
  }
}
