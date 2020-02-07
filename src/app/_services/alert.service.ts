import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { AuthenticationService } from './authentication.service';
/* https://sweetalert2.github.io/ */
@Injectable({ providedIn: 'root' })
export class AlertService {
    private subject = new Subject<any>();
    private keepAfterNavigationChange = false;

    constructor(private router: Router, private authService : AuthenticationService) {
        // clear alert message on route change
        router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                if (this.keepAfterNavigationChange) {
                    // only keep for a single location change
                    this.keepAfterNavigationChange = false;
                } else {
                    // clear alert
                    this.subject.next();
                }
            }
        });
    }

    success(message: string, keepAfterNavigationChange = false) {
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({ type: 'success', text: message });
    }

    successSaveUpdate(message: string) {
        let mainMessage = 'Your work has been saved';
        if (message != '' && message != null) {
            mainMessage = message;
        }        
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: mainMessage,
            showConfirmButton: false,
            timer: 1500
        })
    }

    deleteSuccessMsg() {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'Your record has been deleted.',
            showConfirmButton: false,
            timer: 1500
        })
        
       /* Swal.fire(
            'Deleted!',
            'Your record has been deleted.',
            'success'
        )*/
    }

    customErrorMessage(message) {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 1700
        })

        /* Swal.fire(
             'Deleted!',
             'Your record has been deleted.',
             'success'
         )*/
    }

    logoutMsg() {
        Swal.fire({
            position: 'top',
            icon: 'error',
            title: 'Your Session has expired.',
            showConfirmButton: false,
            timer: 1500
        })

        /* Swal.fire(
             'Deleted!',
             'Your record has been deleted.',
             'success'
         )*/
    }

    error(message: string, keepAfterNavigationChange = false) {
        // if (message == 'OK') {
        //     this.logoutMsg();
        //     this.authService.logout();
        //     this.router.navigate(['/login']);
        // }
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({ type: 'error', text: message });
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}