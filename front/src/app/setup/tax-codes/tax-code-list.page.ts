import { ResidencyCode } from './../../common/residency-codes/residency-code.interface';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import { TaxCode} from "../../common/tax-codes/tax-code.interface";
import { TaxCodeEditorDialog } from "./dialog/tax-code-editor.dialog";



@Component({
  selector: 'pams-tax-list-page',
  templateUrl: './tax-code-list.page.html',
})
export class TaxCodeListPage implements OnInit {

  private TAX_CODES = "setupModuleState.taxCodes".split(".");
  
  private taxCodes$:Observable<TaxCode>;
  private creatorDialogRef: MdDialogRef<TaxCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'taxRate', label: 'Tax Rate'},
    {name: 'action', label: ''}
  ];

  constructor(private actions: SetupActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private store: Store<SetupModuleState>){
    this.taxCodes$ = this.store.select(...this.TAX_CODES);
  }
 
   ngOnInit(): void {
    this.store.dispatch(this.actions.findTaxCodes())
    this.store.dispatch(this.actions.changeTitle("Tax Codes"))
  }

   createDialog(): void {
     this.showDialog(null);
   }

   editDialog(code:TaxCode): void {
     this.showDialog(code);
   }
   

   delete(code: TaxCode): void {
     this.store.dispatch(this.actions.removeTaxCode(code))
   }

   filter(): void {
   }

   private showDialog(code:TaxCode): void {
     console.log("create");
     let config = new MdDialogConfig();
     config.viewContainerRef = this.vcf;
     config.role = 'dialog';
     config.width = '70%';
     config.height = '65%';
     config.position = {top: '0px'};
     this.creatorDialogRef = this.dialog.open(TaxCodeEditorDialog, config);
     if(code) this.creatorDialogRef.componentInstance.taxCode = code; // set
     this.creatorDialogRef.afterClosed().subscribe(res => {
       console.log("close dialog");
     });
   }

}
