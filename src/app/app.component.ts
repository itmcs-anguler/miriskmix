import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './_services';
import { User } from './_models';
import { isUndefined, isNullOrUndefined } from 'util';

@Component({
    selector: 'app', templateUrl: 'app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    currentUser: User;
    public href: string = "";
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        
        let mainUrl = window.location.href.split(window.location.host)[1];
        if (isNullOrUndefined(localStorage.getItem('currentUser')) && mainUrl == '/') {
            this.logout();
        } else if (!isNullOrUndefined(localStorage.getItem('currentUser')) && mainUrl == '/') {
            this.router.navigate(['/dashboard']);
        } else if (isNullOrUndefined(localStorage.getItem('currentUser'))){
            this.logout();
        }
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
}