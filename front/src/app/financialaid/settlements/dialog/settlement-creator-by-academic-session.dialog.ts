import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {FinancialaidModuleState} from "../../index";
import {SettlementActions} from "../settlement.action";
import {AcademicSession} from "../../../account/academic-sessions/academic-session.interface";
import {SettlementCreator} from "../settlement-creator.interface";


@Component({
  selector: 'pams-settlement-creator-by-academic-session',
  templateUrl: './settlement-creator-by-academic-session.dialog.html',
})

export class SettlementCreatorByAcademicSessionDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementCreatorByAcademicSessionDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<SettlementCreator>{
      sourceNo:'',
      description: '',
      academicSession:<AcademicSession>{},
    });
  }

  save(settlementCreator: SettlementCreator, isValid: boolean) {
    this.store.dispatch(this.actions.initSettlementByAcademicSession(settlementCreator));
    this.dialog.close();
  }
}
