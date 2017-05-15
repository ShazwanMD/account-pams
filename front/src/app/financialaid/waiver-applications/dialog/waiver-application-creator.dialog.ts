import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {FinancialaidModuleState} from "../../index";
import {WaiverApplicationActions} from "../waiver-application.action";
import {WaiverApplicationCreator} from "../waiver-application-creator.interface";
import {AcademicSession} from "../../../account/academic-sessions/academic-session.interface";
import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";

@Component({
  selector: 'pams-waiver-application',
  templateUrl: './waiver-application-creator.dialog.html',
})

export class WaiverApplicationCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: WaiverApplicationActions,
              private dialog: MdDialogRef<WaiverApplicationCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<WaiverApplicationCreator>{
      sourceNo: '',
      description: '',
      reason: '',
      waivedAmount: 0,
      academicSession: <AcademicSession>{},
    });
  }

save(waiverApplicationCreator: WaiverApplicationCreator, isValid: boolean) {
    //this.store.dispatch(this.actions.initWaiverApplication(waiverApplicationCreator, waiverApplicationCreator.cohortCode));
    this.dialog.close();
  }

  
}
