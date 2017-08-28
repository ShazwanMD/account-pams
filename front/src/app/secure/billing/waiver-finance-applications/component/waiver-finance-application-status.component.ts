import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {WaiverFinanceApplication} from '../../../../shared/model/billing/waiver-finance-application.interface';

@Component({
  selector: 'pams-waiver-finance-application-status',
  templateUrl: './waiver-finance-application-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class WaiverFinanceApplicationStatusComponent {
  @Input() waiverFinanceApplication: WaiverFinanceApplication[];
}
