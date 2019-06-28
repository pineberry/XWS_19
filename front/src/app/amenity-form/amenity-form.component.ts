import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-amenity-form',
  templateUrl: './amenity-form.component.html',
  styleUrls: ['./amenity-form.component.css']
})
export class AmenityFormComponent implements OnInit {
	amenities: any;

  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {
  }

  createAmenity(amenity: string)
  {
  	const body = new HttpParams().set('amenity', amenity);
  	this.http.get('http://localhost:8081/amenity-agent/add')
  	.subscribe(() =>
  	{
  		this.http.get('http://localhost:8083/amenity/all')
			.subscribe((response) =>
			{
				this.amenities = response;
			});
  	});
  }
}
