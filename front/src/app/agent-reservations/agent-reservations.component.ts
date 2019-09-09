import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { Reservation } from '../shared/models/reservation.model';
import { AccommodationUnit } from '../shared/models/accommodation-unit.model';

@Component({
    selector: 'app-agent-reservations',
    templateUrl: './agent-reservations.component.html',
    styleUrls: ['./agent-reservations.component.css']
})
export class AgentReservationsComponent implements OnInit {

    response: any;
    reservations: Reservation[] = [];
    accommodations: AccommodationUnit[] = []; 
    userId: number = 0;

    constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

    ngOnInit() {

        this.collect();
    }

    confirm(reservationId: number) {
        console.log(reservationId);
        if(this.userId != null) {
            this.http.post('http://localhost:8081/reservation-agent/'+ this.userId + '/confirm', reservationId)
            .subscribe((response) =>
            {
                this.collect();
            });
        }
    }

    cancel(reservationId: number) {
        if(this.userId != null) {
            this.http.post('http://localhost:8081/reservation-agent/'+ this.userId + '/deny', reservationId)
            .subscribe((response) =>
            {
                this.collect();
            });
        }
    }

    collect() {
        if (this.cookie.get('Authorization') == null || this.cookie.get('Authorization') == "") {
            this.router.navigate(['/login']);
        } else {
            var hostInfo = atob(this.cookie.get('Authorization').slice(6));
            let hostInfoParts = hostInfo.split('&');
            this.userId = +hostInfoParts[0];

            this.http.get('http://localhost:8083/accommodations')
            .subscribe((response) => 
            {
                this.response = response;
                this.accommodations = this.response.accommodationUnits;

                this.http.get('http://localhost:8083/user/reservations/' + this.userId)
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
