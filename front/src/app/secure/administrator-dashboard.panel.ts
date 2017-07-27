import {Component, Output, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
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
        route: '/dashboard',
        icon: 'dashboard',
        color: 'blue-700',
        description: '',
      },
        {
          title: 'Account',
          route: '/account',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        }
        ,
        {
          title: 'Billing',
          route: '/billing',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        },
        {
          title: 'Financial Aid',
          route: '/financialaid',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        },
        {
          title: 'Marketing',
          route: '/marketing',
          icon: 'assignment',
          color: 'blue-700',
          description: '',
        },
        {
          title: 'Identities',
          route: '/identity',
          icon: 'assignment',
          color: 'blue-700',
          description: ' ',
        },
        {
          title: 'System',
          route: '/system',
          icon: 'assignment',
          color: 'blue-700',
          description: ' ',
        },
        {
          title: 'Setup',
          route: '/setup',
          icon: 'assignment',
          color: 'blue-700',
          description: ' ',
        },
      ];
    }
  }
}
