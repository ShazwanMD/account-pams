import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../../services';
import {FinancialaidService} from '../../../../services/financialaid.service';
import {SettlementEffects} from './settlement.effect';
import {EffectsModule} from '@ngrx/effects';
import {SettlementCenterPage} from './settlement-center.page';
import {SettlementActions} from './settlement.action';
import {IdentityModule} from '../../identity/index';
import {CommonModule} from '../../../common/index';
import {AcademicSessionSubModule} from '../../account/academic-sessions/index';
import {SettlementCreatorByCohortDialog} from './dialog/settlement-creator-by-cohort.dialog';
import {SettlementCreatorByFacultyDialog} from './dialog/settlement-creator-by-faculty.dialog';
import {SettlementCreatorBySponsorDialog} from './dialog/settlement-creator-by-sponsor.dialog';
import {SettlementComponent} from './component/settlement.component';
import {SettlementDetailPage} from './settlement-detail.page';
import {SettlementItemListComponent} from './component/settlement-item-list.component';
import {SettlementListComponent} from './component/settlement-list.component';
import {UploadSettlementListComponent} from './component/upload-settlement-list.component';
import {SettlementItemDialog} from './dialog/settlement-item.dialog';
import {AccountSubModule} from '../../account/accounts/index';
import {InvoiceSubModule} from '../../billing/invoices/index';
import {SettlementFileUploaderDialog} from './dialog/settlement-file-uploader.dialog';
import { UploadSettlementPage } from "./upload-settlement.page";
import {SponsorshipTypeSelectComponent} from './component/sponsorship-type-select.component';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    EffectsModule.run(SettlementEffects),
    AccountSubModule.forRoot(),
    InvoiceSubModule.forRoot(),
  ],
  declarations: [
    // page
    SettlementCenterPage,
    SettlementDetailPage,
    UploadSettlementPage,

    // dialog
    SettlementItemDialog,
    SettlementCreatorByCohortDialog,
    SettlementCreatorByFacultyDialog,
    SettlementCreatorBySponsorDialog,
    SettlementFileUploaderDialog,

    // component
    SettlementComponent,
    SettlementListComponent,
    SettlementItemListComponent,
    UploadSettlementListComponent,
    SponsorshipTypeSelectComponent,
  ],
  exports: [],
  entryComponents: [
    SettlementCreatorByCohortDialog,
    SettlementCreatorByFacultyDialog,
    SettlementCreatorBySponsorDialog,
    SettlementItemDialog,
    SettlementFileUploaderDialog,
    UploadSettlementListComponent,
    SponsorshipTypeSelectComponent,
  ],

})
export class SettlementSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SettlementSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
        SettlementActions,
      ],
    };
  }
}
