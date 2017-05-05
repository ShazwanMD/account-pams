import {Routes, RouterModule} from '@angular/router';
import {WaiverApplicationCenterPage} from "./waiver-application-center.page";

// Route Configuration
export const WaiverApplicationRoutes: Routes = [
   {path: 'financialaid/waiver-applications', component: WaiverApplicationCenterPage},
];
