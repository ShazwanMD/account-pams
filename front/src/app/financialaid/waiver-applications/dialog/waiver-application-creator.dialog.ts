import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationActions} from '../waiver-application.action';
import {WaiverApplication} from '../../../shared/model/financialaid/waiver-application.interface';
import {AcademicSession} from '../../../shared/model/account/academic-session.interface';
import {Account} from '../../../shared/model/account/account.interface';

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
    this.createForm = this.formBuilder.group(<WaiverApplication>{
      referenceNo: '',
      sourceNo: '',
      description: '',
      reason: '',
      waivedAmount: 0,
      account: <Account>{},
      academicSession: <AcademicSession>{},
    });
  }

  save(waiverApplicationCreator: WaiverApplication, isValid: boolean) {
    this.store.dispatch(this.actions.startWaiverApplicationTask(waiverApplicationCreator));
    this.dialog.close();
  }

}
