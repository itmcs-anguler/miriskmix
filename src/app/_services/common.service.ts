import { Injectable } from "@angular/core";
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';
import { isNullOrUndefined } from "util";
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

@Injectable({ providedIn: 'root' })
export class CommonUtility {

    printdownloadpdfflag: string = 'print';

    constructor() {
    }

    private checkForExportJsonFormat(json) {
        if (json.hasOwnProperty('id'))
            delete json['id'];
        let contentjson = {};
        for (let key in json) {
            var showKey = key;
            showKey = showKey.replace(/([a-z])([A-Z])/g, '$1 $2');
            showKey = showKey.replace(/([A-Z])([A-Z][a-z])/g, '$1 $2');
            showKey = showKey.charAt(0).toUpperCase() + showKey.slice(1);
            contentjson[showKey] = this.jsonValuecheck(json[key])
        } 
        return contentjson;
    }

    public exportExcel(json, excelFileName: string){
        let mainJsonArr = [];
        if(isNullOrUndefined(json[0])){          
            mainJsonArr[0] = this.checkForExportJsonFormat(json);
        } else {
            json.forEach(element => {
                mainJsonArr.push(this.checkForExportJsonFormat(element));
            });
            //mainJsonArr = json;
        }        
        this.exportAsExcelFile(mainJsonArr, excelFileName);
    }

    public exportAsExcelFile(json: any[], excelFileName: string): void { 
        const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };        
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array',});
        this.saveAsExcelFile(excelBuffer, excelFileName);
    }

    private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
        FileSaver.saveAs(data, fileName + '_Form_' + new  Date().getTime() + EXCEL_EXTENSION);
    }

    getBowserName() {
        
        const { detect } = require('detect-browser');
        const browser = detect();
        if (browser) {
            console.log(browser.name);
            console.log(browser.version);
            console.log(browser.os);
        }
        return browser.name;
    }
    genRandom() {
        let text = "";
        let possible = "0123456789";
        for (let i = 0; i < 4; i++) {
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        }
        const dt = new Date();
        return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + text;
    }

    checkVal(value) {
        if (value) {
            return value;
        } else {
            return '';
        }
    }

    fnDateFormatter(dt: string) {
        const dtUpdate = new Date(dt).toISOString();
        const formatedDate = dtUpdate.split('T');
        const part1 = formatedDate[0].split('-');
        const updated = (part1[2] + '-' + part1[1] + '-' + part1[0]).toString();
        return updated;
    }

    generatePdf(json, modulename, action) {
        let filename = modulename+ '_Form_' + new  Date().getTime();
        const documentDefinition = this.getDocumentDefinition(json, modulename);
        switch (action) {
            case 'open': pdfMake.createPdf(documentDefinition).open(filename);    
            break;
            case 'print': pdfMake.createPdf(documentDefinition).print(); 
            break;
            case 'download':     
            pdfMake.createPdf(documentDefinition).download(filename); 
            break;
            default: pdfMake.createPdf(documentDefinition).open(); 
            break;
        }
    }  

    getDocumentDefinition(json, modulename) {
        return {
            content: [
            {
              text: modulename,
              bold: true,
              fontSize: 20,
              alignment: 'center',
              margin: [0, 0, 0, 20]
            },
            {
            columns:  this.makePdfcontrentFromJson(json)
            }],
            styles: {
              name: {
                fontSize: 16,
                bold: true
            }
          }
        };
    }

    makePdfcontrentFromJson(providedjson){
        if(providedjson.hasOwnProperty('id'))
               delete providedjson['id'];
        let mainArr =[];
        let nestedArr=[];
        for (let key in providedjson) {           
            let contentjson ={};
            var showKey = key;
            showKey = showKey.replace(/([a-z])([A-Z])/g, '$1 $2');
            showKey = showKey.replace(/([A-Z])([A-Z][a-z])/g, '$1 $2');
            showKey = showKey.charAt(0).toUpperCase()+ showKey.slice(1);
            contentjson['text'] = showKey+': '+ this.jsonValuecheck(providedjson[key]);
            nestedArr.push(contentjson);     
        }
        mainArr.push(nestedArr);
        return mainArr;
    }

    jsonValuecheck(providedjsonValue){
        if(typeof providedjsonValue == 'object'){
            return this.checkVal(providedjsonValue.description);
        }else{
            return providedjsonValue;
        }
    }

}