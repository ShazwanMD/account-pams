import {
  Component,
  ComponentFactoryResolver,
  ComponentRef,
  Input,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {Observable} from 'rxjs';
import {FlowState} from '../../../../core/flow-state.enum';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationTask } from "../../../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplicationDraftTaskPanel } from "./waiver-finance-application-draft-task.panel";
import { WaiverFinanceApplicationRegisterTaskPanel } from "./waiver-finance-application-register-task.panel";
import { WaiverFinanceApplicationVerifyTaskPanel } from "./waiver-finance-application-verify-task.panel";

@Component({
  selector: 'pams-waiver-finance-application-task-workflow',
  templateUrl: './waiver-finance-application-task-workflow.panel.html',
})
export class WaiverFinanceApplicationTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;

  @Input() waiverFinanceApplicationTaskObservable: Observable<WaiverFinanceApplicationTask>;
  private componentRef: ComponentRef<any>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.waiverFinanceApplicationTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(WaiverFinanceApplicationDraftTaskPanel);
            break;
          case FlowState.REGISTERED:
            componentFactory = this.cfr.resolveComponentFactory(WaiverFinanceApplicationRegisterTaskPanel);
            break;
          case FlowState.VERIFIED:
            componentFactory = this.cfr.resolveComponentFactory(WaiverFinanceApplicationVerifyTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.waiverFinanceApplicationTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
