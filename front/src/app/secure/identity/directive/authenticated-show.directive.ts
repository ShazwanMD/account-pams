import {Directive, ElementRef, OnInit, Renderer} from '@angular/core';
import {AuthenticationService} from '../../../../services/authentication.service';

@Directive({selector: '[authenticatedShow]'})
export class AuthenticatedShowDirective implements OnInit {

  constructor(private el: ElementRef, private renderer: Renderer, private authnService: AuthenticationService) {
  }

  ngOnInit(): void {
    if (!this.authnService.isLoggedIn()) {
      this.renderer.setElementStyle(this.el.nativeElement, 'display', 'none');
    }
  }
}
