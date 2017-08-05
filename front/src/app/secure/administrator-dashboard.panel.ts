import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthorizationService} from '../../services/authorization.service';

@Component({
  selector: 'pams-administrator-dashboard-panel',
  templateUrl: './administrator-dashboard.panel.html',
})

export class AdministratorDashboardPanel implements OnInit {

  private items: Object[];

  constructor(private router: Router,
              private  route: ActivatedRoute,
              private authz: AuthorizationService) {
  }

  ngOnInit(): void {
    {
      this.items = [{
        title: 'Dashboard',
        route: '/secure/dashboard',
        icon: 'dashboard',
        color: 'blue-700',
        description: '',
      },
        {
          title: 'Account',
          route: '/secure/account',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        }
        ,
        {
          title: 'Billing',
          route: '/secure/billing',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        },
        {
          title: 'Financial Aid',
          route: '/secure/financialaid',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        },
        {
          title: 'Marketing',
          route: '/secure/marketing',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        },
/*        {
          title: 'Identities',
          route: '/secure/identity',
          icon: 'assignment',
          color: 'blue-700',
          description: ' ',
        },
        {
          title: 'System',
          route: '/secure/system',
          icon: 'assignment',
          color: 'blue-700',
          description: ' ',
        },*/
        {
          title: 'Setup',
          route: '/secure/setup',
          icon: 'assignment',
          color: 'blue-700',
          description: ' ',
        },
      ];
    }
  }
}
