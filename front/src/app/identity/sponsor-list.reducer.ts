import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {SponsorActions} from './sponsor.action';
import {Sponsor} from '../shared/model/identity/sponsor.interface';

export type SponsorListState = Sponsor[];

const initialState: SponsorListState = <Sponsor[]>[];

export function sponsorListReducer(state = initialState, action: Action): SponsorListState {
  switch (action.type) {
    case SponsorActions.FIND_SPONSORS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
