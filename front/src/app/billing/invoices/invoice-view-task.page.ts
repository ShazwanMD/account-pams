import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceDraftTaskPanel} from "./panel/invoice-draft-task.panel";
import {InvoiceRegisterTaskPanel} from "./panel/invoice-register-task.panel";
import {FlowState} from "../../core/flow-state.enum";
import {InvoiceActions} from "./invoice.action";


@Component({
  selector: 'pams-invoice-view-task',
  templateUrl: './invoice-view-task.page.html',
})
export class InvoiceViewTaskPage implements OnInit {

  @ViewChild('taskPanel', {read: ViewContainerRef})
  private taskPanel: ViewContainerRef;
  private componentReference: ComponentRef<any>;
  private invoiceTask: InvoiceTask = <InvoiceTask>{};

  constructor(private router: Router,
              private route: ActivatedRoute,
              private cfr: ComponentFactoryResolver,
              private actions: InvoiceActions) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {taskId: string}) => {
      let taskId: string = params.taskId;
      this.actions.findInvoiceTaskById(taskId);
      this.switchPanel();
    });
  }

  ngOnDestroy() {
    if (this.componentReference) {
      this.componentReference.destroy();
    }
  }

  goBack(): void {
    this.router.navigate(['/ledger/invoices']);
  }

  switchPanel(): void {
    let componentFactory;
    switch(FlowState[this.invoiceTask.flowState.toString()]){
      case FlowState.DRAFTED:
         componentFactory = this.cfr.resolveComponentFactory(InvoiceDraftTaskPanel);
         break;
      case FlowState.REGISTERED:
         componentFactory = this.cfr.resolveComponentFactory(InvoiceRegisterTaskPanel);
        break;
    }
    this.componentReference = this.taskPanel.createComponent(componentFactory);
  }
}

