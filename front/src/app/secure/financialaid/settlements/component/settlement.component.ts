import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';

@Component({
  selector: 'pams-settlement',
  templateUrl: './settlement.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SettlementComponent {

  @Input() settlement: Settlement;
}
