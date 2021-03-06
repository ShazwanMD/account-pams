import {SecurityChargeCodeListPage} from './security-charge-codes/security-charge-code-list.page';
import {BankCodeListPage} from './bank-codes/bank-code-list.page';
import {StudyModeListPage} from './study-modes/study-mode-list.page';
import {FacultyCodeListPage} from './faculty-codes/faculty-code-list.page';
import {ProgramCodeListPage} from './program-codes/program-code-list.page';
import {StateCodeListPage} from './state-codes/state-code-list.page';
import {Routes} from '@angular/router';
import {SetupPage} from './setup.page';
import {CountryCodeListPage} from './country-codes/country-code-list.page';
import {CohortCodeListPage} from './cohort-codes/cohort-code-list.page';
import {ResidencyCodeListPage} from './residency-codes/residency-code-list.page';
import {TaxCodeListPage} from './tax-codes/tax-code-list.page';

export const SetupModuleRoutes: Routes = [
  {
    path: 'setup', component: SetupPage,
    children: [
      {path: 'country-codes', component: CountryCodeListPage},
      {path: 'cohort-codes', component: CohortCodeListPage},
      {path: 'state-codes', component: StateCodeListPage},
      {path: 'program-codes', component: ProgramCodeListPage},
      {path: 'faculty-codes', component: FacultyCodeListPage},
      {path: 'residency-codes', component: ResidencyCodeListPage},
      {path: 'study-modes', component: StudyModeListPage},
      {path: 'bank-codes', component: BankCodeListPage},
      {path: 'tax-codes', component: TaxCodeListPage},
      {path: 'security-charge-code', component: SecurityChargeCodeListPage},

    ],
  },

];
