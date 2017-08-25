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
import {KnockoffDraftTaskPanel} from './knockoff-draft-task.panel';
//import {InvoiceRegisterTaskPanel} from './invoice-register-task.panel';
//import {InvoiceVerifyTaskPanel} from './invoice-verify-task.panel';
import {KnockoffTask} from '../../../../shared/model/billing/knockoff-task.interface';
import {FlowState} from '../../../../core/flow-state.enum';

@Component({
  selector: 'pams-knockoff-task-workflow',
  templateUrl: './knockoff-task-workflow.panel.html',
})
export class KnockoffTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentRef: ComponentRef<any>;
  @Input() knockoffTaskObservable: Observable<KnockoffTask>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.knockoffTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(KnockoffDraftTaskPanel);
            break;
          case FlowState.VERIFIED:
//            componentFactory = this.cfr.resolveComponentFactory(InvoiceRegisterTaskPanel);
            break;
          case FlowState.APPROVED:
//            componentFactory = this.cfr.resolveComponentFactory(InvoiceVerifyTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.knockoffTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
