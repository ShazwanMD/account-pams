import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Store } from '@ngrx/store';
import { BillingModuleState } from '../../billing/index';
import { ReportActions } from '../../../shared/report/report.action';
import { CreditNoteActions } from '../../billing/credit-notes/credit-note.action';
import { DateRange } from '../../../shared/model/billing/date-range.interface';
import { CreditNote } from '../../../shared/model/billing/credit-note.interface';

@Component({
  selector: 'pams-listing-credit-note-center-page',
  templateUrl: './listing-credit-note-center.page.html',
})

export class ListingCreditNoteCenterPage implements OnInit {

  @Input() creditNote: CreditNote;
  private editForm: FormGroup;

    constructor(private router: Router,
      private route: ActivatedRoute,
      private store: Store<BillingModuleState>,
      private formBuilder: FormBuilder,
      private reportActions: ReportActions,
      private actions: CreditNoteActions) {
    }
  
    ngOnInit(): void {
      this.editForm = this.formBuilder.group(<DateRange>{
        Start_date: undefined,
        End_date: undefined,
      });
    }
  
  downloadReportListing(reportId: CreditNote,parameterReport:DateRange): void {
  let repParam = reportId+'&Start_date=' +  this.editForm.value.Start_date;
  console.log("Start Date" + this.editForm.value.Start_date);
  let repParam2 = reportId+'&End_date='+ this.editForm.value.End_date;
  console.log("End Date" + this.editForm.value.End_date);
  this.store.dispatch(this.reportActions.downloadReportListing(repParam, repParam2));
  }
  }
  