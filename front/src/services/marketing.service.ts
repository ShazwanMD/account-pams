import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {PromoCodeItem} from '../app/marketing/promo-codes/promo-code-item.interface';
import {PromoCode} from '../app/marketing/promo-codes/promo-code.interface';

@Injectable()
export class MarketingService {

  private MARKETING_API: string = environment.endpoint + '/api/marketing';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // SETTLEMENT
  // ====================================================================================================

  findPromoCodeByReferenceNo(referenceNo: string): Observable<PromoCode> {
    return this._http.get(this.MARKETING_API + '/promoCodes/' + referenceNo)
      .map((res: Response) => <PromoCode>res.json());
  }

  findPromoCodeById(id: string): Observable<PromoCode> {
    return this._http.get(this.MARKETING_API + '/promoCodes/' + id)
      .map((res: Response) => <PromoCode>res.json());
  }

  findPromoCodes(): Observable<PromoCode[]> {
    console.log('findPromoCodes');
    return this._http.get(this.MARKETING_API + '/promoCodes')
      .map((res: Response) => <PromoCode[]>res.json());
  }

  findPromoCodeItems(promoCode: PromoCode): Observable<PromoCodeItem[]> {
    console.log('findPromoCodeItems');
    return this._http.get(this.MARKETING_API + '/promoCodes/' + promoCode.referenceNo + '/promoCodeItems')
      .map((res: Response) => <PromoCodeItem[]>res.json());
  }

  initPromoCode(promoCode: PromoCode): Observable<String> {
    return this._http.post(this.MARKETING_API + '/promoCodes/init', JSON.stringify(promoCode))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updatePromoCode(promoCode: PromoCode): Observable<Boolean> {
    return Observable.of(true);
    // return this._http.put(this.MARKETING_API + '/promoCodes', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

  addPromoCodeItem(promoCode: PromoCode, promoCodeItem: PromoCodeItem): Observable<String> {
    return this._http.post(this.MARKETING_API + '/promoCodes/' + promoCode.referenceNo + '/promoCodeItems', JSON.stringify(promoCodeItem))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updatePromoCodeItem(promoCode: PromoCode, promoCodeItem: PromoCodeItem) {
    return this._http.put(this.MARKETING_API + '/promoCodes/' + promoCode.referenceNo + '/promoCodeItems/' + promoCodeItem.id, JSON.stringify(promoCodeItem))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deletePromoCodeItem(promoCode: PromoCode, promoCodeItem: PromoCodeItem) {
    return this._http.delete(this.MARKETING_API + '/promoCodes/' + promoCode.referenceNo + '/promoCodeItems/' + promoCodeItem.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}
