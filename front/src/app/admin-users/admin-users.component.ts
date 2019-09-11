import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { User } from "../shared/models/user.model"; 


@Component({
	selector: 'app-admin-users',
	templateUrl: './admin-users.component.html',
	styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {
	users: User[] = [];
	response: any;

	constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

	ngOnInit() {

		this.collect();
	}

	activate(id: number) {
		let user: User = new User();

		this.http.get("http://localhost:8083/user/" + id)
		.subscribe((response) =>
		{
			this.response = response;
			user = this.response;
			user.confirmed = "activated";
			this.http.post("http://localhost:8083/user/activate", user)
			.subscribe((response) => 
			{
				this.response = response;
				this.collect();
			});
		});
	}

	block(id: string) {
		let user: User = new User();

		this.http.get("http://localhost:8083/user/" + id)
		.subscribe((response) =>
		{
			this.response = response;
			user = this.response;
			this.http.post("http://localhost:8083/user/block", user)
			.subscribe((response) => 
			{
				this.response = response;
				this.collect();
			});
		});
	}

	delete(id: string) {
		let user: User = new User();

		this.http.get("http://localhost:8083/user/" + id)
		.subscribe((response) =>
		{
			this.response = response;
			user = this.response;
			this.http.post("http://localhost:8083/user/delete", user)
			.subscribe();

			this.collect();
		});
	}

	collect() {

		this.http.get("http://localhost:8083/user/all")
		.subscribe((response) => 
		{
			this.response = response;
			this.users = this.response.users;
		});
	}
}
