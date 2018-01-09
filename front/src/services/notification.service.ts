import {Injectable} from '@angular/core';
import {MdSnackBar, MdSnackBarConfig} from '@angular/material';
import {ApplicationError} from '../app/core/application-error.interface';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class NotificationService {

  constructor(private snackBar: MdSnackBar) {
  }

  showInfo(message: string) {
    let config = new MdSnackBarConfig();
    config.duration = 5000;
    this.snackBar.open(message, undefined, config);
  }

  showError(error: ApplicationError): Observable<any> {
    let config: MdSnackBarConfig = new MdSnackBarConfig();
    config.duration = 5000;
    this.snackBar.open(error.error, 'OK', config);
    return Observable.empty();
  }
}
