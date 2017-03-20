import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';

@Injectable()
export class IdentityService {

  constructor(private _http: HttpInterceptorService) {
  }
}
