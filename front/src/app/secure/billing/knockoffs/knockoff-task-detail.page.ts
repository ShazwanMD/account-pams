import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {KnockoffActions} from './knockoff.action';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {KnockoffTask} from '../../../shared/model/billing/knockoff-task.interface';

@Component({
  selector: 'pams-knockoff-task-detail',
  templateUrl: './knockoff-task-detail.page.html',
})
export class KnockoffTaskDetailPage implements OnInit {

  private KNOCKOFF_TASK: string[] = 'billingModuleState.knockoffTask'.split('.');
  private knockoffTask$: Observable<KnockoffTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions) {
    this.knockoffTask$ = this.store.select(...this.KNOCKOFF_TASK);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { taskId: string }) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findKnockoffTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/invoices']);
  }
}

