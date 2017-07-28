import {Routes} from '@angular/router';
import {ReceiptCenterPage} from './receipt-center.page';
import {ReceiptTaskViewPage} from './receipt-task-view.page';
import {ReceiptItemDetailPage} from './receipt-item-detail.page';

export const ReceiptRoutes: Routes = [
  {path: 'billing/receipts', component: ReceiptCenterPage},
  {path: 'billing/receipts/view-task/:taskId', component: ReceiptTaskViewPage},
  {path: 'billing/receipts/view-item/:referenceNo', component: ReceiptItemDetailPage},
];
