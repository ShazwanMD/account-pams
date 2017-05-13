import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {CommonActions} from "../../common.action";
import { CommonModuleState } from "../../index";
import { BankCode } from "../bank-code.interface";

@Component({
  selector: 'pams-bank-code-select',
  templateUrl: './bank-code-select.component.html',
})
export class BankCodeSelectComponent implements OnInit {

  private BANK_CODE = "commonModuleState.bankCodes".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  bankCodes$: Observable<BankCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.bankCodes$ = this.store.select(...this.BANK_CODE);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findBankCodes());
  }

  selectChangeEvent(event: BankCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

