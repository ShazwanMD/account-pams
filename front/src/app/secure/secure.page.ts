import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';
import {AuthenticatedUser} from '../shared/model/identity/authenticated-user.interface';

@Component({
  selector: 'pams-secure',
  templateUrl: './secure.page.html',
})
export class SecurePage implements OnInit {

  private authenticatedUser: AuthenticatedUser;

  constructor(private router: Router,
              private authnService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.authenticatedUser = this.authnService.authenticatedUser;
  }

  logout(): void {
    this.authnService.logout();
    this.router.navigate(['/login']);
  }
}
