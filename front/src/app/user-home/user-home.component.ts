import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { Reservation } from '../shared/models/reservation.model';
import { AccommodationUnit } from '../shared/models/accommodation-unit.model';



@Component({
    selector: 'app-user-home',
    templateUrl: './user-home.component.html',
    styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {
	
    auth: string = "";
    response: any;
    reservations: Reservation[] = [];
    accommodations: AccommodationUnit[] = []; 

    constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

    ngOnInit() 
    {
        if (this.cookie.get('Authorization') == null || this.cookie.get('Authorization') == "") {
            this.router.navigate(['/login']);
        } else {
            var hostInfo = atob(this.cookie.get('Authorization').slice(6));
            let hostInfoParts = hostInfo.split('&');
            let userId = +hostInfoParts[0];

            this.http.get('http://localhost:8083/accommodations')
            .subscribe((response) => 
            {
                this.response = response;
                this.accommodations = this.response.accommodationUnits;

                this.http.get('http://localhost:8083/user/reservations/' + userId)
                .subscribe((response) => 
                {
                    this.response = response;
                    this.reservations = this.response.reservations;
                    
                    console.log(this.accommodations);
                    console.log(this.reservations);

                });

            });
        }
    }
}
