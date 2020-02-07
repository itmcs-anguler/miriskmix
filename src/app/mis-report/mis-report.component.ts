import { Component, OnInit } from '@angular/core';
import { ChartType, ChartOptions, ChartDataSets } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip, MultiDataSet, Color } from 'ng2-charts';
import { HttpClientService } from '@app/_services/httpClient.service';
import { CommonUtility } from '@app/_services/common.service';
import { filter } from 'rxjs/operators';
@Component({
  selector: 'app-mis-report',
  templateUrl: './mis-report.component.html',
  styleUrls: ['./mis-report.component.css']
})
export class MisReportComponent implements OnInit {

  //doughnut
  public doughnutChartLabels: Label[] = [];// ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nav', 'Dec'];
  public doughnutChartData: MultiDataSet = [[]]; //[[3, 5, 2, 6, 9, 5, 1, 7, 8, 2, 1, 9]];
  //arrayCount
  public doughnutChartType: ChartType = 'doughnut';


  //Bar chart
  public barChartOptions: ChartOptions = {
    responsive: true,
  };

  public barChartLabels: Label[] = ['Financial', 'Operation', 'I.T', 'Regulatory', 'People', 'Fraud'];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: ChartDataSets[] = [
    { data: [3, 8, 6, 2, 9, 5], label: 'Category' }
  ];
  public barChartColors: Color[] = [
    { backgroundColor: 'red' }
  ]

