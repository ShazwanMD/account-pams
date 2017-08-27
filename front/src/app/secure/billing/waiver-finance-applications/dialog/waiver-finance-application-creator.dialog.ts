import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";

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
              private store: Store<FinancialaidModuleState>,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<WaiverFinanceApplicationCreatorDialog>) {
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
    this.store.dispatch(this.actions.startWaiverFinanceApplicationTask(waiverApplicationCreator));
    this.dialog.close();
  }

}
