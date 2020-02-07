import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    get(path) {
        return this.http.get<any[]>(`${environment.apiUrl}` + path);
    }

    post(model, path) {
        return this.http.post(`${environment.apiUrl}` + path, model);
    }

    put(model, path) {
        return this.http.put(`${environment.apiUrl}` + path, model);
    }

    delete(path) {
        return this.http.delete(`${environment.apiUrl}` + path);
    }




    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }

    getById(id: number) {
        return this.http.get(`${environment.apiUrl}/users/${id}`);
    }

    register(user: User) {
        return this.http.post(`${environment.apiUrl}/users/register`, user);
    }
    model(model, path) {
        return this.http.post(`${environment.apiUrl}` + path, model);
    }

}