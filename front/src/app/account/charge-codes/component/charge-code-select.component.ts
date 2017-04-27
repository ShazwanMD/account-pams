import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {AccountModuleState} from "../../index";
import {ChargeCode} from "../charge-code.interface";
import {ChargeCodeActions} from "../charge-code.action";


@Component({
  selector: 'pams-charge-code-select',
  templateUrl: './charge-code-select.component.html',
})
export class ChargeCodeSelectComponent implements OnInit {

  private CHARGE_CODES = "accountModuleState.chargeCodes".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  chargeCodes$: Observable<ChargeCode[]>;

  constructor(private store: Store<AccountModuleState>,
              private actions: ChargeCodeActions) {
    this.chargeCodes$ = this.store.select(...this.CHARGE_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findChargeCodes());
  }

  selectChangeEvent(event: ChargeCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

