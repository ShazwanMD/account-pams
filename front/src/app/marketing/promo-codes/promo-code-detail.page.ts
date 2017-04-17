import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {PromoCode} from "./promo-code.interface";
import {PromoCodeActions} from "./promo-code.action";
import {MarketingModuleState} from "../index";
import {PromoCodeItem} from "./promo-code-item.interface";

@Component({
  selector: 'pams-promo-code-detail',
  templateUrl: './promo-code-detail.page.html',
})

export class PromoCodeDetailPage implements OnInit {

  private promoCode$: Observable<PromoCode>;
  private promoCodeItems$: Observable<PromoCodeItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: PromoCodeActions,
              private store: Store<MarketingModuleState>) {

    this.promoCode$ = this.store.select(state => state.promoCode);
    this.promoCodeItems$ = this.store.select(state => state.promoCodeItems);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {referenceNo: string}) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findPromoCodeByReferenceNo(referenceNo));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/marketing']);
  }
}

