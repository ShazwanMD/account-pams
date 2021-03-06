import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../../services';
import {BillingService} from '../../../../services/billing.service';
import {AdvancePaymentSelectComponent} from './component/advance-payment-select.component';
import {EffectsModule} from '@ngrx/effects';
import {AccountSubModule} from '../../account/accounts/index';
import {AccountService} from '../../../../services/account.service';
import {ChargeCodeSubModule} from '../../account/charge-codes/index';
import {AcademicSessionSubModule} from '../../account/academic-sessions/index';
import {CreditNoteSubModule} from '../credit-notes/index';
import {DebitNoteSubModule} from '../debit-notes/index';
import {PipeModule} from '../../../app.pipe.module';
import {ReceiptItemDetailPage} from '../receipts/receipt-item-detail.page';
import { AdvancePaymentCenterPage } from "./advance-payment-center.page";
import { AdvancePaymentActions } from "./advance-payment.action";
import { AdvancePaymentEffects } from "./advance-payment.effect";
import { KnockoffCreatorDialog } from "../knockoffs/dialog/knockoff-creator.dialog";
import { KnockoffSubModule } from "../knockoffs/index";
import {AdvancePaymentListComponent} from './component/advance-payment-list.component';
import { InvoiceUnpaidSelectComponent } from "./component/invoice-select.component";
import { RefundPaymentTaskCreatorDialog } from "../refund-payments/dialog/refund-payment-task-creator.dialog";
import { RefundPaymentActions } from "../refund-payments/refund-payment.action";

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
    EffectsModule.run(AdvancePaymentEffects),
    PipeModule,
  ],
  declarations: [
    // page
    AdvancePaymentCenterPage,


    // components
    AdvancePaymentSelectComponent,
    AdvancePaymentListComponent,
    KnockoffCreatorDialog,
    RefundPaymentTaskCreatorDialog,
    InvoiceUnpaidSelectComponent,

    // dialog
  ],
  exports: [
    AdvancePaymentSelectComponent,  
    KnockoffCreatorDialog,
    RefundPaymentTaskCreatorDialog,
    InvoiceUnpaidSelectComponent,
  ],
  entryComponents: [
    KnockoffCreatorDialog,
    RefundPaymentTaskCreatorDialog,
  ],

})
export class AdvancePaymentSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AdvancePaymentSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        BillingService,
        AdvancePaymentActions,
      ],
    };
  }
}