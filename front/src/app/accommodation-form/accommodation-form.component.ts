import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { Location } from '../model/location.model';

@Component({
  selector: 'app-accommodation-form',
  templateUrl: './accommodation-form.component.html',
  styleUrls: ['./accommodation-form.component.css']
})
export class AccommodationFormComponent implements OnInit {
	amenities: any;
  response: any;
  authorization: string;
  info: string[];
  locationID: string;
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {
  	console.log(this.cookie.getAll());
  	this.http.get('http://localhost:8083/amenity/all')
    .subscribe((response) =>
      {
        this.response = response;
        this.amenities = this.response.amenities;
      });
  }
  createAccommodation(city: string, state: string, address: string, type: string, 
		category: string, description: string, unitCapacity: string, cancelationPeriod: string, price: string)
  {
  	this.http.post('http://localhost:8081/location-agent/add?'
  		+ 'state=' + state
  		+ '&city=' + city
  		+ '&address=' + address, null)
    .subscribe((response) =>
    {
      this.response = response;
      this.locationID = this.response.id;
      console.log("evo me!")
      this.cookie.set('locationID', this.locationID);
      this.authorization = this.cookie.get('Authorization');
     	//this.cookie.deleteAll();
			this.info = this.authorization.split('&');
			//hostId=1&locationID=19&type=apartman&category=4*&unitCapacity=1&cancelationPeriod=2&price=10&description=50
      this.http.post('http://localhost:8081/accommodation-agent/postAccommodation?'
	  		+ 'hostId=' + this.info[0]
	  		+ '&locationID=' + this.cookie.get('locationID')
	  		+ '&type=' +  type
	  		+ '&category=' +  category
	  		+ '&unitCapacity=' + unitCapacity 
	  		+ '&cancelationPeriod=' + cancelationPeriod 
	  		+ '&price=' + price
	  		+ '&description=' + description , null)
	    .subscribe((response) =>
      {
        this.response = response;
        console.log(this.response);
      });
	 });
  }
}
