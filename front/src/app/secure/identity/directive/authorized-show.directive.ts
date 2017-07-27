import {Directive, ElementRef, Input, OnInit, Renderer} from '@angular/core';
import {AuthorizationService} from '../../../../services/authorization.service';

@Directive({selector: '[authorizedShow]'})
export class AuthorizedShowDirective implements OnInit {

  @Input() authorizedShow: string;

  constructor(private el: ElementRef, private renderer: Renderer, private authzService: AuthorizationService) {
  }

  ngOnInit(): void {
    console.log('checking for permission: ' + this.authorizedShow);
    if (!this.authzService.can(this.authorizedShow)) {
      this.renderer.setElementStyle(this.el.nativeElement, 'display', 'none');
    }
  }
}
