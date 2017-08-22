import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {Knockoff} from '../../../../shared/model/billing/knockoff.interface';

@Component({
  selector: 'pams-knockoff-list',
  templateUrl: './knockoff-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class KnockoffListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},                        
    {name: 'referenceNo', label: 'Reference No'},
    {name: 'invoice.referenceNo', label: 'Invoice'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Total Amount'},
    {name: 'action', label: ''},
  ];

  @Input() knockoff: Knockoff[];

}
