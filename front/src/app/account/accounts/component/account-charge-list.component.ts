import {Component, Input, EventEmitter, OnInit, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AccountCharge} from "../account-charge.interface";
import {AdmissionChargeDialog} from "../dialog/admission-charge.dialog";
import {AdmissionChargeEditorDialog} from "../dialog/admission-charge-editor.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {AccountActions} from "../account.action";
import {AccountModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {Account} from "../account.interface";
import {AdmissionCharge} from "../admission-charge.interface";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent {
  @Input() account: Account;
  @Input() charges: AccountCharge[];

  private ADMISSION_CHARGES: string[] = "accountModuleState.admissionCharge".split(".");
  admissionCharges$: Observable<AdmissionCharge[]>;
  private creatorDialogRef: MdDialogRef<AdmissionChargeDialog>;
  private editorDialogRef: MdDialogRef<AdmissionChargeEditorDialog>;
  private selectedRows: AccountCharge[];
  private columns: any[] = [
    {name: 'sourceNo', label: 'Source No'},
    {name: 'referenceNo', label: 'Reference No'},
    {name: 'description', label: 'Description'},
    {name: 'chargeType', label: 'Type'},   
    {name: 'session.code', label: 'Session'},
    {name: 'amount', label: 'Amount'},
    {name: 'invoiced', label: 'Invoiced'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  this.admissionCharges$ = this.store.select(...this.ADMISSION_CHARGES);
  }

  ngOnInit(): void {
    this.selectedRows = this.charges.filter(value => value.selected);
  }

  /*edit(account: Account, accountCharge: AccountCharge): void {
    this.editAdmissionCharge(account, accountCharge);
  }*/

   edit(accountCharge: AdmissionCharge): void {
     this.showDialog(accountCharge);
  }

  delete(account: Account, accountCharge: AccountCharge): void {
    this.store.dispatch(this.actions.removeAdmissionCharge(account, accountCharge))
    this.selectedRows = [];
  }

  filter(): void {
  }

  create(): void {
     this.showDialog(null);
  }

  selectRow(accountCharge: AccountCharge): void {
  }

  selectAllRows(accountCharge: AccountCharge[]): void {
  }

 /* createAdmissionChargeDialog(account, accountCharge): void {
    console.log("show dialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '90%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(AdmissionChargeDialog, config);
    this.creatorDialogRef.componentInstance.account = this.account;
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }*/

/*  editAdmissionCharge(account, accountCharge): void {
    console.log("Edit");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '90%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(AdmissionChargeEditorDialog, config);
    if (account) this.editorDialogRef.componentInstance.admissionCharge= account; // set
    this.editorDialogRef.afterClosed().subscribe(res => {
      console.log("close editor dialog");
    });
  }

}*/

showDialog(admissionCharge:AdmissionCharge): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(AdmissionChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account
    if (admissionCharge) this.editorDialogRef.componentInstance.admissionCharge = admissionCharge;
    this.editorDialogRef.afterClosed().subscribe(res => {
        this.selectedRows = [];
    });
  }
}