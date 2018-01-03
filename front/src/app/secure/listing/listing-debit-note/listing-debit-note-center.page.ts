import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { BillingModuleState } from '../../billing/index';
import { Store } from '@ngrx/store';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReportActions } from '../../../shared/report/report.action';
import { DebitNoteActions } from '../../billing/debit-notes/debit-note.action';
import { DateRange } from '../../../shared/model/billing/date-range.interface';
import { DebitNote } from '../../../shared/model/billing/debit-note.interface';

@Component({
  selector: 'pams-listing-debit-note-center-page',
  templateUrl: './listing-debit-note-center.page.html',
})

export class ListingDebitNoteCenterPage implements OnInit {

  @Input() debitNote: DebitNote;
  private editForm: FormGroup;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private formBuilder: FormBuilder,
    private reportActions: ReportActions,
    private actions: DebitNoteActions) {
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<DateRange>{
      Start_date: undefined,
      End_date: undefined,
    });
  }

downloadReport(reportId: DebitNote): void {
let repParam = reportId+'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date;
console.log("Start Date" + this.editForm.value.Start_date +"&End_date="+ this.editForm.value.End_date);
this.store.dispatch(this.reportActions.downloadReport(repParam));
}
}
