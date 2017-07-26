import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {ActorActions} from '../actor.action';
import {IdentityModuleState} from '../index';
import {Actor} from '../../shared/model/identity/actor.interface';

@Component({
  selector: 'pams-actor-select',
  templateUrl: './actor-select.component.html',
})
export class ActorSelectComponent implements OnInit {

  private ACTORS = 'identityModuleState.actors'.split('.');
  private programLevels$: Observable<Actor[]>;
  private actors$: Observable<Actor[]>;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<IdentityModuleState>,
              private actions: ActorActions) {
    this.actors$ = this.store.select(...this.ACTORS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findActors());
  }

  selectChangeEvent(event: Actor) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

