import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'pams-listing-advance-payment-statement-center-page',
  templateUrl: './listing-advance-payment-statement-center.page.html',
})

export class ListingAdvancePaymentStatementCenterPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }
}
