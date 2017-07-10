import {Injectable} from '@angular/core';
import {HttpInterceptorService} from '@covalent/http';
import {environment} from '../environments/environment';

@Injectable()
export class SystemService {

  private SYSTEM_API: string = environment.endpoint + '/api/system';

  constructor(private _http: HttpInterceptorService) {
  }
}
