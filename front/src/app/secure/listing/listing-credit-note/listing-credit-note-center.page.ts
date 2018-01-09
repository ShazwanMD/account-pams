import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Store } from '@ngrx/store';
import { BillingModuleState } from '../../billing/index';
import { ReportActions } from '../../../shared/report/report.action';
import { CreditNoteActions } from '../../billing/credit-notes/credit-note.action';
import { DateRange } from '../../../shared/model/billing/date-range.interface';
import { CreditNote } from '../../../shared/model/billing/credit-note.interface';
import { ProgramLevel } from '../../../shared/model/common/program-level.interface';
import { ProgramCode } from '../../../shared/model/common/program-code.interface';

@Component({
  selector: 'pams-listing-credit-note-center-page',
  templateUrl: './listing-credit-note-center.page.html',
})

export class ListingCreditNoteCenterPage implements OnInit {

  @Input() creditNote: CreditNote;
  @Input() programCode: ProgramCode;
  @Input() programLevel: ProgramLevel;
  private editForm: FormGroup;

    constructor(private router: Router,
      private route: ActivatedRoute,
      private store: Store<BillingModuleState>,
      private formBuilder: FormBuilder,
      private reportActions: ReportActions,
      private actions: CreditNoteActions) {
    }
  
    ngOnInit(): void {
      this.editForm = this.formBuilder.group({
        Start_date: undefined,
        End_date: undefined,
        programLevel: [<ProgramLevel>{}],
        programCode: [<ProgramCode>{}],
      });
    }

    downloadReport(editForm: FormGroup): void {
      let repParam =editForm +'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date
      +'&Program_code='+ this.editForm.value.programCode.description +'&Study_level='+ this.editForm.value.programLevel.code;
      // + '&Status='+ this.editForm.value.debitNoteStatusType;
      this.store.dispatch(this.reportActions.downloadReport(repParam));
      }  
  }
  