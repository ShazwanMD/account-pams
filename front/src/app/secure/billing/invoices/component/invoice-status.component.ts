import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-status',
  templateUrl: './invoice-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class InvoiceStatusComponent {
  @Input() invoice: Invoice;
}
