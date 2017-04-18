import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptItem} from "../receipt-item.interface";
import {ChargeCode} from "../../../account/accounts/charge-code.interface";


@Component({
  selector: 'pams-receipt-item-editor',
  templateUrl: './receipt-item-editor.dialog.html',
})

export class ReceiptItemEditorDialog implements OnInit {

  private editForm: FormGroup;

  private selectedChargeCode: ChargeCode;
  private chargeCodes: ChargeCode[] = <ChargeCode[]> [];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef) {
  }

  set receiptItem(value: ReceiptItem) {
    this.receiptItem = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<ReceiptItem>{
      id: null,
      description: '',
      amount: 0,
      balanceAmount: 0,
    });

    // this.editForm.patchValue(this.receiptItem);
  }

  // save(receipt: Receipt, isValid: boolean) {
  //   this.submitted = true; // set form submit to true
  //   this._receiptService.startReceiptTask(receipt).subscribe(res => {
  //     let snackBarRef = this._snackBar.open("Receipt started", "OK");
  //     snackBarRef.afterDismissed().subscribe(() => {
  //       this.goBack();
  //     });
  //   });
  // }
}
