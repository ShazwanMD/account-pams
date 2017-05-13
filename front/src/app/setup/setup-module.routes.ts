import {StudyModeListPage} from './study-modes/study-mode-list.page';
import {FacultyCodeListPage} from './faculty-codes/faculty-code-list.page';
import {ProgramCodeListPage} from './program-codes/program-code-list.page';
import {StateCodeListPage} from './state-codes/state-code-list.page';
import {SetupModule} from './index';
import {Routes, RouterModule} from '@angular/router';
import {SetupPage} from "./setup.page";
import {BankCodeListPage} from "./bank-codes/bank-code-list.page";
import {CountryCodeListPage} from './country-codes/country-code-list.page';


export const SetupModuleRoutes: Routes = [
  {
    path: 'setup', component: SetupPage,
    children: [
      {path: 'bank-codes', component: BankCodeListPage},
      {path: 'country-codes', component: CountryCodeListPage},
      {path: 'state-codes', component: StateCodeListPage},
      {path: 'program-codes', component: ProgramCodeListPage},
      {path: 'faculty-codes', component: FacultyCodeListPage},
      {path: 'study-modes', component: StudyModeListPage},
    ]
  },

];
