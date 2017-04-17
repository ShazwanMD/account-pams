import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {PromoCode} from "../promo-code.interface";

@Component({
  selector: 'pams-promo-code-list',
  templateUrl: './promo-code-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PromoCodeListComponent {

  @Input() promoCodes: PromoCode[];
  @Output() view = new EventEmitter<PromoCode>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''}
  ];
}
