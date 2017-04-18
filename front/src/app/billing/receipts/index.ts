import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {BillingService} from "../../../services/billing.service";
import {ReceiptCenterPage} from "./receipt-center.page";
import {ReceiptTaskWorkflowPanel} from "./panel/receipt-task-workflow.panel";
import {ReceiptDraftTaskPanel} from "./panel/receipt-draft-task.panel";
import {ReceiptRegisterTaskPanel} from "./panel/receipt-register-task.panel";
import {ReceiptItemEditorDialog} from "./dialog/receipt-item-editor.dialog";
import {ReceiptTaskViewPage} from "./receipt-task-view.page";
import {ReceiptActions} from "./receipt.action";
import {ReceiptTaskListComponent} from "./component/receipt-task-list.component";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
  ],
  declarations: [
    // page
    ReceiptCenterPage,
    ReceiptTaskViewPage,

    // components
    ReceiptTaskListComponent,
    ReceiptTaskWorkflowPanel,
    ReceiptDraftTaskPanel,
    ReceiptRegisterTaskPanel,
    ReceiptItemEditorDialog,
  ],
  exports: [],
  entryComponents: [
    // dialog
    ReceiptDraftTaskPanel,
    ReceiptRegisterTaskPanel,
    ReceiptItemEditorDialog,
  ],

})
export class ReceiptSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ReceiptSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
        ReceiptActions,
      ],
    };
  }
}
