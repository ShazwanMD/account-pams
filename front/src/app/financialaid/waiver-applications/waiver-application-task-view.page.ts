import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef,
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {FinancialaidModuleState} from '../index';
import {Store} from '@ngrx/store';
import {WaiverApplicationActions} from './waiver-application.action';
import {WaiverApplicationTask} from '../../shared/model/financialaid/waiver-application-task.interface';

@Component({
  selector: 'pams-waiver-application-task-iew',
  templateUrl: './waiver-application-task-view.page.html',
})
export class WaiverApplicationTaskViewPage implements OnInit {

  private INVOICE_TASK = 'financialaidModuleState.waiverApplicationTask'.split('.');
  private waiverApplicationTask$: Observable<WaiverApplicationTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<FinancialaidModuleState>,
              private actions: WaiverApplicationActions) {
    this.waiverApplicationTask$ = this.store.select(...this.INVOICE_TASK);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {taskId: string}) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findWaiverApplicationTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/financialaid/waiverApplications']);
  }
}
