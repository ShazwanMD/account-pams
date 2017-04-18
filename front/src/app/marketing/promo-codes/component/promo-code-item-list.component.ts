import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {PromoCodeItem} from "../promo-code-item.interface";

@Component({
  selector: 'pams-promo-code-item-list',
  templateUrl: './promo-code-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PromoCodeItemComponent {
  @Input() promoCodeItems: PromoCodeItem[];
}
