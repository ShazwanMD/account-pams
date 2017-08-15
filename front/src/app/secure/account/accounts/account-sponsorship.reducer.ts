import { AccountSponsorship } from './../../../shared/model/account/account-sponsorship.interface';
import {Action} from '@ngrx/store';
import {AccountActions} from './account.action';


export type AccountSponsorshipState = AccountSponsorship[];

const initialState: AccountSponsorshipState = <AccountSponsorship[]>[];

export function accountSponsorshipReducer(state = initialState, action: Action): AccountSponsorshipState {
  switch (action.type) {
    case AccountActions.FIND_SPONSORSHIPS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
