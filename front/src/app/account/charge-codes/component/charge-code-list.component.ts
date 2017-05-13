import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {ChargeCode} from "../charge-code.interface";

@Component({
  selector: 'pams-charge-code-list',
  templateUrl: './charge-code-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ChargeCodeListComponent {

  @Input() chargeCodes: ChargeCode[];
  @Output() view = new EventEmitter<ChargeCode>();

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'priority', label: 'Priority'},
    {name: 'action', label: ''}
  ];
}
