import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-agent-accommodations',
  templateUrl: './agent-accommodations.component.html',
  styleUrls: ['./agent-accommodations.component.css']
})
export class AgentAccommodationsComponent implements OnInit {
	accommodations: any = [];
	agentId: number;
	response: any;
  addAccFlag: boolean = false;

  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {

  	var hostInfo = atob(this.cookie.get('Authorization').slice(6));
    let hostInfoParts = hostInfo.split('&');
    this.agentId = +hostInfoParts[0];

  	this.http.get('http://localhost:8083/accommodations/all?id=' + this.agentId)
  		.subscribe((response) => 
  			{
  				this.response = response;
  				this.accommodations = this.response.accommodationUnits;
  				console.log(this.response);
  			});
  }

  addAcc() {
    this.addAccFlag = !this.addAccFlag;
  }

}
