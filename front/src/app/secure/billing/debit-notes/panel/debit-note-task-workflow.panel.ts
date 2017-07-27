import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef, Input,
} from '@angular/core';
import {Observable} from 'rxjs';
import {FlowState} from '../../../../core/flow-state.enum';
import {DebitNoteRegisterTaskPanel} from './debit-note-register-task.panel';
import {DebitNoteDraftTaskPanel} from './debit-note-draft-task.panel';
import {DebitNoteVerifyTaskPanel} from './debit-note-verify-task.panel';
import {DebitNoteTask} from '../../../../shared/model/billing/debit-note-task.interface';

@Component({
  selector: 'pams-debit-note-task-workflow',
  templateUrl: './debit-note-task-workflow.panel.html',
})
export class DebitNoteTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentRef: ComponentRef<any>;
  @Input() debitNoteTaskObservable: Observable<DebitNoteTask>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.debitNoteTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(DebitNoteDraftTaskPanel);
            break;
          case FlowState.REGISTERED:
            componentFactory = this.cfr.resolveComponentFactory(DebitNoteRegisterTaskPanel);
            break;
          case FlowState.VERIFIED:
            componentFactory = this.cfr.resolveComponentFactory(DebitNoteVerifyTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.debitNoteTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
