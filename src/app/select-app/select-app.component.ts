import { Component, OnInit, ViewContainerRef, NgZone, TemplateRef, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-select-app',
  templateUrl: './select-app.component.html',
  styleUrls: ['./select-app.component.css']
})
export class SelectAppComponent implements OnInit {
  @Input() mainlabel;
  @Input() mainlabelclass;
  @Input() mainid;
  @Input() mainLabelRequired = true;
  @Input() optionsValues = [];
  @Input() selectedVal;
  @Output() customChange = new EventEmitter();
  config = {};

  constructor(private vcr: ViewContainerRef, private zone: NgZone, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.config = {
      displayKey: "description", //if objects array passed which key to be displayed defaults to description
      search: true, //true/false for the search functionlity defaults to false,
      height: 'auto', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
      placeholder: 'Select ' + this.mainlabel,// text to be displayed when no item is selected defaults to Select,
      customComparator: () => { }, // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
      limitTo: this.optionsValues.length, // a number thats limits the no of options displayed in the UI similar to angular's limitTo pipe
      moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
      noResultsFound: 'No results found!', // text to be displayed when no items are found while searching
      searchPlaceholder: 'Search ' + this.mainlabel, // label thats displayed in search input,
      searchOnKey: "description", // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
      clearOnSelection: false// clears search criteria when an option is selected if set to true, default is false
    }
  }

  fnSelectVal() {
    this.customChange.emit(this.selectedVal);
  }

}
