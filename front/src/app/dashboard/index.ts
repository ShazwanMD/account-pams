import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService} from '../../services';
import {IdentityService, BillingService} from '../../services';
import { DashboardPage } from './dashboard.page';
import {PooledTaskListComponent} from './component/pooled-task-list.component';
import {AssignedTaskListComponent} from './component/assigned-task-list.component';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
  ],
  declarations: [
    // page
    DashboardPage,

    PooledTaskListComponent,
    AssignedTaskListComponent,
  ],
  exports: [],
  entryComponents: [],
})
export class DashboardModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: DashboardModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
      ],
    };
  }
}
