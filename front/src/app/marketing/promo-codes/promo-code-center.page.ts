import {Component, OnInit, ChangeDetectionStrategy, state} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {PromoCodeActions} from "./promo-code.action";
import {PromoCode} from "./promo-code.interface";
import {MarketingModuleState} from "../index";

@Component({
  selector: 'pams-promo-code-center',
  templateUrl: './promo-code-center.page.html',
})

export class PromoCodeCenterPage implements OnInit {

  private promoCodes$: Observable<PromoCode[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: PromoCodeActions,
              private store: Store<MarketingModuleState>) {
    this.promoCodes$ = this.store.select(state => state.promoCodes);
  }

  goBack(route: string): void {
    this.router.navigate(['/promoCodes']);
  }

  view(promoCode: PromoCode) {
    console.log("promoCode: " + promoCode.referenceNo);
    this.router.navigate(['/view', promoCode.referenceNo]);
  }

  ngOnInit(): void {
    console.log("find promoCodes");
    this.store.dispatch(this.actions.findPromoCodes());
  }
}

