import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {TaxCode} from '../../../shared/model/common/tax-code.interface';
import { SetupActions } from '../../../secure/setup/setup.action';
import { SetupModuleState } from '../../../secure/setup/index';

@Component({
  selector: 'pams-tax-code-select',
  templateUrl: './tax-code-select.component.html',
  styleUrls: ['./tax-code-select.scss'],
})
export class TaxCodeSelectComponent implements OnInit {

  private TAX_CODES_BY_ACTIVE = 'setupModuleState.taxCodesByActive'.split('.');
  private taxCodesByActive$: Observable<TaxCode[]>;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<SetupModuleState>,
    private actions: SetupActions) {
  this.taxCodesByActive$ = this.store.select(...this.TAX_CODES_BY_ACTIVE);
}


  ngOnInit() {
    this.store.dispatch(this.actions.findTaxCodesByActive());
  }

  selectChangeEvent(event: TaxCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

