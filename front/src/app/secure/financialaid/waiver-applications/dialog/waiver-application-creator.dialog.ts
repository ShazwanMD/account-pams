import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationActions} from '../waiver-application.action';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-waiver-application',
  templateUrl: './waiver-application-creator.dialog.html',
})

export class WaiverApplicationCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: WaiverApplicationActions,
              private dialog: MdDialogRef<WaiverApplicationCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      referenceNo: [''],
      sourceNo: [''],
      description: ['',Validators.required],
      reason: ['',Validators.required],
      waivedAmount:[0,Validators.required],
      account: <Account>{},
      academicSession: <AcademicSession>{},
    });
  }

  save(waiverApplicationCreator: WaiverApplication, isValid: boolean) {
    this.store.dispatch(this.actions.startWaiverApplicationTask(waiverApplicationCreator));
    this.dialog.close();
    window.location.reload();
  }

}
