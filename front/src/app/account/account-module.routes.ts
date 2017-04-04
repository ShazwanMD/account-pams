import {Routes, RouterModule} from '@angular/router';
import {AccountPage} from "./account.page";
import {AccountRoutes} from "./accounts/account.routes";


export const AccountModuleRoutes: Routes = [
  {path: 'account', component: AccountPage},
  ...AccountRoutes,
];
