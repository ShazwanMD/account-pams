import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef, Input, AfterViewInit
} from '@angular/core';
import {Observable} from "rxjs";
import {InvoiceTask} from "../invoice-task.interface";
import {InvoiceDraftTaskPanel} from "./invoice-draft-task.panel";
import {FlowState} from "../../../core/flow-state.enum";
import {InvoiceRegisterTaskPanel} from "./invoice-register-task.panel";


@Component({
  selector: 'pams-invoice-task-workflow',
  templateUrl: './invoice-task-workflow.panel.html',
})
export class InvoiceTaskWorkflowPanel implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentRef: ComponentRef<any>;
  @Input() invoiceTaskObservable: Observable<InvoiceTask>;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    this.invoiceTaskObservable.subscribe(task => {
      if (task.flowState) {

        console.log("task flowState: " + task.flowState);
        if (this.componentRef) this.componentRef.destroy();
        switch (FlowState[task.flowState.toString()]) {
          case FlowState.DRAFTED:
            componentFactory = this.cfr.resolveComponentFactory(InvoiceDraftTaskPanel);
            break;
          case FlowState.REGISTERED:
            componentFactory = this.cfr.resolveComponentFactory(InvoiceRegisterTaskPanel);
            break;
        }
        this.componentRef = this.taskPanel.createComponent(componentFactory);
        this.componentRef.instance.invoiceTask = task;
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
