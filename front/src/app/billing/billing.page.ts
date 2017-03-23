import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../services';
import {BillingService} from "../../services/billing.service";

@Component({
  selector: 'pams-billing-page',
  templateUrl: './billing.page.html',
})

export class BillingPage implements OnInit {

  private _router: Router;
  private _route: ActivatedRoute;
  private _identityService: IdentityService;
  private _billingService: BillingService;

  constructor(router: Router,
              route: ActivatedRoute,
              identityService: IdentityService,
              billingService: BillingService) {
    this._router = router;
    this._route = route;
    this._billingService = billingService;
  }

  ngOnInit(): void {
    this._route.params.subscribe(() => {
    });
  }
}
