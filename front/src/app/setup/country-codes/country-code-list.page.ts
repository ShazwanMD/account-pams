import { CountryCode } from './../../common/country-codes/country-code.interface';

import {Component, OnInit} from "@angular/core";
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";



@Component({
  selector: 'pams-country-list-page',
  templateUrl: './country-code-list.page.html',
})
export class CountryCodeListPage implements OnInit {

  private COUNTRY_CODES = "setupModuleState.countryCodes".split(".");
  private countryCodes$:Observable<CountryCode>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'descriptionEn', label: 'DescriptionEn'},
    {name: 'action', label: ''}
  ];

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>){
    this.countryCodes$ = this.store.select(...this.COUNTRY_CODES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findCountryCodes())
    this.store.dispatch(this.actions.changeTitle("Country Codes"))
  }

}
