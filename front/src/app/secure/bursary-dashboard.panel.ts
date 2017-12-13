import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthorizationService} from '../../services/authorization.service';

@Component({
  selector: 'pams-bursary-dashboard-panel',
  templateUrl: './bursary-dashboard.panel.html',
})

export class BursaryDashboardPanel implements OnInit {

  private items: Object[];

  constructor(private router: Router,
              private  route: ActivatedRoute,
              private authz: AuthorizationService) {
  }

  ngOnInit(): void {
    {
      this.items = [
        {
          title: 'Dashboard',
          route: '/secure',
          icon: 'assignment',
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
            title: 'Listing',
            route: '/secure/listing',
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
