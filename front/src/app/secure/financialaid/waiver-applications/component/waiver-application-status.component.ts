import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';

@Component({
  selector: 'pams-waiver-application-status',
  templateUrl: './waiver-application-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class WaiverApplicationStatusComponent {
  @Input() waiverApplication: WaiverApplication[];
}
