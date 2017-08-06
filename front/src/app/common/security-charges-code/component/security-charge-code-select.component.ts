import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import { SecurityChargeCode } from "../../../shared/model/common/security-charge-code.interface";
import { SetupModuleState } from "../../../secure/setup/index";
import { SetupActions } from "../../../secure/setup/setup.action";
@Component({
  selector: 'pams-security-charge-code-select',
  templateUrl: './security-charge-code-select.component.html',
  styleUrls: ['./security-charge-code-select.scss'],
})
export class SecurityChargeCodeSelectComponent implements OnInit {
  private SECURITY_CHARGE_CODES = 'setupModuleState.securityChargeCode'.split('.');
  private securityChargeCodes$: Observable<SecurityChargeCode[]>;
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  constructor(private store: Store<SetupModuleState>,
              private actions: SetupActions) {
    this.securityChargeCodes$ = this.store.select(...this.SECURITY_CHARGE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findSecurityChargeCodes());
  }
  selectChangeEvent(event: SecurityChargeCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
