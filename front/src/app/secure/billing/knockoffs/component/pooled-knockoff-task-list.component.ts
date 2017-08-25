import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {KnockoffTask} from '../../../../shared/model/billing/knockoff-task.interface';
import {MdSnackBar, MdSnackBarConfig, MdSnackBarRef, SimpleSnackBar} from '@angular/material';

@Component({
  selector: 'pams-pooled-knockoff-task-list',
  templateUrl: './pooled-knockoff-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledKnockoffTaskListComponent {

    private columns: any[] = [
                              {name: 'issuedDate', label: 'Date'},                        
                              {name: 'referenceNo', label: 'Reference No'},
                              {name: 'invoice.referenceNo', label: 'Invoice'},
                              {name: 'description', label: 'Description'},
                              {name: 'amount', label: 'Total Amount'},
                              {name: 'action', label: ''},
                            ];

  @Input() knockoffTask: KnockoffTask[];
  @Output() claim: EventEmitter<KnockoffTask> = new EventEmitter<KnockoffTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: KnockoffTask): void {
      console.log('Emitting task');
      let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Claiming invoice');
      snackBarRef.dismiss();
      snackBarRef.afterDismissed().subscribe(() => {
        this.claim.emit(task);
      });
    }
}
