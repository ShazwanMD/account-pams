import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, forwardRef, Provider} from '@angular/core';
import {FormControl, ControlValueAccessor, NG_VALUE_ACCESSOR} from '@angular/forms';
import {AccountService} from '../../../../../services/account.service';
import {Account} from '../../../../shared/model/account/account.interface';

export const CUSTOM_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => AccountComboBoxComponent),
  multi: true,
};

const noop = () => {
};

@Component({
  selector: 'pams-account-combo-box',
  templateUrl: './account-combo-box.component.html',
  providers: [CUSTOM_VALUE_ACCESSOR],
})
export class AccountComboBoxComponent implements ControlValueAccessor {

  private innerValue: any;
  private onTouchedCallback: () => void = noop;
  private onChangeCallback: (_: any) => void = noop;
  options: Account[] = <Account[]>[];

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.findAccounts().subscribe((accounts) => this.options = accounts);
  }

  onBlur() {
    this.onTouchedCallback();
  }

  //get accessor
  get value(): any {
    return this.innerValue;
  };

  //set accessor including call the onchange callback
  set value(v: any) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCallback(v);
    }
  }

  //From ControlValueAccessor interface
  writeValue(value: any) {
    if (value !== this.innerValue) {
      this.innerValue = value;
    }
  }

  //From ControlValueAccessor interface
  registerOnChange(fn: any) {
    this.onChangeCallback = fn;
  }

  //From ControlValueAccessor interface
  registerOnTouched(fn: any) {
    this.onTouchedCallback = fn;
  }
}
