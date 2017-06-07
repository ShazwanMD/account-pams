import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {CommonActions} from "../../common.action";
import {CommonModuleState} from "../../index";
import { TaxCode } from "../tax-code.interface";


@Component({
  selector: 'pams-tax-code-select',
  templateUrl: './tax-code-select.component.html',
})
export class TaxCodeSelectComponent implements OnInit {

  private TAX_CODES = "commonModuleState.taxCodes".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  taxCodes$: Observable<TaxCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.taxCodes$ = this.store.select(...this.TAX_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findTaxCodes());
  }

  selectChangeEvent(event: TaxCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

