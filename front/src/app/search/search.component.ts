import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
	locations: any;
	amenities: any;
	isAdvanced: boolean = true;
  
  constructor(private http: HttpClient) { }

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
}
