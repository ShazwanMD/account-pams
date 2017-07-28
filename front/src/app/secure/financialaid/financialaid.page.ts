import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'pams-financialaid-page',
  templateUrl: './financialaid.page.html',
})

export class FinancialaidPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }
}
