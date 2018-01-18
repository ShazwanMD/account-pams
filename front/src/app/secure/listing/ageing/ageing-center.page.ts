import { ProgramCode } from './../../../shared/model/common/program-code.interface';
import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { BillingModuleState } from '../../billing/index';
import { Store } from '@ngrx/store';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReportActions } from '../../../shared/report/report.action';
import { DebitNoteActions } from '../../billing/debit-notes/debit-note.action';
import { DateRange } from '../../../shared/model/billing/date-range.interface';
import { DebitNote } from '../../../shared/model/billing/debit-note.interface';
import { ProgramLevel } from '../../../shared/model/common/program-level.interface';
import { DebitNoteStatusType } from '../../../shared/model/common/debit-note-status-type.enum';
import { InvoiceStatusType } from '../../../shared/model/common/invoice-status-type';

@Component({
  selector: 'pams-ageing-center-page',
  templateUrl: './ageing-center.page.html',
})
export class AgeingCenterPage implements OnInit {

  @Input() programCode: ProgramCode;
  @Input() programLevel: ProgramLevel;

  private editForm: FormGroup;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private formBuilder: FormBuilder,
    private reportActions: ReportActions,
    private actions: DebitNoteActions) {
  }
  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      Start_date: undefined,
      End_date: undefined,
      programLevel: [<ProgramLevel>{}],
      programCode: [<ProgramCode>{}],
      invoiceStatusType: InvoiceStatusType,
    });
  }
downloadReport(editForm: FormGroup): void {
let repParam = editForm+'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date
+'&Program_code='+ this.editForm.value.programCode.description +'&Study_level='+ this.editForm.value.programLevel.code;
this.store.dispatch(this.reportActions.downloadReport(repParam));
}
}