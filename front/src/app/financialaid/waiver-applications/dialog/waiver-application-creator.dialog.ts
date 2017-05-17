import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {FinancialaidModuleState} from "../../index";
import {WaiverApplicationActions} from "../waiver-application.action";
import {AcademicSession} from "../../../account/academic-sessions/academic-session.interface";
import {WaiverApplication} from "../waiver-application.interface";
import {Account} from "../../../account/accounts/account.interface";

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
    this.createForm = this.formBuilder.group(<WaiverApplication>{
      referenceNo:'',
      sourceNo: '',
      description: '',
      reason: '',
      waivedAmount: 0,
      account:<Account>{},
      academicSession: <AcademicSession>{},
    });
  }

  save(waiverApplicationCreator: WaiverApplication, isValid: boolean) {
    this.store.dispatch(this.actions.startWaiverApplicationTask(waiverApplicationCreator));
    this.dialog.close();
  }

}
