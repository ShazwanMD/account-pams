import {Routes} from '@angular/router';
import {DebitNoteCenterPage} from "./debit-note-center.page";
import {DebitNoteTaskDetailPage} from "./debit-note-task-detail.page";

export const DebitNoteRoutes: Routes = [
  {path: 'billing/debit-notes', component: DebitNoteCenterPage},
  {path: 'billing/debit-notes/debit-note-task-detail/:taskId', component: DebitNoteTaskDetailPage},

  // todo(aida): follow invoice
  // {path: 'billing/debit-notes/debit-note-detail/:referenceNo', component: DebitNoteDetailPage},
];
