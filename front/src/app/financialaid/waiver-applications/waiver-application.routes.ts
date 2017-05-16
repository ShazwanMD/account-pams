import {Routes, RouterModule} from '@angular/router';
import {WaiverApplicationCenterPage} from "./waiver-application-center.page";
import {WaiverApplicationTaskViewPage} from "./waiver-application-task-view.page";

export const WaiverApplicationRoutes: Routes = [
  {path: 'financialaid/waiver-applications', component: WaiverApplicationCenterPage},
  {path: 'financialaid/waiver-applications/view-task/:taskId', component: WaiverApplicationTaskViewPage},
];
