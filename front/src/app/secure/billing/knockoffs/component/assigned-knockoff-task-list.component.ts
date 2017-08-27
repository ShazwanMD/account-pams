import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {KnockoffTask} from '../../../../shared/model/billing/knockoff-task.interface';
import {MdSnackBar, MdSnackBarConfig} from '@angular/material';

@Component({
  selector: 'pams-assigned-knockoff-task-list',
  templateUrl: './assigned-knockoff-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedKnockoffTaskListComponent {

    private columns: any[] = [
                              {name: 'issuedDate', label: 'Date'},                        
                              {name: 'referenceNo', label: 'Reference No'},
                              {name: 'invoice.referenceNo', label: 'Invoice'},
                              {name: 'description', label: 'Description'},
                              {name: 'amount', label: 'Total Amount'},
                              {name: 'flowState', label: 'Status'},
                              {name: 'action', label: ''},
                            ];

  @Input() knockoffTasks: KnockoffTask[];
  @Output() view = new EventEmitter<KnockoffTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: KnockoffTask): void {
    console.log('Emitting task');
    let config: MdSnackBarConfig = new MdSnackBarConfig();
    config.duration = 2000;
    let snackBarRef = this.snackBar.open('Viewing knockoff', 'OK', config);
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
