import { OnInit, Component, ViewContainerRef } from "@angular/core";
import { FormGroup, FormBuilder } from "@angular/forms";
import { InvoiceTask } from "../invoice-task.interface";
import { Router, ActivatedRoute } from "@angular/router";
import { MdDialogRef } from "@angular/material";
import { Store } from "@ngrx/store";
import { SetupModuleState } from "../../../setup/index";
import { InvoiceActions } from "../invoice.action";

@Component({
  selector: 'pams-invoice-task-editor',
  templateUrl: './invoice-task-editor.dialog.html',
})

export class InvoiceTaskEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _invoice: InvoiceTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<InvoiceTaskEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: InvoiceActions) {
  }

  set invoice(value: InvoiceTask) {
    this._invoice = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<InvoiceTask>{
        taskId: null,
        description: '',
        totalAmount: 0,
        balanceAmount: 0,
        accountCode:'',
        referenceNo:'',
    });

    if (this.edit) this.editorForm.patchValue(this._invoice);
  }

  submit(invoice: InvoiceTask, isValid: boolean) {
    if (!invoice.taskId) this.store.dispatch(this.actions.startInvoiceTask(invoice));
    else  this.store.dispatch(this.actions.updateInvoice(invoice));
    this.dialog.close();
  }

}