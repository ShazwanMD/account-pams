import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {SettlementActions} from '../settlement.action';
import {SettlementCreator} from '../../../../shared/model/financialaid/settlement-creator.interface';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Sponsor} from '../../../../shared/model/identity/sponsor.interface';

@Component({
  selector: 'pams-settlement-creator-by-sponsor',
  templateUrl: './settlement-creator-by-sponsor.dialog.html',
})

export class SettlementCreatorBySponsorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementCreatorBySponsorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<SettlementCreator>{
      sourceNo: '',
      description: '',
      issuedDate: undefined,
      sponsor: <Sponsor>{},
      academicSession: <AcademicSession>{},
    });
  }

  save(settlementCreator: SettlementCreator, isValid: boolean) {
    this.store.dispatch(this.actions.initSettlementBySponsor(settlementCreator, settlementCreator.sponsor));
    this.dialog.close();
  }
}
