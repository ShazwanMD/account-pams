import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {SettlementActions} from '../settlement.action';
import {FinancialaidModuleState} from '../../index';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';

@Component({
  selector: 'pams-settlement-file-uploader',
  templateUrl: './settlement-file-uploader.dialog.html',
})

export class SettlementFileUploaderDialog implements OnInit {

  private editForm: FormGroup;
  private _settlement: Settlement;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementFileUploaderDialog>) {
  }

  set settlement(settlement: Settlement) {
    this._settlement = settlement;
  }

  ngOnInit(): void {
    // todo
  }

  upload(file: File): void {
    // todo
  }
}