  // Pie
  //Number of High Risk (Residual Score greater than 15 Risk Incidents by Risk Category
  public pieChartOptions: ChartOptions = {
    responsive: true,
  };
  public pieChartLabels: Label[] = ['Financial11', 'Operation', 'I.T', 'Regulatory', 'People', 'Fraud'];
  public pieChartData: SingleDataSet = [3, 8, 6, 2, 9, 5];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];

  // Pie
  //Number of High Risk (Residual Score between 8 and  12 Risk Incidents by Risk Category
  public pieChartOptionstwo: ChartOptions = {
    responsive: true,
  };
  public pieChartLabelstwo: Label[] = ['Financial55', 'Operation', 'I.T', 'Regulatory', 'People', 'Fraud'];
  public pieChartDatatwo: SingleDataSet = [3, 8, 6, 2, 9, 5]
  public pieChartTypetwo: ChartType = 'pie';
  public pieChartLegendtwo = true;
  public pieChartPluginstwo = [];

  // Pie
  //Number of High Risk (Residual Score between 0 and  8 Risk Incidents by Risk Category
  public pieChartOptionsthree: ChartOptions = {
    responsive: true,
  };
  public pieChartLabelsthree: Label[] = ['Financial99', 'Operation', 'I.T', 'Regulatory', 'People', 'Fraud'];
  public pieChartDatathree: SingleDataSet = [3, 8, 6, 2, 9, 5]
  public pieChartTypethree: ChartType = 'pie';
  public pieChartLegendthree = true;
  public pieChartPluginsthree = [];


  //Bar chart
  //Asset Current Value $
  public barChartOptionstwo: ChartOptions = {
    responsive: true,
  };

  public barChartLabelstwo: Label[] = [];
  public barChartTypetwo: ChartType = 'bar';
  public barChartLegendtwo = true;
  public barChartPluginstwo = [];

  public barChartDatatwo: ChartDataSets[] = [
    { data: [], label: 'Current Value $' }
  ];
  public barChartColorstwo: Color[] = [
    {}
  ]

  //Bar chart
  //Document Status Overdue
  public barChartOptionsthree: ChartOptions = {
    responsive: true,
  };

  public barChartLabelsthree: Label[] = ['Compliance Policy', 'Operations Policy', 'Finance Policy'];
  public barChartTypethree: ChartType = 'bar';
  public barChartLegendthree = true;
  public barChartPluginsthree = [];

  public barChartDatathree: ChartDataSets[] = [
    { data: [2, 5, 5], label: 'OverDue' }
  ];
  public barChartColorsthree: Color[] = [
    { backgroundColor: 'orange' }
  ]
  get_rand_color() {
    let color = Math.floor(Math.random() * Math.pow(256, 3)).toString(16);
    while (color.length < 6) {
      color = "0" + color;
    }
    return "#" + color;
  }
  constructor(private commonUtility: CommonUtility, private httpclientService: HttpClientService) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
  }

  arrayCount = [[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]];
  arrayMonth = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nav', 'Dec'];
  dataMonth = 0;
  getMonthCount(dt) {
    const current = new Date();
    const year = current.getFullYear();
    const part = dt.split('-');

    if (part[0] == year) {
      const cnt = part[1].replace(/^0+/, '');
      if (this.dataMonth < cnt) {
        this.dataMonth = cnt;
      }

      this.arrayCount[0][cnt - 1] = this.arrayCount[0][cnt - 1] + 1;
    }
  }

  public donutColors = [
    {
      backgroundColor: [
        'rgba(110, 114, 20, 1)',
        'rgba(118, 183, 172, 1)',
        'rgba(0, 148, 97, 1)',
        'rgba(110, 114, 10, 1)',
        'rgba(118, 183, 152, 1)',
        'rgba(5, 148, 100, 1)',
        'rgba(190, 114, 10, 1)',
        'rgba(148, 153, 152, 1)',
        'rgba(9, 108, 100, 1)',
        'rgba(12, 164, 20, 1)',
        'rgba(18, 153, 102, 1)',
        'rgba(3, 108, 150, 1)',

      ]
    }
  ];

  filterArray() {
    this.arrayCount[0].length = this.dataMonth;
    this.arrayMonth.length = this.dataMonth;
    this.doughnutChartData = this.arrayCount;
    this.doughnutChartLabels = this.arrayMonth;
    console.log('Arrrrrr');
    console.log(this.doughnutChartData);
    console.log('DataMonth:: ' + this.dataMonth);
  }


  getRiskIncidentDetails() {
    this.httpclientService.get('riskincidents').subscribe(
      data => {

        console.log(data);
        for (let index = 0; index < data['content'].length; index++) {
          this.getMonthCount(data['content'][index]['occurrence']);
        }
        this.filterArray();

      },
      error => {

      });
  }

  getPiChartData() {
    this.httpclientService.get('riskregisters/search/getpichartdatabyresidualscore0to8').subscribe(
      data => {

        this.pieChartLabels = data['0to8']['lable'];
        this.pieChartData = data['0to8']['count'];
        this.pieChartLabelstwo = data['8to12']['lable'];
        this.pieChartDatatwo = data['8to12']['count'];
        this.pieChartLabelsthree = data['above15']['lable'];
        this.pieChartDatathree = data['above15']['count'];
        // this.barChartLabels = data['allCategoryName'];
        // this.barChartData[0]['data'] = data['allCategoryCount'];
      },
      error => {

      });

  }
  // public barChartLabelstwo: Label[] = ['Computer', 'Screen', 'Laptop', 'Printer'];
  // public barChartTypetwo: ChartType = 'bar';
  // public barChartLegendtwo = true;
  // public barChartPluginstwo = [];

  // public barChartDatatwo: ChartDataSets[] = [
  //   { data: [150, 400, 300, 100], label: 'Current Value $' }
  // ];
  // public barChartColorstwo: Color[] = [
  //   { backgroundColor: 'blue' }
  // ]
  getAssetCurrentValue() {
    this.httpclientService.get('assetregisters').subscribe(
      data => {

        let lable = [];
        let count = [];
        let color = [];
        this.barChartLabelstwo = [];
        this.barChartDatatwo[0]['data'] = [];

        this.barChartDatatwo[0]['backgroundColor'] = "white";
        this.barChartColorstwo = [];
        for (const iterator of data['content']) {
          lable.push(iterator['assetName']);
          count.push(iterator['currentValue']);
          let randomcolor = this.get_rand_color();
          color.push(randomcolor);
        }
        this.barChartLabelstwo = lable;
        this.barChartDatatwo[0]['data'] = count;
        this.barChartColorstwo.push({ backgroundColor: color });
      },
      error => {

      });
  }
  getDocumentChartData() {
    this.httpclientService.get('documents/search/getdocumentpichartdata').subscribe(
      data => {

        this.barChartLabelsthree = [];
        this.barChartDatathree[0]['data'] = [];
        this.barChartColorsthree = [];
        this.barChartDatathree[0]['backgroundColor'] = "white";
        let color = [];
        for (const iterator of data['lable']) {
          let randomcolor = this.get_rand_color();
          color.push(randomcolor);
        }
        this.barChartLabelsthree = data['lable'];
        this.barChartDatathree[0]['data'] = data['count'];
        this.barChartColorsthree.push({ backgroundColor: color });
      },
      error => {

      });

  }
  // public barChartColorsthree: Color[] = [
  //   { backgroundColor: 'orange' }
  // ]
  getAllCategoryLevelOneOccurenceForPIChart() {
    this.httpclientService.get('riskincidents/search/getallcategoryleveloneoccurenceforpichart').subscribe(
      data => {

        this.barChartLabels = [];
        this.barChartData[0]['data'] = [];
        this.barChartColors = [];
        let color = [];
        this.barChartData[0]['backgroundColor'] = "white";
        for (let index = 0; index < data['lable'].length; index++) {
          this.barChartLabels.push(data['lable'][index]);
          this.barChartData[0]['data'].push(data['count'][index]);
          let randomColor = this.get_rand_color();
          color.push(randomColor);
        }
        this.barChartColors.push({ backgroundColor: color });

        // for (const iterator of data['lable']) {
        //   let randomColor = this.get_rand_color();
        //   color.push({ backgroundColor: randomColor });

        // }
        // this.barChartColors = [];
        // this.barChartColors = color;

      },
      error => {

      });

  }
  ngOnInit() {
    this.getRiskIncidentDetails();
    this.getPiChartData();
    this.getAssetCurrentValue();
    this.getDocumentChartData();
    this.getAllCategoryLevelOneOccurenceForPIChart();

  }

}
