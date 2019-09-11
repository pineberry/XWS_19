import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { AccommodationUnit } from '../shared/models/accommodation-unit.model';


@Component({
	selector: 'app-sifrarnik',
	templateUrl: './sifrarnik.component.html',
	styleUrls: ['./sifrarnik.component.css']
})
export class SifrarnikComponent implements OnInit {
	response: any;
	accommodations: AccommodationUnit[] = [];

	constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

	ngOnInit() {

	}

	collect() {
		
	}

}
