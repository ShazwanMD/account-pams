import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../services';

@Component({
  selector: 'pams-marketing-page',
  templateUrl: './marketing.page.html',
})

export class MarketingPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }
}
