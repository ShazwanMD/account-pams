import {Routes, RouterModule} from '@angular/router';
import {CreditNoteCenterPage} from "./credit-note-center.page";

export const CreditNoteRoutes: Routes = [
  {path: 'billing/credit-notes', component: CreditNoteCenterPage},
];
