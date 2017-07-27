import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'pams-forget-password-information-page',
  templateUrl: './forget-password-information.page.html',
})

export class ForgetPasswordInformationPage {


  private REGISTRATION: string[] = 'registrationModuleState.registration'.split('.');
  private forgetPasswordForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.forgetPasswordForm = this.formBuilder.group({
      email: '',
    });
  }

}
