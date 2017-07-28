import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {SettlementActions} from '../settlement.action';
import {SettlementCreator} from '../../../../shared/model/financialaid/settlement-creator.interface';
import {CohortCode} from '../../../../shared/model/common/cohort-code.interface';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';

@Component({
  selector: 'pams-settlement-creator-by-cohort',
  templateUrl: './settlement-creator-by-cohort.dialog.html',
})

export class SettlementCreatorByCohortDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementCreatorByCohortDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<SettlementCreator>{
      sourceNo: '',
      description: '',
      issuedDate: undefined,
      cohortCode: <CohortCode>{},
      academicSession: <AcademicSession>{},
    });
  }

  save(settlementCreator: SettlementCreator, isValid: boolean) {
    this.store.dispatch(this.actions.initSettlementByCohortCode(settlementCreator, settlementCreator.cohortCode));
    this.dialog.close();
  }
}
