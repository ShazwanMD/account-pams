import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorizationService } from '../../services/authorization.service';

@Component({
    selector: 'pams-management-dashboard-panel',
    templateUrl: './management-dashboard.panel.html',
})

export class ManagementDashboardPanel implements OnInit {

    private items: Object[];

    constructor(private router: Router,
        private route: ActivatedRoute,
        private authz: AuthorizationService) {
    }

    ngOnInit(): void {
        {
            this.items = [{
                title: 'Setup',
                route: '/secure/setup',
                icon: 'assignment',
                color: 'blue-700',
                description: ' ',

                //to be determined
            },
            ];
        }
    }
}
