import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {Actor} from "../actor.interface";
import {ActorActions} from "../actor.action";
import {IdentityModuleState} from "../index";
import {SponsorActions} from "../sponsor.action";


@Component({
  selector: 'pams-sponsor-select',
  templateUrl: './sponsor-select.component.html',
})
export class SponsorSelectComponent implements OnInit {

  private SPONSORS = "identityModuleState.sponsors".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  programLevels$: Observable<Actor[]>;

  constructor(private store: Store<IdentityModuleState>,
              private actions: SponsorActions) {
    this.programLevels$ = this.store.select(...this.SPONSORS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findSponsors());
  }

  selectChangeEvent(event: Actor) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
