import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {CovalentCoreModule} from '@covalent/core';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {IdentityService} from '../../../../services/identity.service';
import {CommonService} from '../../../../services/common.service';
import {BillingService} from '../../../../services/billing.service';
import { VoucherCenterPage } from "./voucher-center.page";
import { RefundPaymentActions } from "../refund-payments/refund-payment.action";
import { AccountSubModule } from "../../account/accounts/index";
import { AcademicSessionSubModule } from "../../account/academic-sessions/index";
import { ChargeCodeSubModule } from "../../account/charge-codes/index";
import { CreditNoteSubModule } from "../credit-notes/index";
import { DebitNoteSubModule } from "../debit-notes/index";
import { RefundPaymentListComponent } from "./component/refund-payment-list.component";
import { VoucherListComponent } from "./component/voucher-list.component";

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    CreditNoteSubModule.forRoot(),
    DebitNoteSubModule.forRoot(),
    appRoutes,
  ],
  declarations: [
    // page, 
    VoucherCenterPage,
    
    //component, 
    VoucherListComponent,
    RefundPaymentListComponent,
    //dialog
  ],
  exports: [],
  entryComponents: [
    // dialog
  ],

})
export class VoucherModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: VoucherModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
        RefundPaymentActions,
      ],
    };
  }
}
