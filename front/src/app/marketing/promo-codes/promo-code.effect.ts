import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {PromoCodeActions} from "./promo-code.action";
import {from} from "rxjs/observable/from";
import {MarketingService} from "../../../services/marketing.service";
import {Router} from "@angular/router";


@Injectable()
export class PromoCodeEffects {
  constructor(private router: Router,
              private actions$: Actions,
              private promoCodeActions: PromoCodeActions,
              private marketingService: MarketingService) {
  }

  @Effect() findPromoCodeById = this.actions$
    .ofType(PromoCodeActions.FIND_PROMO_CODE_BY_ID)
    .map(action => action.payload)
    .switchMap(id => this.marketingService.findPromoCodeById(id))
    .map(promoCode => this.promoCodeActions.findPromoCodeByIdSuccess(promoCode));

  @Effect() findPromoCodeByReferenceNo$ = this.actions$
    .ofType(PromoCodeActions.FIND_PROMO_CODE_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.marketingService.findPromoCodeByReferenceNo(referenceNo))
    .map(promoCode => this.promoCodeActions.findPromoCodeByReferenceNoSuccess(promoCode))
    .mergeMap(action => from([action, this.promoCodeActions.findPromoCodeItems(action.payload)]));

  @Effect() findPromoCodes$ = this.actions$
    .ofType(PromoCodeActions.FIND_PROMO_CODES)
    .map(action => action.payload)
    .switchMap(() => this.marketingService.findPromoCodes())
    .map(items => this.promoCodeActions.findPromoCodesSuccess(items));

  @Effect() findPromoCodeItems$ = this.actions$
    .ofType(PromoCodeActions.FIND_PROMO_CODE_ITEMS)
    .map(action => action.payload)
    .switchMap(promoCode => this.marketingService.findPromoCodeItems(promoCode))
    .map(items => this.promoCodeActions.findPromoCodeItemsSuccess(items));

  @Effect() initPromoCode$ = this.actions$
    .ofType(PromoCodeActions.INIT_PROMO_CODE)
    .map(action => action.payload)
    .switchMap(promoCode => this.marketingService.initPromoCode(promoCode))
    .map(() => this.promoCodeActions.findPromoCodes());
    // .mergeMap(action => {
    //   from([action, this.router.navigate(['/marketing/promo-codes', action.payload])])
    // });


  @Effect() updatePromoCode$ = this.actions$
    .ofType(PromoCodeActions.UPDATE_PROMO_CODE)
    .map(action => action.payload)
    .switchMap(promoCode => this.marketingService.updatePromoCode(promoCode))
    .map(promoCode => this.promoCodeActions.updatePromoCodeSuccess(promoCode));
}
