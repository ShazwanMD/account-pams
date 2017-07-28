import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';

@Component({
  selector: 'pams-settlement-list',
  templateUrl: './settlement-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SettlementListComponent {

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'executed', label: 'Executed'},
    {name: 'action', label: ''}
  ];

  @Input() settlements: Settlement[];
  @Output() view = new EventEmitter<Settlement>();
}
