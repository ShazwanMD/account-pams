import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {SettlementActions} from '../settlement.action';
import {FinancialaidModuleState} from '../../index';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';
import {UploadHelper} from '../dialog/UploadHelper.interface';
import { SponsorshipType } from "../../../../shared/model/financialaid/sponsorship-type.enum";

@Component({
  selector: 'pams-settlement-file-uploader',
  templateUrl: './settlement-file-uploader.dialog.html',
})

export class SettlementFileUploaderDialog implements OnInit {

  private editForm: FormGroup;
  private _settlement: Settlement;
  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementFileUploaderDialog>) {
  }

  set settlement(settlement: Settlement) {
      
    console.log("settlement upload dialog:"+settlement.referenceNo);  
    this._settlement = settlement;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group(<UploadHelper>{
          sponsorshipType: SponsorshipType.PTPTN,
        })
  }

  upload(file: File): void {
      console.log("file to be uploaded:"+file.name);  
      console.log("_settlement to be uploaded:"+this._settlement.referenceNo);
      this.store.dispatch(this.actions.uploadSettlement(this._settlement,file));
  }
}
