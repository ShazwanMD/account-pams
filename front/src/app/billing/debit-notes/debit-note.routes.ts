import {Routes, RouterModule} from '@angular/router';
import {DebitNoteCenterPage} from "./debit-note-center.page";

export const DebitNoteRoutes: Routes = [
  {path: 'billing/debit-notes', component: DebitNoteCenterPage},
];
