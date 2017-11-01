import {AfterViewInit, Component, OnInit} from '@angular/core';
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
      this.items = [        {
        title: 'Login Account',
        route: '/login',
        icon: 'assignment',
        color: 'blue-700',
        description: '',
      }
        
      ];
    }
  }
}
