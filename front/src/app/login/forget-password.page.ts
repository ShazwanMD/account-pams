import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'pams-login-forgetpassword',
  templateUrl: './forget-password.page.html',
  styleUrls: ['./forget-password.page.scss'],
})
export class ForgetPasswordPage implements OnInit {

  // email: string;
  // password: string;

  private forgetPasswordForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    let emailPattern: string = '^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$';
    this.forgetPasswordForm = this.formBuilder.group({
      name: [''],
      email: ['', Validators.pattern(emailPattern)],
      identityNo: [''],
      password: [''],
    });
  }

  submit(forgetPasswordForm: any, isValid: boolean): void {
    console.log('submit email=: ', forgetPasswordForm.email);
  }
}
