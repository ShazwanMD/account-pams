import {CountryCode} from '../../../../shared/model/common/country-code.interface';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-country-code-creator',
  templateUrl: './country-code-creator.dialog.html',
})

export class CountryCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CountryCodeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group(<CountryCode>{
      id: null,
      code: '',
      name: '',
      descriptionMs: '',
      descriptionEn: '',
      prefix: '',

    });
  }

  save(code: CountryCode, isValid: boolean) {
    this.store.dispatch(this.actions.saveCountryCode(code));
  }
}
