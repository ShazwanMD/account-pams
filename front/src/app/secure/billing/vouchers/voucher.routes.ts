import {Routes} from '@angular/router';
import { VoucherCenterPage } from "./voucher-center.page";

// Route Configuration
export const VoucherRoutes: Routes = [
   {path: 'billing/vouchers', component: VoucherCenterPage},
  // {path: 'billing/vouchers/assigned-tasks', component: VoucherAssignedTaskPage},
  // {path: 'billing/vouchers/pooled-tasks', component: VoucherPooledTaskPage},
  // {path: 'billing/vouchers/view-task/:taskId', component: VoucherViewTaskPage},
];
