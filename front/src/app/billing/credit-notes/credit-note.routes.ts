import {Routes} from '@angular/router';
import {CreditNoteCenterPage} from './credit-note-center.page';
import {CreditNoteTaskDetailPage} from './credit-note-task-detail.page';
import {CreditNoteDetailPage} from './credit-note-detail.page';

export const CreditNoteRoutes: Routes = [
  {path: 'billing/credit-notes', component: CreditNoteCenterPage},
  {path: 'billing/credit-notes/credit-note-task-detail/:taskId', component: CreditNoteTaskDetailPage},

  // todo(aida): follow invoice
  {path: 'billing/credit-notes/credit-note-detail/:referenceNo', component: CreditNoteDetailPage},
];
