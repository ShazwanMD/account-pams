import {ChangeDetectionStrategy, Component, Input, OnChanges, OnInit, SimpleChange} from '@angular/core';
import {Observable} from "rxjs";
import {Account} from "../account.interface";
import {AccountActions} from "../account.action";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {AccountModuleState} from "../../index";


@Component({
  selector: 'pams-account-select',
  templateUrl: './account-select.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AccountSelectComponent implements OnInit, OnChanges {
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  private ACCOUNTS: string[] = "accountModuleState.accounts".split(".");
  private accounts$: Observable<Account[]>;
  private selected: Account;

  constructor(private store: Store<AccountModuleState>,
              private actions: AccountActions) {
    this.accounts$ = this.store.select(...this.ACCOUNTS);
  }

  ngOnInit() {
    console.log("ngOnInit");
    this.store.dispatch(this.actions.findAccounts());
    console.log("selected:" + this.selected.code);
  }

  ngOnChanges(changes: { [propertyName: string]: SimpleChange }) {
    console.log("ngOnChanges");
    if (changes['innerFormControl'] && this.innerFormControl.value) {
      this.selected = <Account>this.innerFormControl.value;
    }
  }

  selectChangeEvent(event: Account) {
    console.log("selectChangeEvent");
    this.innerFormControl.setValue(event, {emitEvent: false});
    this.selected = event;
  }
}

