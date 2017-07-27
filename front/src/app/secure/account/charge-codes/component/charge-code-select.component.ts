import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AccountModuleState} from '../../index';
import {ChargeCodeActions} from '../charge-code.action';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';

@Component({
  selector: 'pams-charge-code-select',
  templateUrl: './charge-code-select.component.html',
  styleUrls: ['./charge-code-select.scss'],
})
export class ChargeCodeSelectComponent implements OnInit {

  private CHARGE_CODES: string[] = 'accountModuleState.chargeCodes'.split('.');
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

