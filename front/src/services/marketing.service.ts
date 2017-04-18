import {Injectable} from '@angular/core';
import {Response, Http, Headers, RequestOptions} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
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
    console.log("encoded uri: " + encodeURI(referenceNo));
    return this.http.get(environment.endpoint + '/api/marketing/promoCodes/' + encodeURI(referenceNo))
      .map((res: Response) => <PromoCode>res.json());
  }

  findPromoCodeById(id: string): Observable<PromoCode> {
    console.log("encoded uri: " + encodeURI(id));
    return this.http.get(environment.endpoint + '/api/marketing/promoCodes/' + encodeURI(id))
      .map((res: Response) => <PromoCode>res.json());
  }

  findPromoCodes(): Observable<PromoCode[]> {
    console.log("findPromoCodes");
    return this.http.get(environment.endpoint + '/api/marketing/promoCodes')
      .map((res: Response) => <PromoCode[]>res.json());
  }

  findPromoCodeItems(promoCode: PromoCode): Observable<PromoCodeItem[]> {
    console.log("findPromoCodeItems");
    return this.http.get(environment.endpoint + '/api/marketing/promoCodes/' + promoCode.referenceNo + "/promoCodeItems")
      .map((res: Response) => <PromoCodeItem[]>res.json());
  }

  initPromoCode(promoCode: PromoCode): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/marketing/promoCodes/init', JSON.stringify(promoCode), options)
      .map((res: Response) => <String>res.json());
  }

  updatePromoCode(promoCode: PromoCode): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.put(environment.endpoint + '/api/marketing/promoCodes', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }
}
