import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {Knockoff} from '../../../../shared/model/billing/knockoff.interface';

@Component({
  selector: 'pams-archived-knockoff-list',
  templateUrl: './archived-knockoff-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedKnockoffListComponent {

    private columns: any[] = [
                              {name: 'issuedDate', label: 'Date'},                        
                              {name: 'referenceNo', label: 'Reference No'},
                              {name: 'invoice.referenceNo', label: 'Invoice'},
                              {name: 'description', label: 'Description'},
                              {name: 'amount', label: 'Total Amount'},
                              {name: 'action', label: ''},
                            ];

                            @Input() knockoff: Knockoff[];
  @Output() view = new EventEmitter<Knockoff>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewKnockoff(invoice: Knockoff): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing invoice', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(invoice);
    });
  }
}
