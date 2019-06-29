import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-amenity-form',
  templateUrl: './amenity-form.component.html',
  styleUrls: ['./amenity-form.component.css']
})
export class AmenityFormComponent implements OnInit{
	amenities: any;
  response: any;
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {
    this.http.get('http://localhost:8083/amenity/all')
    .subscribe((response) =>
      {
        this.response = response;
        this.amenities = this.response.amenities;
        console.log(this.response);
      });
  }

  createAmenity(amenity: string)
  {
  	const body = new HttpParams().set('amenity', amenity);
  	this.http.post('http://localhost:8081/amenity-agent/add?amenity=' + amenity, amenity).subscribe(); 
    this.router.navigate(['/agent-home']);
  }
}
