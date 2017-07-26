import { StateCode } from '../../common/state-codes/state-code.interface';
import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {SetupModuleState} from '../index';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'pams-state-list-page',
  templateUrl: './state-code-list.page.html',
})
export class StateCodeListPage implements OnInit {

  private STATE_CODES = 'setupModuleState.stateCodes'.split('.');
  private stateCodes$: Observable<StateCode>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'descriptionEn', label: 'DescriptionEn'},
    {name: 'action', label: ''},
  ];

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>){
    this.stateCodes$ = this.store.select(...this.STATE_CODES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findStateCodes());
    this.store.dispatch(this.actions.changeTitle('State Codes'));
  }

  filter(): void {

  }
}
