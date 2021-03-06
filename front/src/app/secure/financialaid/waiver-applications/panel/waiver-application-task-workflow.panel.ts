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
import {WaiverApplicationDraftTaskPanel} from './waiver-application-draft-task.panel';
import {FlowState} from '../../../../core/flow-state.enum';
import {WaiverApplicationRegisterTaskPanel} from './waiver-application-register-task.panel';
import {WaiverApplicationVerifyTaskPanel} from './waiver-application-verify-task.panel';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';

@Component({
  selector: 'pams-waiver-application-task-workflow',
  templateUrl: './waiver-application-task-workflow.panel.html',
})
export class WaiverApplicationTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;

  @Input() waiverApplicationTaskObservable: Observable<WaiverApplicationTask>;
  private componentRef: ComponentRef<any>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.waiverApplicationTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(WaiverApplicationDraftTaskPanel);
            break;
          case FlowState.REGISTERED:
            componentFactory = this.cfr.resolveComponentFactory(WaiverApplicationRegisterTaskPanel);
            break;
          case FlowState.VERIFIED:
            componentFactory = this.cfr.resolveComponentFactory(WaiverApplicationVerifyTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.waiverApplicationTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
