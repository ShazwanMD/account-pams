import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../services';

@Component({
  selector: 'pams-wakjoko-page',
  templateUrl: './wakjoko.page.html',
})

export class WakjokoPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }
}
