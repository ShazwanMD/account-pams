import {Injectable} from '@angular/core';
import {Headers, RequestOptionsArgs} from '@angular/http';
import {IHttpInterceptor} from '@covalent/http';

@Injectable()
export class RequestInterceptor implements IHttpInterceptor {

    onRequest(requestOptions: RequestOptionsArgs): RequestOptionsArgs {
        let currentUser: any = JSON.parse(localStorage.getItem('currentUser'));
        let token = currentUser && currentUser.token;

        let headers = requestOptions.headers;
        if (!headers)
          requestOptions.headers = new Headers();

        let isUpload = headers && headers.get('Content-Type') === '';

        if (isUpload) {
          requestOptions.headers.delete('Content-Type'); // Workaround for upload bug, see term.service.ts
        }
        else {
          requestOptions.headers.append('Content-Type', 'application/json');
        }

        if (token)
          requestOptions.headers.append('Authorization', 'Bearer ' + token);

        return requestOptions;
      }
}
