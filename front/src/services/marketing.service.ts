import { Injectable } from '@angular/core';
import {Response, Http} from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {PromoCodeItem} from "../app/marketing/promo-codes/promo-code-item.interface";
import {PromoCode} from "../app/marketing/promo-codes/promo-code.interface";

@Injectable()
export class MarketingService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // SETTLEMENT
  // ====================================================================================================

  findPromoCodeByReferenceNo(referenceNo: string): Observable<PromoCode> {
    console.log("encoded uri: " + encodeURI (referenceNo));
    return this.http.get(environment.endpoint + '/api/billing/settlements/' + encodeURI (referenceNo))
      .map((res: Response) => <PromoCode>res.json());
  }

  findPromoCodeById(id: string): Observable<PromoCode> {
    console.log("encoded uri: " + encodeURI (id));
    return this.http.get(environment.endpoint + '/api/billing/settlements/' + encodeURI (id))
      .map((res: Response) => <PromoCode>res.json());
  }

  findPromoCodeItems(promoCode:PromoCode): Observable<PromoCodeItem[]> {
    console.log("findPromoCodeItems");
    return this.http.get(environment.endpoint + '/api/billing/settlements/' + promoCode.referenceNo + "/settlementItems")
      .map((res: Response) => <PromoCodeItem[]>res.json());
  }

  createPromoCode(promoCode: PromoCode): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.post(environment.endpoint + '/api/billing/settlements/create', JSON.stringify(promoCode))
    //   .flatMap(data => Observable.of(true));
  }

  processPromoCode(settlement: PromoCode): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.post(environment.endpoint + '/api/billing/settlements/process', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

  updatePromoCode(settlement: PromoCode): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.put(environment.endpoint + '/api/billing/settlements', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

}
