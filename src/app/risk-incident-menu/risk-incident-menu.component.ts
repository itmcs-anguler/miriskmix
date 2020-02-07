import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-risk-incident-menu',
  templateUrl: './risk-incident-menu.component.html',
  styleUrls: ['./risk-incident-menu.component.css']
})
export class RiskIncidentMenuComponent implements OnInit {
  public static showOtherOption;
  public classReference = RiskIncidentMenuComponent;

  setOption = 'Incident';

  constructor() { }

  ngOnInit() {
    RiskIncidentMenuComponent.showOtherOption = false;
  }

  navClick() {
    $('ul').on('click', 'a', function () {
      $('li a.active').removeClass('active');
      $(this).addClass('active');
    });
  }
  getOption(id) {
    this.setOption = id;
  }
}
