import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
	locations: any;
	amenities: any;
	isAdvanced: boolean = true;
  	accommodations: any;
  	response: any;
  	authorization: string;
  	info: string[];
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {
  	this.http.get('http://localhost:8083/location/all')
		.subscribe((response) => 
		{
			this.locations = response;
		});
		this.http.get('http://localhost:8083/amenity/all')
		.subscribe((response) =>
		{
			this.amenities = response;
		});
  }

  advanced()
	{
		this.isAdvanced = !this.isAdvanced;
	}

	getCities()
	{
		let uniqueCityNames : any[] = [];

		if(this.locations != null)
		{
			for (var i = this.locations.locations.length - 1; i >= 0; i--) {
			 	if (!uniqueCityNames.includes( this.locations.locations[i].city))
			 	{
					uniqueCityNames.push(this.locations.locations[i].city);	 		
			 	}
			}
		}
		return uniqueCityNames;
	}

	search(location: string, checkin: string, checkout: string, guests: string)
	{
		this.http.get('http://localhost:8082/search?'
			+ 'location=' + location 
			+ '&checkin=' + checkin
			+ '&checkout=' + checkout
			+ '&guests=' +  guests).subscribe((response) => 
			{
				this.response = response;
				console.log(this.response.accommodationUnits);
				this.accommodations = this.response.accommodationUnits;
			});
	}
	book(acc: string, checkin: string, checkout: string)
	{
		this.authorization = this.cookie.get('Authorization');
		this.info = this.authorization.split('&');
		this.http.post('http://localhost:8084/book-accommodation/book?'
			+ 'accID=' + acc 
			+ '&id=' + this.info[0] 
			+ '&checkincheckout=' + checkin + 'x' + checkout, acc)
		.subscribe((response) => 
			{
				console.log(response);
			});
		
	}
}
