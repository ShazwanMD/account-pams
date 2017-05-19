import {Routes, RouterModule} from '@angular/router';
import {ReceiptCenterPage} from "./receipt-center.page";
import {ReceiptTaskViewPage} from "./receipt-task-view.page";

export const ReceiptRoutes: Routes = [
  {path: 'billing/receipts', component: ReceiptCenterPage},
  {path: 'billing/receipts/view-task/:taskId', component: ReceiptTaskViewPage},
];
