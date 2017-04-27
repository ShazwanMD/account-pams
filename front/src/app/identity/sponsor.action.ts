import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class SponsorActions {

  static FIND_SPONSORS = '[Sponsor] Find Sponsors';

  findSponsors(): Action {
    return {
      type: SponsorActions.FIND_SPONSORS
    };
  }

  static FIND_SPONSORS_SUCCESS = '[Sponsor] Find Sponsors Success';

  findSponsorsSuccess(sponsors): Action {
    console.log("findSponsorsSuccess");
    return {
      type: SponsorActions.FIND_SPONSORS_SUCCESS,
      payload: sponsors
    };
  }

  static FIND_SPONSOR = '[Sponsor] Find Sponsor';

  findSponsor(identityNo): Action {
    return {
      type: SponsorActions.FIND_SPONSOR,
      payload: identityNo
    };
  }

  static FIND_SPONSOR_SUCCESS = '[Sponsor] Find Sponsor Success';

  findSponsorSuccess(sponsor): Action {
    console.log("findSponsorSuccess");
    return {
      type: SponsorActions.FIND_SPONSOR_SUCCESS,
      payload: sponsor
    };
  }


  static UPDATE_SPONSOR = '[Sponsor] Update Sponsor';

  updateSponsor(account): Action {
    return {
      type: SponsorActions.UPDATE_SPONSOR,
      payload: account
    };
  }

  static UPDATE_SPONSOR_SUCCESS = '[Sponsor] Update Sponsor Success';

  updateSponsorSuccess(account): Action {
    return {
      type: SponsorActions.UPDATE_SPONSOR_SUCCESS,
      payload: account
    };
  }
}
