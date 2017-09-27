import { DebitNoteKnockoffListComponent } from './component/debit-note-knockoff-list.component';
import { DebitNoteKnockoffCreatorDialog } from './dialog/debit-note-knockoff-creator.dialog';
import { ReceiptSubModule } from './../receipts/index';
import { DebitNoteUnpaidSelectComponent } from './../receipts/component/debit-note-select-unpaid.component';
import { DebitNoteKnockoffDialog } from './dialog/knockoff-debit-note-creator.dialog';
import { KnockoffDebitNoteListComponent } from './component/knockoff-debit-note-list.component';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { appRoutes, appRoutingProviders } from '../../../app.routes';
import { CovalentCoreModule } from '@covalent/core';
import { CommonService, IdentityService } from '../../../../services';
import { BillingService } from '../../../../services/billing.service';
import { EffectsModule } from '@ngrx/effects';
import { AccountSubModule } from '../../account/accounts/index';
import { AccountService } from '../../../../services/account.service';
import { ChargeCodeSubModule } from '../../account/charge-codes/index';
import { AcademicSessionSubModule } from '../../account/academic-sessions/index';
import { CreditNoteSubModule } from '../credit-notes/index';
import { DebitNoteSubModule } from '../debit-notes/index';
import { PipeModule } from '../../../app.pipe.module';
import { KnockoffActions } from './knockoff.action';
import { KnockoffEffects } from './knockoff.effect';
import { KnockoffListComponent } from './component/knockoff-list.component';
import { KnockoffCreatorDialog } from "./dialog/knockoff-creator.dialog";
import { AdvancePaymentSubModule } from "../advance-payments/index";
import { KnockoffDraftTaskPanel } from "./panel/knockoff-draft-task.panel";
import { KnockoffStatusComponent } from './component/knockoff-status.component';
import { KnockoffTaskWorkflowPanel } from "./panel/knockoff-task-workflow.panel";
import { KnockoffTaskDetailPage } from "./knockoff-task-detail.page";
import { AssignedKnockoffTaskListComponent } from './component/assigned-knockoff-task-list.component';
import { PooledKnockoffTaskListComponent } from './component/pooled-knockoff-task-list.component';
import { ArchivedKnockoffListComponent } from './component/archived-knockoff-list.component';
import { KnockoffCenterPage } from "./knockoff-center.page";
import { KnockoffVerifyTaskPanel } from "./panel/knockoff-verify-task.panel";
import { KnockoffInvoiceListComponent } from "./component/knockoff-invoice-list.component";
import { KnockoffAccountChargeListComponent } from "./component/knockoff-account-charge-list.component";
import { InvoiceUnpaidSelectComponent } from "./component/invoice-select-unpaid.component";
import { AccountChargeUnpaidSelectComponent } from "./component/account-charge-select-unpaid.component";
import { InvoiceKnockoffDialog } from "./dialog/knockoff-invoice-creator.dialog";
import { AccountChargeKnockoffDialog } from "./dialog/knockoff-account-charge-creator.dialog";
import { KnockoffItemListComponent } from "./component/knockoff-item-list.component";
import { KnockoffItemDialog } from "./dialog/knockoff-item.dialog";
import {KnockoffAccountChargeItemDialog} from "./dialog/knockoff-account-charge-item.dialog";

@NgModule( {
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
        ReceiptSubModule.forRoot(),
        EffectsModule.run( KnockoffEffects ),
        PipeModule,
    ],
    declarations: [
        // page
        KnockoffDraftTaskPanel,
        KnockoffVerifyTaskPanel,
        KnockoffTaskWorkflowPanel,
        KnockoffTaskDetailPage,
        KnockoffCenterPage,

        // components
        AssignedKnockoffTaskListComponent,
        PooledKnockoffTaskListComponent,
        ArchivedKnockoffListComponent,
        KnockoffListComponent,
        KnockoffStatusComponent,
        KnockoffInvoiceListComponent,
        KnockoffAccountChargeListComponent,
        KnockoffDebitNoteListComponent,
        InvoiceUnpaidSelectComponent,
        AccountChargeUnpaidSelectComponent,
        KnockoffItemListComponent,
        DebitNoteKnockoffListComponent,
        

        

        // dialog
        InvoiceKnockoffDialog,
        AccountChargeKnockoffDialog,
        KnockoffItemDialog,
        KnockoffAccountChargeItemDialog,
        DebitNoteKnockoffDialog,
        DebitNoteKnockoffCreatorDialog,
    ],
    exports: [
        KnockoffListComponent,
        KnockoffDraftTaskPanel,
        KnockoffCenterPage,
        InvoiceKnockoffDialog,
        AccountChargeKnockoffDialog,
        KnockoffDebitNoteListComponent,
        DebitNoteKnockoffDialog,
        DebitNoteKnockoffCreatorDialog,
        
    ],
    entryComponents: [
        KnockoffDraftTaskPanel,
        KnockoffVerifyTaskPanel,
        InvoiceKnockoffDialog,
        AccountChargeKnockoffDialog,
        KnockoffItemDialog,
        KnockoffAccountChargeItemDialog,
        DebitNoteKnockoffDialog,
        DebitNoteKnockoffCreatorDialog,
    ],

} )
export class KnockoffSubModule {
    static forRoot(): ModuleWithProviders {
        return {
            ngModule: KnockoffSubModule,
            providers: [
                appRoutingProviders,
                IdentityService,
                CommonService,
                AccountService,
                BillingService,
                KnockoffActions,
            ],
        };
    }
}