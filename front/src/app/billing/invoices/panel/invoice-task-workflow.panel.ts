import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef, Input, AfterViewInit
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {InvoiceTask} from "../invoice-task.interface";
import {InvoiceRegisterTaskPanel} from "./invoice-register-task.panel";
import {FlowState} from "../../../core/flow-state.enum";
import {InvoiceDraftTaskPanel} from "./invoice-draft-task.panel";


@Component({
  selector: 'pams-invoice-task-workflow',
  templateUrl: './invoice-task-workflow.panel.html',
})
export class InvoiceTaskWorkflowPanel implements OnInit,AfterViewInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentReference: ComponentRef<any>;
  @Input() invoiceTask: InvoiceTask;

  constructor(private viewContainerRef: ViewContainerRef,
              private cfr: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    let componentFactory;
    componentFactory = this.cfr.resolveComponentFactory(InvoiceDraftTaskPanel);
    this.componentReference = this.taskPanel.createComponent(componentFactory);
    console.log("task: " + this.invoiceTask.referenceNo);

    // console.log("task: " + this.invoiceTask.referenceNo);
    // switch (FlowState[this.invoiceTask.flowState.toString()]) {
    //   case FlowState.DRAFTED:
    //     componentFactory = this.cfr.resolveComponentFactory(InvoiceDraftTaskPanel);
    //     break;
    //   case FlowState.REGISTERED:
    //     componentFactory = this.cfr.resolveComponentFactory(InvoiceRegisterTaskPanel);
    //     break;
    // }
    // this.componentReference = this.taskPanel.createComponent(componentFactory);
  }

  ngAfterViewInit(): void {
    let componentFactory;
    console.log("task: " + this.invoiceTask.referenceNo);
  }

  ngOnDestroy() {
    if (this.componentReference) {
      this.componentReference.destroy();
    }
  }
}
