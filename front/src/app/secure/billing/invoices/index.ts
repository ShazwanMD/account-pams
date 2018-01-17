import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService, NotificationService} from '../../../../services';
import {BillingService} from '../../../../services/billing.service';
import {InvoiceEffects} from './invoice.effect';
import {EffectsModule} from '@ngrx/effects';
import {InvoiceCenterPage} from './invoice-center.page';
import {InvoiceActions} from './invoice.action';
import {InvoiceDraftTaskPanel} from './panel/invoice-draft-task.panel';
import {InvoiceRegisterTaskPanel} from './panel/invoice-register-task.panel';
import {InvoiceItemEditorDialog} from './dialog/invoice-item-editor.dialog';
import {InvoiceTaskWorkflowPanel} from './panel/invoice-task-workflow.panel';
import {InvoiceTaskDetailPage} from './invoice-task-detail.page';
import {InvoiceDetailPage} from './invoice-detail.page';
import {InvoiceTaskCreatorDialog} from './dialog/invoice-task-creator.dialog';
import {AccountSubModule} from '../../account/accounts/index';
import {AccountService} from '../../../../services/account.service';
import {InvoiceItemListComponent} from './component/invoice-item-list.component';
import {ChargeCodeSubModule} from '../../account/charge-codes/index';
import {AcademicSessionSubModule} from '../../account/academic-sessions/index';
import {InvoiceVerifyTaskPanel} from './panel/invoice-verify-task.panel';
import {AssignedInvoiceTaskListComponent} from './component/assigned-invoice-task-list.component';
import {PooledInvoiceTaskListComponent} from './component/pooled-invoice-task-list.component';
import {InvoiceSelectComponent} from './component/invoice-select.component';
import {InvoiceStatusComponent} from './component/invoice-status.component';
import {ArchivedInvoiceListComponent} from './component/archived-invoice-list.component';
import {InvoiceDebitNoteListComponent} from './component/invoice-debit-note-list.component';
import {InvoiceCreditNoteListComponent} from './component/invoice-credit-note-list.component';
import {InvoiceActionComponent} from './component/invoice-action.component';
import {CreditNoteSubModule} from '../credit-notes/index';
import {DebitNoteSubModule} from '../debit-notes/index';
import {PipeModule} from '../../../app.pipe.module';
import {InvoiceItemPaidListComponent} from './component/invoice-item-paid-list.component';
import {ReceiptItemDetailPage} from '../receipts/receipt-item-detail.page';
import {InvoicePaidListComponent} from './component/invoice-paid-list.component';
import { InvoiceItemListOnlyComponent } from './component/invoice-item-list-only.component';
import { ReportActions } from '../../../shared/report/report.action';
import { ReportModule } from '../../../shared/report/index';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    CreditNoteSubModule.forRoot(),
    DebitNoteSubModule.forRoot(),
    ReportModule.forRoot(),
    EffectsModule.run(InvoiceEffects),
    PipeModule,
  ],
  declarations: [
    // page
    InvoiceCenterPage,
    InvoiceTaskDetailPage,
    InvoiceDetailPage,
    

    // components
    AssignedInvoiceTaskListComponent,
    PooledInvoiceTaskListComponent,
    ArchivedInvoiceListComponent,
    InvoiceTaskWorkflowPanel,
    InvoiceDraftTaskPanel,
    InvoiceRegisterTaskPanel,
    InvoiceVerifyTaskPanel,
    InvoiceItemEditorDialog,
    InvoiceTaskCreatorDialog,
    InvoiceItemListComponent,
    InvoiceItemListOnlyComponent,
    InvoiceSelectComponent,
    InvoiceStatusComponent,
    InvoiceDebitNoteListComponent,
    InvoiceCreditNoteListComponent,
    InvoiceActionComponent,
    // todo(hajar): move paid, unpaid to receipt

    InvoicePaidListComponent,
    InvoiceItemPaidListComponent,

    // dialog
  ],
  exports: [
    InvoiceSelectComponent,
    // todo(hajar): move paid, unpaid to receipt
    // todo(hajar): these two component belongs in receipt

    InvoicePaidListComponent,
    InvoiceItemPaidListComponent,
    InvoiceItemListComponent,
    InvoiceItemListOnlyComponent,
  ],
  entryComponents: [
    InvoiceDraftTaskPanel,
    InvoiceRegisterTaskPanel,
    InvoiceVerifyTaskPanel,
    InvoiceItemEditorDialog,
    InvoiceTaskCreatorDialog,
    InvoiceSelectComponent,
    InvoiceItemListComponent,
    InvoiceItemListOnlyComponent,
    InvoiceItemPaidListComponent,
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
        ReportActions,
        NotificationService,
      ],
    };
  }
}
