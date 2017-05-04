import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';


@Component({
    selector: 'pams-waiver-application-center',
    templateUrl: './waiver-application-center.page.html',
})

export class WaiverApplicationCenterPage{

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }

}