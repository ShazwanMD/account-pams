import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {IdentityService} from "../../../../services/identity.service";
import {CommonService} from "../../../../services/common.service";
import {InvoiceItem} from "../invoice-item.interface";
import {ChargeCode} from "../../../account/accounts/charge-code.interface";


@Component({
  selector: 'pams-invoice-item-editor',
  templateUrl: './invoice-item-editor.dialog.html',
})

export class InvoiceItemEditorDialog implements OnInit {

  private editForm: FormGroup;

  private selectedChargeCode: ChargeCode;
  private chargeCodes: ChargeCode[] = <ChargeCode[]> [];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef) {
  }

  set invoiceItem(value: InvoiceItem) {
    this.invoiceItem = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<InvoiceItem>{
      id: null,
      description: '',
      amount: 0,
      balanceAmount: 0,
    });

    // this.editForm.patchValue(this.invoiceItem);
  }

  // save(invoice: Invoice, isValid: boolean) {
  //   this.submitted = true; // set form submit to true
  //   this._invoiceService.startInvoiceTask(invoice).subscribe(res => {
  //     let snackBarRef = this._snackBar.open("Invoice started", "OK");
  //     snackBarRef.afterDismissed().subscribe(() => {
  //       this.goBack();
  //     });
  //   });
  // }
}
