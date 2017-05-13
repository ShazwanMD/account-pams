import {AfterViewInit, Component, OnInit} from "@angular/core";
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {BankCode} from "../../common/bank-codes/bank-code.interface";
import {Observable} from "rxjs/Observable";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'pams-bank-list-page',
  templateUrl: './bank-code-list.page.html',
})
export class BankCodeListPage implements OnInit {

  private BANK_CODES = "setupModuleState.bankCodes".split(".");
  private bankCodes$: Observable<BankCode>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'name', label: 'Name'},
    {name: 'action', label: ''}
  ];

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private title: Title) {
    this.bankCodes$ = this.store.select(...this.BANK_CODES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findBankCodes())
    this.store.dispatch(this.actions.changeTitle("Bank Codes"))
  }

  filter(filter:string):void {
    console.log("filter");
  }
}
