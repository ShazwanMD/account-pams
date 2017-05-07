import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {ChargeCode} from "../charge-code.interface";
import {Actor} from "../../../identity/actor.interface";
import {ChargeCodeActions} from "../charge-code.action";
import {AccountModuleState} from "../../index";
import {IdentityService} from "../../../../services/identity.service";


@Component({
  selector: 'pams-charge-code-creator',
  templateUrl: './charge-code-creator.dialog.html',
})

export class ChargeCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private store: Store<AccountModuleState>,
              private actions: ChargeCodeActions,
              private dialog: MdDialogRef<ChargeCodeCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<ChargeCode>{
      id: null,
      code: '',
      description:'',
    });
  }

  save(chargeCode: ChargeCode, isValid: boolean) {
    console.log("code: " + chargeCode.code);
    this.store.dispatch(this.actions.saveChargeCode(chargeCode));
    this.dialog.close();
  }
}
