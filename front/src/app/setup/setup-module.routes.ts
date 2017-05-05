import { SetupModule } from './index';
import {Routes, RouterModule} from '@angular/router';
import {SetupPage} from "./setup.page";


export const SetupModuleRoutes: Routes = [
  {path: 'setup', component: SetupPage},
];
