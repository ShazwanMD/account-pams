import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {GraduateCentre} from "../graduate-centre.interface";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {CommonActions} from "../../common.action";
import {CommonModuleState} from "../../index";


@Component({
  selector: 'pams-graduate-centre-select',
  templateUrl: './graduate-centre-select.component.html',
})
export class GraduateCentreSelectComponent implements OnInit {

  private GRADUATE_CENTRES = "commonModuleState.graduateCentres".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  graduateCentres$: Observable<GraduateCentre[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.graduateCentres$ = this.store.select(...this.GRADUATE_CENTRES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findGraduateCentres());
  }

  selectChangeEvent(event: GraduateCentre) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

