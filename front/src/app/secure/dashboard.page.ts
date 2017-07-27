import {
  Component, OnInit, OnDestroy, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentFactory, ComponentRef,
} from '@angular/core';
import {Router} from '@angular/router';
import {AuthorizationService} from '../../services/authorization.service';
import {AdministratorDashboardPanel} from './administrator-dashboard.panel';

@Component({
  selector: 'pams-dasboard',
  templateUrl: './dashboard.page.html',
})
export class DashboardPage implements OnInit, OnDestroy {

  private componentRef: ComponentRef<any>;
  @ViewChild('dashboardPanel', {read: ViewContainerRef}) dashboardPanel: ViewContainerRef;

  constructor(private authzService: AuthorizationService,
              private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver,
              private router: Router) {
  }

  ngOnInit(): void {
    let componentFactory: ComponentFactory<any>;
    if (this.authzService.hasRole('ROLE_ADMINISTRATOR') && this.authzService.hasRole('ROLE_USER')) {
      componentFactory = this.cfr.resolveComponentFactory(AdministratorDashboardPanel);
    } else if (this.authzService.hasRole('ROLE_USER')) {
      // componentFactory = this.cfr.resolveComponentFactory(ApplicantDashboardPanel);
    }

    // handle null factory
    if (componentFactory) {
      this.componentRef = this.dashboardPanel.createComponent(componentFactory);
    } else {
      this.router.navigate(['/login']);
    }
  }

  ngOnDestroy(): void {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
