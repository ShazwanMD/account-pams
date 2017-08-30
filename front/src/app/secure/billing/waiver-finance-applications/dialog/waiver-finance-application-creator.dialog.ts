import { BillingModuleState } from './../../index';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";

@Component({
  selector: 'pams-waiver-finance-application',
  templateUrl: './waiver-finance-application-creator.dialog.html',
})

export class WaiverFinanceApplicationCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<WaiverFinanceApplicationCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      referenceNo: [''],
      sourceNo: [''],
      description: ['',Validators.required],
      reason: ['',Validators.required],
      waivedAmount: [0,Validators.required],
      account: [<Account>{}],
      academicSession: [<AcademicSession>{}],
    });
  }

  save(waiverFinanceApplicationCreator: WaiverFinanceApplication, isValid: boolean) {
    this.store.dispatch(this.actions.startWaiverFinanceApplicationTask(waiverFinanceApplicationCreator));
    this.dialog.close();
  }

}
