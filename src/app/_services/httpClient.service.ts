import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { environment } from '@environments/environment';
import { CommonUtility } from './common.service';

@Injectable({ providedIn: 'root' })
export class HttpClientService {
    static self: HttpClientService;
    commonPath = '/mirisk/';
    mainformData: FormData = new FormData();
    client: any = 'access denied';
    
    constructor(private http: HttpClient, private commonUtility: CommonUtility) {
        HttpClientService.self = this;
        this.getMainClientIpAddress();
    }
    
    getMainClientIpAddress() {
        $.getJSON("https://jsonip.com?callback=?", function (data) {
            HttpClientService.self.client = data.ip;
        });
    }

    get(path) {
        var maindata = this.http.get<any[]>(`${environment.apiUrl}` + this.commonPath + path);        
       /* var subscription = maindata.subscribe(
            data => {
                console.log(data);
            }
        );   */    
        return maindata;
    }

    post(model, path) {
        return this.http.post(`${environment.apiUrl}` + this.commonPath + path, model);
    }

    put(model, path) {
        return this.http.put(`${environment.apiUrl}` + this.commonPath + path, model);
    }

    delete(path: string) {
        let json = this.makeExtraFieldsDelete(path);
        const options = {
            headers: new HttpHeaders({'Content-Type': 'application/json',}),
            body: json,
        };
        return this.http.delete(`${environment.apiUrl}` + this.commonPath + path, options);
    }
    makeExtraFieldsDelete(path) {
        let json = {};
        json['actionType'] = 'delete'.toUpperCase();
        json['browser'] = this.commonUtility.getBowserName();
        json['moduleName'] = path.split('/')[path.split('/').length-2].toUpperCase();
        json['ipAddress'] = this.client;
        return json;
    }

    saveOrUpdate(model, path, flag) {
        this.removeExtraKeyValues(model);
        this.makeExtraFields(model, path, flag);
        if (flag == 'save') {
            return this.http.post(`${environment.apiUrl}` + this.commonPath + path, model);
        } else if (flag == 'update') {
            return this.http.put(`${environment.apiUrl}` + this.commonPath + path, model);
        }
    }
    makeExtraFields(model, path, flag) {
        let json = {};
        json['actionType'] = flag.toUpperCase();
        json['browser'] = this.commonUtility.getBowserName();
        json['moduleName'] = path.toUpperCase();
        json['ipAddress'] = this.client;
        model['auditlogs'] = json;
    }

    removeExtraKeyValues(json){
        for (let key in json) { 
            if(typeof json[key] == 'object'){
                let tempjson = json[key];
                if(tempjson.hasOwnProperty('description'))
                    delete tempjson['description'];
            }
        }
    }

    saveArray(model, path) {
        return this.http.post(`${environment.apiUrl}/mirisk/${path}`, model);
    }

    updateArray(model, path) {
        return this.http.put(`${environment.apiUrl}/mirisk/${path}`, model);
    }

    uploadDocument(model, path) {
        return this.http.post(`${environment.apiUrl}/${path}`, model);
    }

    downloadDocument(path) {
        let headers = new HttpHeaders();
        headers = headers.append('Accept', 'charset=utf-8');        
        return this.http.get(`${path}`,{
            headers: headers,
            observe: 'response',
            responseType: 'blob'
        });
    }

    cheackCanSavePermission(flag) {
        let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
        let mainpermissionJson = {};
        userPermission.forEach(element => {
            if (element.pageName == flag) {
                mainpermissionJson = element;
            }
        });
        return mainpermissionJson['canSave'];
    }
}