import {Component, Output, OnInit, AfterViewInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {TdLoadingService} from '@covalent/core';

@Component({
  selector: 'pams-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})

export class HomePage implements OnInit, AfterViewInit {

  private items: Object[];

  constructor(private _titleService: Title,
              private _loadingService: TdLoadingService) {
  }

  ngAfterViewInit(): void {
    this._titleService.setTitle('PAMS Universiti Malaysia Kelantan');
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
