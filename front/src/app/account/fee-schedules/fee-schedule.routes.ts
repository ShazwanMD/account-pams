import {Routes, RouterModule} from '@angular/router';
import {FeeScheduleCenterPage} from "./fee-schedule-center.page";

export const FeeScheduleRoutes: Routes = [
  {path: 'account/fee-schedules', component: FeeScheduleCenterPage},
];
