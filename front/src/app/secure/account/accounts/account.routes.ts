import {Routes, RouterModule} from '@angular/router';
import {AccountCenterPage} from "./account-center.page";
import {AccountDetailPage} from "./account-detail.page";

export const AccountRoutes: Routes = [
  {path: 'account/accounts', component: AccountCenterPage},
  {path: 'account/accounts/:code', component: AccountDetailPage},
];
