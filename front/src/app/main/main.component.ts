import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'pams-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent {

  routes: Object[] = [{
      title: 'Home',
      route: '/',
      icon: 'home',
    }, {
      title: 'Dashboard',
      route: '/dashboard',
      icon: 'dashboard',
    }, {
        title: 'Account',
        route: '/account',
        icon: 'receipt',
    }, {
      title: 'Billing',
      route: '/billing',
      icon: 'receipt',
    }, {
      title: 'Financial Aid',
      route: '/financialaid',
      icon: 'receipt',
    }, {
      title: 'Marketing',
      route: '/marketing',
      icon: 'receipt',
    }, {
      title: 'Setup',
      route: '/setup',
      icon: 'receipt',
    }
  ];

  constructor(private _router: Router) {}

  logout(): void {
    this._router.navigate(['/login']);
  }
}
