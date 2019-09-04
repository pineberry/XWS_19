import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { FormGroup, FormControl , ReactiveFormsModule } from '@angular/forms';
import { Location } from '../shared/models/location.model';
import { AccommodationUnit } from '../shared/models/accommodation-unit.model';

@Component({
  selector: 'app-accommodation-form',
  templateUrl: './accommodation-form.component.html',
  styleUrls: ['./accommodation-form.component.css']
})
export class AccommodationFormComponent implements OnInit {
  accommodationForm: FormGroup;  
  location: Location;
  accommodation: AccommodationUnit;
  
  authorization: string;
  info: string[];
  locationID: string;

  defaultPrice: number;
  pricingPlan = new Map<string, number>();
  pricePlan = new Array();

  error: boolean = false;
  show: string = 'd1';

  newAccFlag: boolean = false;
  
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { 
    
  }

  ngOnInit() {
    this.accommodationForm = new FormGroup
    ({
      location : new FormGroup(
      {
        city: new FormControl(),
        state: new FormControl(),
        address: new FormControl()
      }),
      type: new FormControl(),
      category: new FormControl(),
      unitCapacity: new FormControl(),
      cancelationPeriod: new FormControl(),
      description: new FormControl(),
      amenities: new FormGroup(
      {
        wifi: new FormControl(false),
        parking: new FormControl(false),
        fen: new FormControl(false),
        kuhinja: new FormControl(false),
        ljubimci: new FormControl(false),
        kada: new FormControl(false),
        bazen: new FormControl(false),
        terasa: new FormControl(false),
        pogled: new FormControl(false),
        kafeaparat: new FormControl(false),
        vesmasina: new FormControl(false),
        izolacija: new FormControl(false)
      }),
      defaultPrice: new FormControl(),
      pricePlan: new FormGroup(
       {
         fromDate: new FormControl(),
         toDate: new FormControl(),
         price: new FormControl()
       })
    });

  	console.log(this.cookie.getAll());
  	
  }

  prebaci(showDiv: string)
  { 
    if (showDiv == 'd1' || showDiv == 'd4') {
      this.show = showDiv;
    }

    if (showDiv == 'd2') {
      if (this.accommodationForm.value.location.city == null
       || this.accommodationForm.value.location.state == null
       || this.accommodationForm.value.location.address == null){
        this.error = true;
      } else {
        this.show = showDiv;
        this.error = false;
      }
    }
    if (showDiv == 'd3') {
      if (this.accommodationForm.value.type == null
       || this.accommodationForm.value.category == null
       || this.accommodationForm.value.unitCapacity == null
       || this.accommodationForm.value.cancelationPeriod == null) {
        this.error = true
      } else {
        this.show = showDiv;
        this.error = false;
      }
    }
    if(showDiv == 'd5') {
      if (this.accommodationForm.value.defaultPrice == null) {
        this.error = true;
      } else {
        this.show = showDiv;
        this.error = false;
        this.defaultPrice = this.accommodationForm.value.defaultPrice;
      }  
    }
  }

  newPrice()
  {
    if (this.accommodationForm.value.pricePlan.fromDate == null
     || this.accommodationForm.value.pricePlan.toDate == null
     || this.accommodationForm.value.pricePlan.price == null) {
      this.error = true
    } else {
      let newPrice = [[this.accommodationForm.value.pricePlan.fromDate,
        this.accommodationForm.value.pricePlan.toDate],
        this.accommodationForm.value.pricePlan.price];   
      this.pricingPlan
        .set(this.accommodationForm.value.pricePlan.fromDate + '&' +
             this.accommodationForm.value.pricePlan.toDate,
             this.accommodationForm.value.pricePlan.price)
      this.pricePlan.push(newPrice);
      this.accommodationForm.controls['pricePlan'].reset();
      this.error = false;
    }
    this.newAccFlag = false;
  }

  newAccommodation()
  {
    this.accommodation = new AccommodationUnit().deserialize(this.accommodationForm.value);
    var hostId: number;
    var hostInfo = atob(this.cookie.get('Authorization').slice(6));
    let hostInfoParts = hostInfo.split('&');
    hostId = +hostInfoParts[0];
    this.accommodation.hostId = hostId;

    let obj = Object.create(null);
    for (let [k,v] of this.pricingPlan) {
        obj[k] = v; //look out! Key must be a string!
    }
    this.accommodation.pricePlan = obj;
    if (this.newAccFlag) {
      this.makeRequest();
    }
    this.newAccFlag = true;
  } 
  
  makeRequest() {
    console.log(this.accommodation);
    this.http.post('http://localhost:8081/agent/accommodations/post', this.accommodation)
      .subscribe((res) => 
      {
        console.log(res);
      });
  }
/*    this.http.post('http://localhost:8081/location-agent/', this.accommodation)
     .subscribe((res) => 
     {

     });*/
  

  /*createAccommodation(city: string, state: string, address: string, type: string, 
		category: string, description: string, unitCapacity: string, cancelationPeriod: string, price: string)
  {
  	this.http.post('http://localhost:8081/location-agent/add?'
  		+ 'state=' + state
  		+ '&city=' + city
  		+ '&address=' + address, null)
    .subscribe((response) =>
    {
      this.response_amenity_list = response;
      this.locationID = this.response_amenity_list.id;
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
        this.response_amenity_list = response;
        console.log(this.response_amenity_list);
      });
	 });
  }*/
}
