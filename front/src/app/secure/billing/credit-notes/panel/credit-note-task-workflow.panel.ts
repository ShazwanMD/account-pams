import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef, Input, AfterViewInit,
} from '@angular/core';
import {Observable} from 'rxjs';
import {FlowState} from '../../../../core/flow-state.enum';
import { CreditNoteRegisterTaskPanel } from './credit-note-register-task.panel';
import { CreditNoteDraftTaskPanel } from './credit-note-draft-task.panel';
import { CreditNoteVerifyTaskPanel } from './credit-note-verify-task.panel';
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';

@Component({
  selector: 'pams-credit-note-task-workflow',
  templateUrl: './credit-note-task-workflow.panel.html',
})
export class CreditNoteTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentRef: ComponentRef<any>;
  @Input() creditNoteTaskObservable: Observable<CreditNoteTask>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.creditNoteTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(CreditNoteDraftTaskPanel);
            break;
          case FlowState.REGISTERED:
            componentFactory = this.cfr.resolveComponentFactory(CreditNoteRegisterTaskPanel);
            break;
          case FlowState.VERIFIED:
            componentFactory = this.cfr.resolveComponentFactory(CreditNoteVerifyTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.creditNoteTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
