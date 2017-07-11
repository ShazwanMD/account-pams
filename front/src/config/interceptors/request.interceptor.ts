import {Injectable} from '@angular/core';
import {Headers, RequestOptionsArgs} from '@angular/http';
import {IHttpInterceptor} from '@covalent/http';

@Injectable()
export class RequestInterceptor implements IHttpInterceptor {

  onRequest(requestOptions: RequestOptionsArgs): RequestOptionsArgs {
    let currentUser: any = JSON.parse(localStorage.getItem('currentUser'));
    let token = currentUser && currentUser.token;

    requestOptions.headers = new Headers({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    });

    return requestOptions;
  }
}
