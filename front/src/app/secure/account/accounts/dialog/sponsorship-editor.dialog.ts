import { Actor } from './../../../../shared/model/identity/actor.interface';
import { AccountSponsorship } from './../../../../shared/model/account/account-sponsorship.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountChargeType} from '../../../../shared/model/account/account-charge-type.enum';
import { ActorType } from "../../../../shared/model/identity/actor-type.enum";
import { Sponsor } from "../../../../shared/model/identity/sponsor.interface";

@Component({
  selector: 'pams-sponsorship-editor',
  templateUrl: './sponsorship-editor.dialog.html',
})
export class SponsorshipEditorDialog implements OnInit {
  private _account: Account;
  private _sponsorship: AccountSponsorship;
  
  private editForm: FormGroup;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private actions: AccountActions,
              private dialog: MdDialogRef<SponsorshipEditorDialog>) {
  }

  set sponsorship(value: AccountSponsorship) {
    this._sponsorship = value;
    this.edit = true;
  }

  set account(value: Account) {
    this._account = value;
  }

  ngOnInit(): void {

    this.editForm = this.formBuilder.group({
      id: undefined,
      referenceNo: '',
      // sourceNo: '',
      // description: '',
      accountNo: '',
      amount: 0,
      startDate: undefined,
      endDate:undefined,
      sponsor: <Sponsor>{},
    });

    if (this.edit) {
      this.editForm.patchValue(this._sponsorship);
    }
  }

  submit(sponsorship: AccountSponsorship, isValid: boolean) {
    //if (this.edit) this.store.dispatch(this.actions.updateSponsorship(this._account, sponsorship));
    //else  
    
    this.store.dispatch(this.actions.addSponsorship(this._account, this.editForm.get( 'sponsor' ).value , sponsorship));
    this.dialog.close();
  }
}
