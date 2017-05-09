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

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private actions: ChargeCodeActions,
              private dialog: MdDialogRef<ChargeCodeCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<ChargeCode>{
      id: null,
      code: '',
      description:'',
      priority:0,
    });
  }

  save(chargeCode: ChargeCode, isValid: boolean): void{
    console.log("saving charge code");
    this.store.dispatch(this.actions.saveChargeCode(chargeCode));
    this.dialog.close();
  }
}
