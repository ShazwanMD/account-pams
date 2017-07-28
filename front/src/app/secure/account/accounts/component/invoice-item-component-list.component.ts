import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';

@Component({
  selector: 'pams-invoice-item-component-list',
  templateUrl: './invoice-item-component-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceItemListAccountComponent implements OnInit {

  @Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];
  @Input() activity: AccountActivity[];
  //private selectedRows: InvoiceItem[];
  private columns: any[] = [
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'chargeCode.description', label: 'Charge Code Description'},
    {name: 'chargeCode.taxCode.code', label: 'Tax Code'},
    {name: 'chargeCode.taxCode.taxRate', label: 'Tax Rate'},
    {name: 'chargeCode.inclusive', label: 'Inclusive'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''},
  ];

  ngOnInit(): void {

  }

}
