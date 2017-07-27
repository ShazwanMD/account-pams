import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {CreditNoteTask} from '../../../shared/model/billing/credit-note-task.interface';
import {CreditNoteActions} from './credit-note.action';

@Component({
  selector: 'pams-credit-note-task-detail',
  templateUrl: './credit-note-task-detail.page.html',
})
export class CreditNoteTaskDetailPage implements OnInit {

  private CREDIT_NOTE_TASK = 'billingModuleState.creditNoteTask'.split('.');
  private creditNoteTask$: Observable<CreditNoteTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: CreditNoteActions) {
    this.creditNoteTask$ = this.store.select(...this.CREDIT_NOTE_TASK);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { taskId: string }) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findCreditNoteTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/credit-notes']);
  }
}

