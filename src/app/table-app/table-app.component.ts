import { Component, OnInit, Input, Renderer, ViewChild, Injectable, Output, EventEmitter } from '@angular/core';
import Swal from 'sweetalert2';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';

declare interface customDatatableInterface {
  title: String;
  cols: any[];
  rows: any[];
  custominput: boolean;
  customcols: string[];
}

@Component({
  selector: 'app-table-app',
  templateUrl: './table-app.component.html',
  styleUrls: ['./table-app.component.css', '../app.component.css']
})
@Injectable({ providedIn: 'root' })
export class TableAppComponent implements OnInit {
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtOptionsCustom: DataTables.Settings = {};
  @Input()
  customDatatableobj: customDatatableInterface;
  dtTrigger: Subject<any[]> = new Subject();

  @Input()
  tableId = "singalTableID";

  @Output()
  customDelete = new EventEmitter();
  @Output()
  customEdit = new EventEmitter();
  @Output()
  customCheck = new EventEmitter();
  @Output()
  customDownload = new EventEmitter();


  constructor() { }

  ngOnInit() {
    this.makeDataTable();
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
    this.rerender();
  }



  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }


  makeDataTable() {
    if (this.customDatatableobj.custominput) {
      this.intialiseTrtdTable();
    }
    if (!this.customDatatableobj.custominput) {
      this.intialiseBtnTable();
    }
  }

  intialiseBtnTable() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      responsive: true,
      dom: 'Blfrtip',
      //buttons: ['excel'],
      destroy: false,
      columns: this.customDatatableobj.cols,
      data: this.customDatatableobj.rows,
      rowCallback: (row: Node, data: any[] | Object, index: number) => {
        const self = this;
        $('td .editcustom', row).unbind('click');
        $('td .editcustom', row).bind('click', () => {
          let id = $(row).find('i').attr('value');
          self.onEdit(id);
        });
        $('td .deletecustom', row).unbind('click');
        $('td .deletecustom', row).bind('click', () => {
          let id = $(row).find('i').attr('value');
          self.confirmFileDeletionSwal(id);
        });
        $('td .checkboxcustom', row).bind('click', () => {
          let id = $(row).find('.checkboxcustom');
          self.onCheck(id);
        });
        $('td .downloadcustom', row).unbind('click');
        $('td .downloadcustom', row).bind('click', () => {
          let downloadPath = $(row).find('.downloadcustom').attr('value');
          self.onDownload(downloadPath);
        });        
        return row;
      }
      /* Declare the use of the extension in the dom parameter
       dom: 'Bfrtip',*/
    };
  }

  intialiseTrtdTable() {
    this.dtOptionsCustom = {
      pagingType: 'full_numbers',
      responsive: true,
      destroy: false,
      dom: 'frtip'
      // Declare the use of the extension in the dom parameter
      //dom: 'Bfrtip',
    };
  }

  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      dtInstance.clear();
      dtInstance.rows.add(this.customDatatableobj.rows);
      dtInstance.draw();
    });
  }

  onEdit(id) {
    this.customEdit.emit(id);
  }

  onCheck(id) {
    this.customCheck.emit(id);
  }

  onDownload(downloadpath) {
    this.customDownload.emit(downloadpath);
  }


  confirmFileDeletionSwal(id) {
    //https://sweetalert2.github.io/
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.customDelete.emit(id);
      }
    })
  }

}
