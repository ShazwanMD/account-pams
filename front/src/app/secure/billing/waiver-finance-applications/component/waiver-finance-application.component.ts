import {Component, Input} from '@angular/core';
import {WaiverFinanceApplication} from '../../../../shared/model/billing/waiver-finance-application.interface';

@Component({
  selector: 'pams-waiver-finance-application',
  templateUrl: './waiver-finance-application.component.html',

})
export class WaiverFinanceApplicationComponent {

  @Input() waiverFinanceApplication: WaiverFinanceApplication;
}
