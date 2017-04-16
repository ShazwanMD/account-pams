import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../services';

@Component({
  selector: 'pams-financialaid-page',
  templateUrl: './financialaid.page.html',
})

export class FinancialaidPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute,
              identityService: IdentityService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }
}
