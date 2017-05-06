import {Routes, RouterModule} from '@angular/router';
import {FeeScheduleCenterPage} from "./fee-schedule-center.page";
import {FeeScheduleDetailPage} from "./fee-schedule-detail.page";

export const FeeScheduleRoutes: Routes = [
  {path: 'account/fee-schedules', component: FeeScheduleCenterPage},
  {path: 'account/fee-schedules/:code', component: FeeScheduleDetailPage},
];
