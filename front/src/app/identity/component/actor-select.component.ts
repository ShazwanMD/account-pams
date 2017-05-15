import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {Actor} from "../actor.interface";
import {ActorActions} from "../actor.action";
import {IdentityModuleState} from "../index";


@Component({
  selector: 'pams-actor-select',
  templateUrl: './actor-select.component.html',
})
export class ActorSelectComponent implements OnInit {

  private ACTORS = "identityModuleState.actors".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  programLevels$: Observable<Actor[]>;
  private actors$: Observable<Actor[]>;
      
  constructor(private store: Store<IdentityModuleState>,
              private actions: ActorActions) {
    this.programLevels$ = this.store.select(...this.ACTORS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findActors());
  }

  selectChangeEvent(event: Actor) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

