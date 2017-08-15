import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';

@Component({
  selector: 'pams-upload-settlement-list',
  templateUrl: './upload-settlement-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class UploadSettlementListComponent {

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'executed', label: 'Executed'},
    {name: 'action', label: ''}
  ];

  @Input() settlements: Settlement[];
  @Output() view = new EventEmitter<Settlement>();
}
