import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '@app/_services/httpClient.service';
import * as $ from 'jquery';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public static self: DashboardComponent;
  mainpath = 'riskregisters';
  constructor(private httpclientService: HttpClientService,private router: Router) {    
  }

  getAllData() {
    this.httpclientService.get(this.mainpath).subscribe(
      data => {
       
        let responsecontent = data['content'];

        for (let index = 0; index < responsecontent.length; index++) {
          let id = responsecontent[index]['id'];
          let inheritRiskAssessmentScore = responsecontent[index]['inheritRiskAssessmentScore'];
          let residualRiskAssessmentScore = responsecontent[index]['residualRiskAssessmentScore'];
          let btn = '<a class="btn-tmp" style="position: relative;background-color: #252424;margin: 5px;border-radius: 62%;padding-right: 5px;padding-left: 5px;color: white;"> ' + id + ' </a>';
          $('#i' + inheritRiskAssessmentScore).html($('#i' + inheritRiskAssessmentScore).html() + btn);
          $('#r' + residualRiskAssessmentScore).html($('#r' + residualRiskAssessmentScore).html() + btn);
        }

        this.bindOnclickEvent();

      },
      error => {

      });
  }
  bindOnclickEvent() {
    $('div .row .divstyle .btn-tmp').each(function () {
      $(this).on('click', function () {
        sessionStorage.setItem('riskRegisterId', $(this).html());       
        window.location.replace('risk-register');
      });

    });
  }

  ngOnInit() {
    this.getAllData();
  }
}
