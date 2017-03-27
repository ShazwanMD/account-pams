import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'qs-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent {

  routes: Object[] = [{
      title: 'Dashboard',
      route: '/',
      icon: 'dashboard',
    }, {
      title: 'Account',
      route: '/account',
      icon: 'view_quilt',
    }, {
      title: 'Billing',
      route: '/billing',
      icon: 'receipt',
    }, {
      title: 'Financial Aid',
      route: '/financialaid',
      icon: 'receipt',
    }
  ];

  constructor(private _router: Router) {}

  logout(): void {
    this._router.navigate(['/login']);
  }
}
