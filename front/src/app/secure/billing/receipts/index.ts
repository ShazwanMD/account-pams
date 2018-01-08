import { ReceiptItemDetailPage } from './receipt-item-detail.page';
import { DebitNoteReceiptCreatorDialog } from './dialog/debit-note-receipt-creator.dialog';
import { DebitNoteReceiptDialog } from './dialog/debit-note-receipt.dialog';
import { DebitNoteReceiptListComponent } from './component/debit-note-receipt-list.component';
import { ReceiptDebitNoteListComponent } from './component/receipt-debit-note-list.component';
import { DebitNoteUnpaidSelectComponent } from './component/debit-note-select-unpaid.component';
import { DebitNoteSubModule } from './../debit-notes/index';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService, NotificationService} from '../../../../services';
import {BillingService} from '../../../../services/billing.service';
import {ReceiptCenterPage} from './receipt-center.page';
import {ReceiptTaskWorkflowPanel} from './panel/receipt-task-workflow.panel';
import {ReceiptDraftTaskPanel} from './panel/receipt-draft-task.panel';
import {ReceiptRegisterTaskPanel} from './panel/receipt-register-task.panel';
import {ReceiptItemEditorDialog} from './dialog/receipt-item-editor.dialog';
import {ReceiptTaskViewPage} from './receipt-task-view.page';
import {ReceiptActions} from './receipt.action';
import {ReceiptTaskCreatorDialog} from './dialog/receipt-task-creator.dialog';
import {AccountSubModule} from '../../account/accounts/index';
import {AccountService} from '../../../../services/account.service';
import {ReceiptEffects} from './receipt.effect';
import {EffectsModule} from '@ngrx/effects';
import {AssignedReceiptTaskListComponent} from './component/assigned-receipt-task-list.component';
import {PooledReceiptTaskListComponent} from './component/pooled-receipt-task-list.component';
import {ArchivedReceiptListComponent} from './component/archived-receipt-list.component';
import {ReceiptStatusComponent} from './component/receipt-status.component';
import {ReceiptTypeSelectComponent} from './component/receipt-type-select.component';
import {PaymentMethodSelectComponent} from './component/payment-method-select.component';
import {ReceiptItemListComponent} from './component/receipt-item-list.component';
import {AcademicSessionSubModule} from '../../account/academic-sessions/index';
import {ChargeCodeSubModule} from '../../account/charge-codes/index';
import {PromoCodeApplicatorDialog} from './dialog/promo-code-applicator.dialog';
import {InvoiceSubModule} from '../invoices/index';
import {PipeModule} from '../../../app.pipe.module';
import {InvoiceApplicatorDialog} from './dialog/invoice-applicator.dialog';
import { InvoiceApplicatorListComponent } from "./component/invoice-applicator-list.component";
import { InvoiceUnpaidListComponent } from "./component/invoice-unpaid-list.component";
import { InvoiceUnpaidSelectComponent } from "./component/invoice-select-unpaid.component";
import { InvoiceReceiptDialog } from "./dialog/invoice-receipt.dialog";
import { InvoiceReceiptListComponent } from "./component/invoice-receipt-list.component";
import { InvoiceReceiptCreatorDialog } from "./dialog/invoice-receipt-creator.dialog";
import { InvoiceItemSelectComponent } from "./component/invoice-items-select.component";
import { ReceiptVerifyTaskPanel } from "./panel/receipt-verify-task.panel";
import { ReceiptAccountChargeListComponent } from "./component/receipt-account-charge-list.component";
import { AccountChargeReceiptDialog } from "./dialog/account-charge-receipt-creator.dialog";
import { AccountChargeUnpaidSelectComponent } from "./component/account-charge-select-unpaid.component";
import { AccountChargeReceiptListComponent } from './component/account-charge-receipt-list.component';
import { ReceiptItemListOnly } from './component/receipt-item-list-only.component';
import { ReceiptActionComponent } from './component/receipt-action.component';
import { ReportActions } from '../../../shared/report/report.action';
import { DebitNoteApplicatorDialog } from "./dialog/debit-note-applicator.dialog";


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
    InvoiceSubModule.forRoot(),
    PipeModule,
  ],
  declarations: [
    // page
    ReceiptCenterPage,
    ReceiptTaskViewPage,
    ReceiptItemDetailPage,

    // components
    AssignedReceiptTaskListComponent,
    PooledReceiptTaskListComponent,
    ArchivedReceiptListComponent,
    ReceiptTaskWorkflowPanel,
    ReceiptDraftTaskPanel,
    ReceiptRegisterTaskPanel,
    ReceiptVerifyTaskPanel,
    ReceiptStatusComponent,
    ReceiptItemListComponent,
    ReceiptTypeSelectComponent,
    PaymentMethodSelectComponent,
    InvoiceApplicatorListComponent,
    InvoiceUnpaidListComponent,
    InvoiceUnpaidSelectComponent,
    InvoiceReceiptListComponent,
    InvoiceItemSelectComponent,
    ReceiptAccountChargeListComponent,
    AccountChargeUnpaidSelectComponent,
    AccountChargeReceiptListComponent,
    DebitNoteUnpaidSelectComponent,
    ReceiptDebitNoteListComponent,
    DebitNoteReceiptListComponent,
    ReceiptItemListOnly,
    ReceiptActionComponent,

    // dialog
    ReceiptItemEditorDialog,
    ReceiptTaskCreatorDialog,
    PromoCodeApplicatorDialog,
    InvoiceApplicatorDialog,
    InvoiceUnpaidSelectComponent,
    InvoiceReceiptDialog,
    InvoiceReceiptCreatorDialog,
    AccountChargeReceiptDialog,
    DebitNoteReceiptDialog,
    DebitNoteReceiptCreatorDialog,
    DebitNoteApplicatorDialog,
  ],
  exports: [
    DebitNoteUnpaidSelectComponent,
    ReceiptItemListOnly,
  ],
  entryComponents: [
    ReceiptDraftTaskPanel,
    ReceiptRegisterTaskPanel,
    ReceiptItemEditorDialog,
    ReceiptTaskCreatorDialog,
    PromoCodeApplicatorDialog,
    InvoiceApplicatorDialog,
    InvoiceReceiptDialog,
    InvoiceReceiptListComponent,
    InvoiceReceiptCreatorDialog,
    AccountChargeReceiptDialog,
    AccountChargeReceiptListComponent,
    ReceiptAccountChargeListComponent,
    DebitNoteReceiptDialog,
    DebitNoteReceiptCreatorDialog,
    ReceiptItemListOnly,
    DebitNoteApplicatorDialog,
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
        ReportActions,
        NotificationService,
      ],
    };
  }
}
