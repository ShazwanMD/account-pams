import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {SettlementActions} from '../settlement.action';
import {SettlementCreator} from '../../../../shared/model/financialaid/settlement-creator.interface';
import {FacultyCode} from '../../../../shared/model/common/faculty-code.interface';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';

@Component({
  selector: 'pams-settlement-creator-by-faculty',
  templateUrl: './settlement-creator-by-faculty.dialog.html',
})

export class SettlementCreatorByFacultyDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementCreatorByFacultyDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<SettlementCreator>{
      sourceNo: '',
      description: '',
      issuedDate: undefined,
      facultyCode: <FacultyCode>{},
      academicSession: <AcademicSession>{},
    });
  }

  save(settlementCreator: SettlementCreator, isValid: boolean) {
    console.log('settlementCreator', settlementCreator);
    this.store.dispatch(this.actions.initSettlementByFacultyCode(settlementCreator, settlementCreator.facultyCode));
    this.dialog.close();
  }
}
