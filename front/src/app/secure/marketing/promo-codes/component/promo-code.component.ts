import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {PromoCode} from '../../../../shared/model/marketing/promo-code.interface';

@Component({
  selector: 'pams-promo-code',
  templateUrl: './promo-code.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PromoCodeComponent {

  @Input() promoCode: PromoCode;
}
