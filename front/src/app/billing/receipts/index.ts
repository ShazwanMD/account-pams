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
import {ReceiptTaskCreatorDialog} from "./dialog/receipt-task-creator.dialog";
import {AccountSubModule} from "../../account/accounts/index";
import {AccountService} from "../../../services/account.service";
import {ReceiptEffects} from "./receipt.effect";
import {EffectsModule} from "@ngrx/effects";
import {AssignedReceiptTaskListComponent} from "./component/assigned-receipt-task-list.component";
import {PooledReceiptTaskListComponent} from "./component/pooled-receipt-task-list.component";
import {ReceiptStatusComponent} from "./component/receipt-status.component";
import {ReceiptItemListComponent} from "./component/receipt-item-list.component";
import {AcademicSessionSubModule} from "../../account/academic-sessions/index";
import {ChargeCodeSubModule} from "../../account/charge-codes/index";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(ReceiptEffects),
    AccountSubModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
  ],
  declarations: [
    // page
    ReceiptCenterPage,
    ReceiptTaskViewPage,

    // components
    AssignedReceiptTaskListComponent,
    PooledReceiptTaskListComponent,
    ReceiptTaskWorkflowPanel,
    ReceiptDraftTaskPanel,
    ReceiptRegisterTaskPanel,
    ReceiptStatusComponent,
    ReceiptItemListComponent,

    // dialog
    ReceiptItemEditorDialog,
    ReceiptTaskCreatorDialog,
  ],
  exports: [],
  entryComponents: [
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
