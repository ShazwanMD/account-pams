import {Routes, RouterModule} from '@angular/router';
import {WaiverApplicationCenterPage} from "./waiver-application-center.page";
import {WaiverApplicationTaskViewPage} from "./waiver-application-task-view.page";
import { WaiverApplicationDetailPage } from "./waiver-application-detail.page";

export const WaiverApplicationRoutes: Routes = [
  {path: 'financialaid/waiver-applications', component: WaiverApplicationCenterPage},
  {path: 'financialaid/waiver-applications/view-task/:taskId', component: WaiverApplicationTaskViewPage},
  {path: 'financialaid/waiver-applications/waiver-application-detail/:referenceNo', component: WaiverApplicationDetailPage},
];
