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
import {RefundPaymentDraftTaskPanel} from './refund-payment-draft-task.panel';
//import {InvoiceRegisterTaskPanel} from './invoice-register-task.panel';
//import {InvoiceVerifyTaskPanel} from './invoice-verify-task.panel';
import {RefundPaymentTask} from '../../../../shared/model/billing/refund-payment-task.interface';
import {FlowState} from '../../../../core/flow-state.enum';

@Component({
  selector: 'pams-refund-payment-task-workflow',
  templateUrl: './refund-payment-task-workflow.panel.html',
})
export class RefundPaymentTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentRef: ComponentRef<any>;
  @Input() refundPaymentTaskObservable: Observable<RefundPaymentTask>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.refundPaymentTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(RefundPaymentDraftTaskPanel);
            break;
          case FlowState.VERIFIED:
//            componentFactory = this.cfr.resolveComponentFactory(InvoiceRegisterTaskPanel);
            break;
          case FlowState.APPROVED:
//            componentFactory = this.cfr.resolveComponentFactory(InvoiceVerifyTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.refundPaymentTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
