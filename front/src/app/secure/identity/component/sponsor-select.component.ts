import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {IdentityModuleState} from '../index';
import {SponsorActions} from '../sponsor.action';
import {Actor} from '../../../shared/model/identity/actor.interface';

@Component({
  selector: 'pams-sponsor-select',
  templateUrl: './sponsor-select.component.html',
  styleUrls: ['./sponsor-select.component.scss'],
})
export class SponsorSelectComponent implements OnInit {

  private SPONSORS = 'identityModuleState.sponsors'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  sponsors$: Observable<Actor[]>;

  constructor(private store: Store<IdentityModuleState>,
              private actions: SponsorActions) {
    this.sponsors$ = this.store.select(...this.SPONSORS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findSponsors());
  }

  selectChangeEvent(event: Actor) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

