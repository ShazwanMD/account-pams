import {Routes, RouterModule} from '@angular/router';
import {FinancialaidPage} from "./financialaid.page";
import {SettlementRoutes} from "./settlements/settlement.routes";


export const FinancialaidModuleRoutes: Routes = [
  {path: 'financialaid', component: FinancialaidPage},
  ...SettlementRoutes,
];
