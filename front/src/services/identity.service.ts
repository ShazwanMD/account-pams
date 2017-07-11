import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {Actor} from '../app/identity/actor.interface';
import {environment} from '../environments/environment';
import {Sponsor} from '../app/identity/sponsor.interface';
import {Student} from '../app/identity/student.interface';

@Injectable()
export class IdentityService {

  private IDENTITY_API: string = environment.endpoint + '/api/identity';

  constructor(private _http: HttpInterceptorService) {
  }

  findActors(): Observable<Actor[]> {
    console.log('findActors');
    return this._http.get(this.IDENTITY_API + '/actors')
      .map((res: Response) => <Actor[]>res.json());
  }

  findActorByIdentityNo(identityNo: string): Observable<Actor> {
    console.log('findActorByIdenittyNo');
    return this._http.get(this.IDENTITY_API + '/actors/' + identityNo)
      .map((res: Response) => <Actor>res.json());
  }

  findStudents(): Observable<Student[]> {
    console.log('findStudents');
    return this._http.get(this.IDENTITY_API + '/students')
      .map((res: Response) => <Student[]>res.json());
  }

  findStudentByIdentityNo(identityNo: string): Observable<Student> {
    console.log('findStudentByIdenittyNo');
    return this._http.get(this.IDENTITY_API + '/students/' + identityNo)
      .map((res: Response) => <Student>res.json());
  }

  findSponsors(): Observable<Sponsor[]> {
    console.log('findSponsors');
    return this._http.get(this.IDENTITY_API + '/sponsors')
      .map((res: Response) => <Sponsor[]>res.json());
  }

  findSponsorByIdentityNo(identityNo: string): Observable<Sponsor> {
    console.log('findSponsorByIdenittyNo');
    return this._http.get(this.IDENTITY_API + '/sponsors/' + identityNo)
      .map((res: Response) => <Sponsor>res.json());
  }
}
