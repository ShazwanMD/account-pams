import {Routes, RouterModule} from '@angular/router';
import {AccountPage} from "./account.page";
import {AccountRoutes} from "./accounts/account.routes";
import {ChargeCodeRoutes} from "./charge-codes/charge-code.routes";
import {FeeScheduleRoutes} from "./fee-schedules/fee-schedule.routes";
import {AccountChargeRoutes} from "./account-charge/account-charge.routes";

export const AccountModuleRoutes: Routes = [
  {path: 'account', component: AccountPage},
  ...AccountRoutes,
  ...ChargeCodeRoutes,
  ...FeeScheduleRoutes,
  ...AccountChargeRoutes,
];
