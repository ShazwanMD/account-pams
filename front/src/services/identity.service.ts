import {Injectable} from '@angular/core';
import {Response, Http} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from "rxjs";
import {Actor} from "../app/identity/actor.interface";
import {environment} from "../environments/environment";
import {Sponsor} from "../app/identity/sponsor.interface";
import {Student} from "../app/identity/student.interface";

@Injectable()
export class IdentityService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  findActors(): Observable<Actor[]> {
    console.log("findActors");
    return this.http.get(environment.endpoint + '/api/identity/actors')
      .map((res: Response) => <Actor[]>res.json());
  }

  findActorByIdentityNo(identityNo: string): Observable<Actor> {
    console.log("findActorByIdenittyNo");
    return this.http.get(environment.endpoint + '/api/identity/actors/' + identityNo)
      .map((res: Response) => <Actor>res.json());
  }

  findStudents(): Observable<Student[]> {
    console.log("findStudents");
    return this.http.get(environment.endpoint + '/api/identity/students')
      .map((res: Response) => <Student[]>res.json());
  }

  findStudentByIdentityNo(identityNo: string): Observable<Student> {
    console.log("findStudentByIdenittyNo");
    return this.http.get(environment.endpoint + '/api/identity/students/' + identityNo)
      .map((res: Response) => <Student>res.json());
  }

  findSponsors(): Observable<Sponsor[]> {
    console.log("findSponsors");
    return this.http.get(environment.endpoint + '/api/identity/sponsors')
      .map((res: Response) => <Sponsor[]>res.json());
  }

  findSponsorByIdentityNo(identityNo: string): Observable<Sponsor> {
    console.log("findSponsorByIdenittyNo");
    return this.http.get(environment.endpoint + '/api/identity/sponsors/' + identityNo)
      .map((res: Response) => <Sponsor>res.json());
  }
}
