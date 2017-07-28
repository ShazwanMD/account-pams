import {Routes} from '@angular/router';
import {SettlementCenterPage} from './settlement-center.page';
import {SettlementDetailPage} from './settlement-detail.page';

export const SettlementRoutes: Routes = [
  {path: 'financialaid/settlements', component: SettlementCenterPage},
  {path: 'financialaid/settlements/:referenceNo', component: SettlementDetailPage},
];
