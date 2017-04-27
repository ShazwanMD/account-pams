import { Injectable } from '@angular/core';
import { Response, Http } from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import {Observable} from "rxjs";
import {Actor} from "../app/identity/actor.interface";
import {environment} from "../environments/environment";

@Injectable()
export class IdentityService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }
    
    findActors(): Observable<Actor[]> {
    console.log("findActors");
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/actor/actors')
      .map((res: Response) => <Actor[]>res.json());
  }
}
