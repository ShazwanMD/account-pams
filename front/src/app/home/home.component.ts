import {Component, Output, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from "../../services/identity.service";
import {CommonService} from "../../services/common.service";

@Component({
  selector: 'pams-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})

export class HomeComponent implements OnInit {

  private _router: Router;
  private _route: ActivatedRoute;
  private _identityService: IdentityService;
  private _commonService: CommonService;

  private items: Object[];

  constructor(router: Router, route: ActivatedRoute, identityService: IdentityService, commonService: CommonService) {
    this._router = router;
    this._route = route;
    this._identityService = identityService;
    this._commonService = commonService;
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
      ];
    }
    ;
  }
}
