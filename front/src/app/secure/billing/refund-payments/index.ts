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
import { RefundPaymentActions } from './refund-payment.action';
import { RefundPaymentEffects } from './refund-payment.effect';
import { RefundPaymentListComponent } from './component/refund-payment-list.component';
import { RefundPaymentTaskCreatorDialog } from "./dialog/refund-payment-task-creator.dialog";
import { AdvancePaymentSubModule } from "../advance-payments/index";
import { RefundPaymentDraftTaskPanel } from "./panel/refund-payment-draft-task.panel";
import { RefundPaymentStatusComponent } from './component/refund-payment-status.component';
import { RefundPaymentTaskWorkflowPanel } from "./panel/refund-payment-task-workflow.panel";
import { RefundPaymentTaskDetailPage } from "./refund-payment-task-detail.page";
import { AssignedRefundPaymentTaskListComponent } from './component/assigned-refund-payment-task-list.component';
import { PooledRefundPaymentTaskListComponent } from './component/pooled-refund-payment-task-list.component';
import { ArchivedRefundPaymentListComponent } from './component/archived-refund-payment-list.component';
import { RefundPaymentCenterPage } from "./refund-payment-center.page";
import { RefundPaymentVerifyTaskPanel } from "./panel/refund-payment-verify-task.panel";
import { RefundPaymentDetailPage } from "./refund-payment-detail.page";

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
        EffectsModule.run( RefundPaymentEffects ),
        PipeModule,
    ],
    declarations: [
        // page
        RefundPaymentDraftTaskPanel,
        RefundPaymentTaskWorkflowPanel,
        RefundPaymentTaskDetailPage,
        RefundPaymentCenterPage,
        RefundPaymentVerifyTaskPanel,
        RefundPaymentDetailPage,

        // components
        AssignedRefundPaymentTaskListComponent,
        PooledRefundPaymentTaskListComponent,
        ArchivedRefundPaymentListComponent,
        RefundPaymentListComponent,
        RefundPaymentStatusComponent,

        

        // dialog
    ],
    exports: [
        RefundPaymentListComponent,
        RefundPaymentDraftTaskPanel,
        RefundPaymentCenterPage,
        RefundPaymentVerifyTaskPanel,
        RefundPaymentDetailPage,
    ],
    entryComponents: [
        RefundPaymentDraftTaskPanel,
        RefundPaymentVerifyTaskPanel,
    ],

} )
export class RefundPaymentSubModule {
    static forRoot(): ModuleWithProviders {
        return {
            ngModule: RefundPaymentSubModule,
            providers: [
                appRoutingProviders,
                IdentityService,
                CommonService,
                AccountService,
                BillingService,
                RefundPaymentActions,
            ],
        };
    }
}