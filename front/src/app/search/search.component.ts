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
	isAdvanced: boolean = true;
  	accommodations: any = [];
  	response: any;
  	auth: string;
  	info: string[];
  	error: boolean = false;
  	amenities: boolean[] = [];
  	infoText: string = "";


  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {
  	this.http.get('http://localhost:8083/locations')
		.subscribe((response) => 
		{
			this.response = response;
			this.locations = response;
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

	removeError() {
		this.error = false;
	}

	searchAdvanced(location: string, type: string, category: string, wifi: boolean, parking: boolean, fen: boolean, 
  	kuhinja: boolean, ljubimci: boolean, kada: boolean, bazen: boolean, terasa: boolean, pogled: boolean,
  	kafeaparat: boolean, vesmasina: boolean, izolacija: boolean, checkin: string, checkout: string, guests: string) {
		if (location == null || checkin == null || checkout ==  null || guests == null || type == null || category == null) {
			this.error = true;
		} else {
			this.amenities[0] = wifi;
			this.amenities[1] = parking;
			this.amenities[2] = fen;
			this.amenities[3] = kuhinja;
			this.amenities[4] = ljubimci;
			this.amenities[5] = kada;
			this.amenities[6] = bazen;
			this.amenities[7] = terasa;
			this.amenities[8] = pogled;
			this.amenities[9] = kafeaparat;
			this.amenities[10] = vesmasina;
			this.amenities[11] = izolacija;
			for (var i = this.amenities.length - 1; i >= 0; i--) {
				if (this.amenities[i] == undefined) {
					this.amenities[i] = false;
				}
			}
		}
		this.http.get('http://localhost:8082/search/more?'
			+ 'location=' + location 
			+ '&checkin=' + checkin
			+ '&checkout=' + checkout
			+ '&guests=' +  guests
			+ '&type=' + type
			+ '&category=' + category
			+ '&amenities=' + this.amenities)
		.subscribe((response) =>
			{
				this.response = response;
				console.log(this.response);
				this.accommodations = this.response.accommodationUnits;
			});
  	}

	search(location: string, checkin: string, checkout: string, guests: string)
	{
		if (location == null || checkin == null || checkout ==  null || guests == null) {
			this.error = true;
		} else {
		this.http.get('http://localhost:8082/search?'
			+ 'location=' + location 
			+ '&checkin=' + checkin
			+ '&checkout=' + checkout
			+ '&guests=' +  guests).subscribe((response) => 
			{
				this.response = response;
				this.accommodations = this.response.accommodationUnits;
				if (this.accommodations.length == 0) {
					this.infoText = "Nema raspolozivog smestaja za unesene parametre!"
				}
			});
		}
	}

	calculatePrice(defaultPrice: number, checkin: string, checkout: string) {
		let checkIn = new Date(checkin);
		let checkOut = new Date(checkout);
		
		var diff = Math.abs(checkOut.getTime() - checkIn.getTime());
		var diffDays = Math.ceil(diff / (1000 * 3600 * 24)); 
		return diffDays * defaultPrice;
	}

	book(acc: string, checkin: string, checkout: string)
	{
		console.log(acc, checkin, checkout);

		this.auth = this.cookie.get('Authorization');
		console.log(this.auth);
		if (this.auth == null || this.auth == "") {
			this.router.navigate(['/login']);
		} else {
			var hostInfo = atob(this.cookie.get('Authorization').slice(6));
    		let hostInfoParts = hostInfo.split('&');
    		let userId = +hostInfoParts[0];
    		this.http.post('http://localhost:8084/book-accommodation/book?'
    			+ 'userId' + userId 
				+ 'accommodationId=' + acc 
				+ '&id=' + this.info[0] 
				+ '&checkincheckout=' + checkin + 'x' + checkout)
			.subscribe((response) => 
				{
					console.log(response);
				});
		}
		
		
	}
}
