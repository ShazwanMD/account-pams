import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {EffectsModule} from '@ngrx/effects';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../../services';
import {AccountService} from '../../../../services/account.service';
import {AccountActions} from './account.action';
import {AccountEffects} from './account.effect';
import {AccountCenterPage} from './account-center.page';
import {AccountListComponent} from './component/account-list.component';
import {SecurityChargeComponent} from './component/security-charge.component';
import {AdmissionChargeComponent} from './component/admission-charge.component';
import {LoanChargeComponent} from './component/loan-charge.component';
import {StudentAffairsChargeComponent} from './component/student-affairs-charge.component';
import {AccountDetailPage} from './account-detail.page';
import {AccountComponent} from './component/account.component';
import {AccountStatusComponent} from './component/account-status.component';
import {AccountComboBoxComponent} from './component/account-combo-box.component';
import {AccountSelectComponent} from './component/account-select.component';
import {AccountCreatorDialog} from './dialog/account-creator.dialog';
import {IdentityModule} from '../../identity/index';
import {AccountActivityListComponent} from './component/account-activity-list.component';
import {AccountChargeListComponent} from './component/account-charge-list.component';
import {AccountWaiverListComponent} from './component/account-waiver-list.component';
import {AccountStudentListComponent} from './component/account-student-list.component';
import {AdmissionChargeEditorDialog} from './dialog/admission-charge-editor.dialog';
import {LoanChargeEditorDialog} from './dialog/loan-charge-editor.dialog';
import {SecurityChargeEditorDialog} from './dialog/security-charge-editor.dialog';
import {StudentAffairsChargeEditorDialog} from './dialog/student-affairs-charge-editor.dialog';
import {CompoundChargeEditorDialog} from './dialog/compound-charge-editor.dialog';
import {CommonModule} from '../../../common/index';
import {AcademicSessionSubModule} from '../academic-sessions/index';
import {AccountSponsorListComponent} from './component/account-sponsor-list.component';
import {InvoiceItemDialog} from './dialog/invoice-item.dialog';
import {InvoiceItemListAccountComponent} from './component/invoice-item-component-list.component';
import {AccountActionComponent} from './component/account-action.component';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    EffectsModule.run(AccountEffects),
  ],
  declarations: [
    // page
    AccountCenterPage,
    AccountDetailPage,

    // component
    AccountActionComponent,
    AccountListComponent,
    AccountComponent,
    AccountStatusComponent,
    AccountActivityListComponent,
    AccountChargeListComponent,
    SecurityChargeComponent,
    AdmissionChargeComponent,
    LoanChargeComponent,
    StudentAffairsChargeComponent,
    AccountWaiverListComponent,
    AccountComboBoxComponent,
    AccountSelectComponent,
    AccountCreatorDialog,
    AdmissionChargeEditorDialog,
    LoanChargeEditorDialog,
    SecurityChargeEditorDialog,
    StudentAffairsChargeEditorDialog,
    CompoundChargeEditorDialog,
    AccountStudentListComponent,
    AccountSponsorListComponent,
    InvoiceItemDialog,
    InvoiceItemListAccountComponent,

  ],
  exports: [
    AccountComboBoxComponent,
    AccountSelectComponent,
    SecurityChargeComponent,
    AdmissionChargeComponent,
    LoanChargeComponent,
    StudentAffairsChargeComponent,
    InvoiceItemDialog,
    InvoiceItemListAccountComponent,
  ],
  entryComponents: [
    AccountCreatorDialog,
    AdmissionChargeEditorDialog,
    LoanChargeEditorDialog,
    StudentAffairsChargeEditorDialog,
    CompoundChargeEditorDialog,
    SecurityChargeEditorDialog,
    InvoiceItemDialog,
    InvoiceItemListAccountComponent,
  ],

})
export class AccountSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AccountSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        AccountActions,
      ],
    };
  }
}
