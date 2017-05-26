import { ResidencyCode } from './../../common/residency-codes/residency-code.interface';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";



@Component({
  selector: 'pams-residency-list-page',
  templateUrl: './residency-code-list.page.html',
})
export class ResidencyCodeListPage implements OnInit {

  private RESIDENCY_CODES = "setupModuleState.residencyCodes".split(".");
  
  private residencyCodes$:Observable<ResidencyCode>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''}
  ];

  constructor(private actions: SetupActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private store: Store<SetupModuleState>){
    this.residencyCodes$ = this.store.select(...this.RESIDENCY_CODES);
  }
 
   ngOnInit(): void {
    this.store.dispatch(this.actions.findResidencyCodes())
    this.store.dispatch(this.actions.changeTitle("Residency Codes"))
  }

}
