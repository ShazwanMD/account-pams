import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef, Input, AfterViewInit,
} from '@angular/core';
import {Observable} from 'rxjs';
import {ReceiptDraftTaskPanel} from './receipt-draft-task.panel';
import {FlowState} from '../../../../core/flow-state.enum';
import {ReceiptRegisterTaskPanel} from './receipt-register-task.panel';
import {ReceiptTask} from '../../../../shared/model/billing/receipt-task.interface';

@Component({
  selector: 'pams-receipt-task-workflow',
  templateUrl: './receipt-task-workflow.panel.html',
})
export class ReceiptTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentRef: ComponentRef<any>;
  @Input() receiptTaskObservable: Observable<ReceiptTask>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.receiptTaskObservable.subscribe((task) => {
      if (task.flowState) {

        console.log('task flowState: ' + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(ReceiptDraftTaskPanel);
            break;
          case FlowState.REGISTERED:
            componentFactory = this.cfr.resolveComponentFactory(ReceiptRegisterTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.receiptTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
