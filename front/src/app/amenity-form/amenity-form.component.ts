import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { Amenity } from "../shared/models/amenity.model"

@Component({
  selector: 'app-amenity-form',
  templateUrl: './amenity-form.component.html',
  styleUrls: ['./amenity-form.component.css']
})
export class AmenityFormComponent implements OnInit{
	amenityForm: FormGroup;
  amenity: Amenity;

  amenities: any;
  response: any;
  
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() {
    this.amenityForm = new FormGroup
    ({
      amenity: new FormControl()
    });

    this.http.get('http://localhost:8083/amenities')
    .subscribe((response) =>
      {
        this.response = response;
        this.amenities = this.response.amenities;
        console.log(this.response);
      });
  }

  newAmenity()
  {
    this.amenity = new Amenity().deserialize(this.amenityForm.value);

    this.http.post('http://localhost:8083/amenities/add', this.amenity).subscribe();
    this.router.navigate(['/agent-home']);
  }

}
