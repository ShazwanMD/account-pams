import {Routes} from '@angular/router';
import {KnockoffCenterPage} from './knockoff-center.page';
import {KnockoffTaskDetailPage} from './knockoff-task-detail.page';
//import {KnockoffDetailPage} from './knockoff-detail.page';

export const KnockoffRoutes: Routes = [
  {path: 'billing/knockoffs', component: KnockoffCenterPage},
  {path: 'billing/knockoffs/knockoff-task-detail/:taskId', component: KnockoffTaskDetailPage},
  //{path: 'billing/knockoffs/knockoff-detail/:referenceNo', component: KnockoffDetailPage},
];
