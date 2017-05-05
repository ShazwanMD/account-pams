import {Routes, RouterModule} from '@angular/router';

import {MainComponent} from './main/main.component';
import {LoginComponent} from './login/login.component';
import {HomeModuleRoutes} from "./home/home-module.routes";
import {AccountModuleRoutes} from "./account/account-module.routes";
import {BillingModuleRoutes} from "./billing/billing-module.routes";
import {FinancialaidModuleRoutes} from "./financialaid/financialaid-module.routes";
import {MarketingModuleRoutes} from "./marketing/marketing-module.routes";
<<<<<<< HEAD
import {WakjokoModuleRoutes} from "./wakjoko/wakjoko-module.routes";
=======
import {SetupModuleRoutes} from "./setup/setup-module.routes";
>>>>>>> refs/remotes/origin/master

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: '', component: MainComponent,
    children: [
      ...HomeModuleRoutes,
      ...AccountModuleRoutes,
      ...BillingModuleRoutes,
      ...FinancialaidModuleRoutes,
      ...MarketingModuleRoutes,
<<<<<<< HEAD
      ...WakjokoModuleRoutes,
=======
      ...SetupModuleRoutes,
>>>>>>> refs/remotes/origin/master
    ]
  },
];

export const appRoutingProviders: any[] = [];

export const appRoutes: any = RouterModule.forRoot(routes, {useHash: false});
