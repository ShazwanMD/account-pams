import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';

@Component({
  selector: 'pams-receipt-status',
  templateUrl: './receipt-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class ReceiptStatusComponent {
  @Input() receipt: Receipt;
}
