import {Routes} from '@angular/router';

import { WaiverFinanceApplicationTaskViewPage } from "./waiver-finance-application-task-view.page";
import { WaiverFinanceApplicationCenterPage } from "./waiver-finance-application-center.page";

export const WaiverFinanceApplicationRoutes: Routes = [
  {path: 'billing/waiver-finance-applications', component: WaiverFinanceApplicationCenterPage},
  {path: 'billing/waiver-finance-applications/view-task/:taskId', component: WaiverFinanceApplicationTaskViewPage},
  // {
  //   path: 'billing/waiver-finance-applications/waiver-finance-application-detail/:referenceNo',
  //   component: WaiverFinanceApplicationDetailPage
  // },
];
