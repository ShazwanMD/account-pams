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
        EffectsModule.run( KnockoffEffects ),
        PipeModule,
    ],
    declarations: [
        // page
        KnockoffDraftTaskPanel,
        KnockoffTaskWorkflowPanel,
        KnockoffTaskDetailPage,
        KnockoffCenterPage,

        // components
        AssignedKnockoffTaskListComponent,
        PooledKnockoffTaskListComponent,
        ArchivedKnockoffListComponent,
        KnockoffListComponent,
        KnockoffStatusComponent,
        

        // dialog
    ],
    exports: [
        KnockoffListComponent,
        KnockoffDraftTaskPanel,
        KnockoffCenterPage,
    ],
    entryComponents: [
        KnockoffDraftTaskPanel
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