import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {ResidencyCode} from '../../../shared/model/common/residency-code.interface';

@Component({
  selector: 'pams-residency-code-select',
  templateUrl: './residency-code-select.component.html',
   styleUrls: ['./residency-code-select.scss'],

})
export class ResidencyCodeSelectComponent implements OnInit {

  private RESIDENCY_CODES = 'commonModuleState.residencyCodes'.split('.');
  private residencyCodes$: Observable<ResidencyCode[]>;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.residencyCodes$ = this.store.select(...this.RESIDENCY_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findResidencyCodes());
  }

  selectChangeEvent(event: ResidencyCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

