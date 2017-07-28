import {Directive, ElementRef, OnInit, Renderer} from '@angular/core';
import {AuthenticationService} from '../../../../services/authentication.service';

@Directive({selector: '[notAuthenticatedShow]'})
export class NotAuthenticatedShowDirective implements OnInit {

  constructor(private el: ElementRef, private renderer: Renderer, private authnService: AuthenticationService) {
  }

  ngOnInit(): void {
    if (this.authnService.isLoggedIn()) {
      this.renderer.setElementStyle(this.el.nativeElement, 'display', 'none');
    }
  }
}
