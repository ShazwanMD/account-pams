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
import { KnockoffCreatorDialog } from "./dialog/knockoff-creator.dialog";

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


        // components


        // dialog
        KnockoffCreatorDialog
    ],
    exports: [
        KnockoffCreatorDialog
    ],
    entryComponents: [
        KnockoffCreatorDialog
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