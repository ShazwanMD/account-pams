import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Settlement} from "../settlement.interface";

@Component({
  selector: 'pams-settlement-list',
  templateUrl: './settlement-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SettlementListComponent {

  @Input() settlements: Settlement[];
  @Output() view = new EventEmitter<Settlement>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'executed', label: 'Executed'},
    {name: 'action', label: ''}
  ];
}
