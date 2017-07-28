import {Routes} from '@angular/router';
import {FinancialaidPage} from './financialaid.page';
import {SettlementRoutes} from './settlements/settlement.routes';
import {WaiverApplicationRoutes} from './waiver-applications/waiver-application.routes';

export const FinancialaidModuleRoutes: Routes = [
  {path: 'financialaid', component: FinancialaidPage},
  ...SettlementRoutes,
  ...WaiverApplicationRoutes,
];
