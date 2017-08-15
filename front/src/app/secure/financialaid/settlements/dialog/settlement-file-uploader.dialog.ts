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

  ngOnInit(): void {
      this.createForm = this.formBuilder.group(<UploadHelper>{
          sponsorshipType: SponsorshipType.PTPTN,
        })
  }
  
  upload(uploadHelper: UploadHelper, file: File) {
      this.store.dispatch(this.actions.uploadSettlement(uploadHelper.sponsorshipType, file));
      this.dialog.close();
    }
}
