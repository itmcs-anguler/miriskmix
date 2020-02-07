import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { AppComponent } from '@app/app.component';
import * as $ from 'jquery';
import { isUndefined } from 'util';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/_services';
import { RiskIncidentComponent } from '@app/risk-incident/risk-incident.component';
import { RiskRegisterComponent } from '@app/risk-register/risk-register.component';
import { AssetRegisterComponent } from '@app/asset-register/asset-register.component';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  host: {
    '(document:click)': 'closeDropDown($event)',
  }
})
export class NavbarComponent implements OnInit {
  dropdownVisible: boolean = false;
  projectName = 'MIRisk';
  currentDrpDownId: string = null;
  userName;
  allowedpagesArray = [];
  adminaccount = true;
  myAccount = true;
  showHide(id) {
    $('.dropdown-menu').hide();
    this.dropdownVisible = false;
    if (this.currentDrpDownId == null || this.currentDrpDownId != id) {
      this.currentDrpDownId = id;
      this.dropdownVisible = true;
      $('#' + id).show();
    } else if (this.currentDrpDownId == id) {
      this.currentDrpDownId = null;
    }
  }

  navClick() {
    $('#navId').on('click', 'button', function () {
      $('div button.active').removeClass('active');
      $(this).addClass('active');
    });
  }

  closeDropDown(ctrl) {
    if (!isUndefined(ctrl.target.attributes) && !isUndefined(ctrl.target.attributes[3])) {
      if (ctrl.target.attributes[3]['value'] != 'maindropdown') {
        $('.dropdown-menu').hide();
        this.currentDrpDownId = null;
        this.dropdownVisible = false;
      }
    } else {
      $('.dropdown-menu').hide();
      this.currentDrpDownId = null;
      this.dropdownVisible = false;
    }
  }

  logout() {
    sessionStorage.clear();
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  constructor(private router: Router,
    private authenticationService: AuthenticationService,
    private cdref: ChangeDetectorRef
  ) { }

  openUrl(url) {
    this.router.navigate([url]);
  }

  ngOnInit() {
    this.userName = sessionStorage.getItem('userName');
    this.makePagesArray();
    setTimeout(function () {
      if ($('#adminAccount').find('a').length <= 1) {
        $('#adminMasters').hide();
      }
      if ($('#myAccount').find('a').length == 0) {
        $('#adminRightsRoles').hide();
      }
    }, 300);

  }

  ngAfterViewInit() {
    if ($('#adminAccount').find('a').length < 1) {
      this.adminaccount = false;
    }
  }

  makePagesArray() {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    user['permissions'].forEach(element => {
      this.allowedpagesArray.push(element.pageName);
    });
  }

  checkRegardingPageAccess(pageName) {
    if (this.allowedpagesArray.indexOf(pageName) != -1) {
      return true;
    } else {
      return false;
    }
  }


  setVariable(flag) {
    if (flag == 'searchIncident') {
      RiskIncidentComponent.incidentOption = 'search';
    } else if (flag == 'addIncident') {
      RiskIncidentComponent.incidentOption = 'add';
    } else if (flag == 'searchRiskRegister') {
      RiskRegisterComponent.riskRegisterOption = 'search';
    } else if (flag == 'addRiskRegister') {
      RiskRegisterComponent.riskRegisterOption = 'add';
      RiskRegisterComponent.self.fillForm('');
    } else if (flag == 'searchAssetRegister') {
      AssetRegisterComponent.assetRegistrationOption = 'search';
      AssetRegisterComponent.self.changeMenu('Grid');
    } else if (flag == 'addAssetRegister') {
      AssetRegisterComponent.assetRegistrationOption = 'add';
      AssetRegisterComponent.self.fillForm('');
      AssetRegisterComponent.self.changeMenu('Add');
    }
  }

}
