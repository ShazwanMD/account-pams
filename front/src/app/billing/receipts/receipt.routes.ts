import {Routes, RouterModule} from '@angular/router';
import {ReceiptCenterPage} from "./receipt-center.page";
import {ReceiptTaskViewPage} from "./receipt-task-view.page";
import { InvoiceItemPaidListComponent } from "../invoices/component/invoice-item-paid-list.component";

export const ReceiptRoutes: Routes = [
  {path: 'billing/receipts', component: ReceiptCenterPage},
  {path: 'billing/receipts/view-task/:taskId', component: ReceiptTaskViewPage},
  {path: 'billing/receipts/view-item/:referenceNo', component: InvoiceItemPaidListComponent},
];
