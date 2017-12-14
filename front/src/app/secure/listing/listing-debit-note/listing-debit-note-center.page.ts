import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'pams-listing-debit-note-center-page',
  templateUrl: './listing-debit-note-center.page.html',
})

export class ListingDebitNoteCenterPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }
}
