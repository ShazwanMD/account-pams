import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

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
import {ReceiptTaskCreatorDialog} from "./dialog/receipt-task-creator.dialog";
import {AccountService} from "../../../services/account.service";
import {ReceiptEffects} from "./receipt.effect";
import {EffectsModule} from "@ngrx/effects";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(ReceiptEffects),
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
    ReceiptTaskCreatorDialog,
  ],
  exports: [],
  entryComponents: [
    // dialog
    ReceiptDraftTaskPanel,
    ReceiptRegisterTaskPanel,
    ReceiptItemEditorDialog,
    ReceiptTaskCreatorDialog
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
        AccountService,
        ReceiptActions,
      ],
    };
  }
}
