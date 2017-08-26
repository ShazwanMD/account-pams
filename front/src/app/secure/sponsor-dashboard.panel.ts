import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorizationService } from '../../services/authorization.service';

@Component({
  selector: 'pams-sponsor-dashboard-panel',
  templateUrl: './sponsor-dashboard.panel.html',
})

export class SponsorDashboardPanel implements OnInit {

  private items: Object[];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private authz: AuthorizationService) {
  }

  ngOnInit(): void {
    {

      this.items = [{
        title: 'Marketing',
        route: '/secure/marketing',
        icon: 'assignment',
        color: 'blue-700',
        description: '',

        //to be determined
      },
      ];

    }
  }
}
