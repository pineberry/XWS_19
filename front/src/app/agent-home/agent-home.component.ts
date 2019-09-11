import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { User } from "../shared/models/user.model"; 

@Component({
	selector: 'app-agent-home',
	templateUrl: './agent-home.component.html',
	styleUrls: ['./agent-home.component.css']
})
export class AgentHomeComponent implements OnInit {
	user: User = new User();
    response: any;
    activated: string = "";

	 constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

	ngOnInit() {

		if (this.cookie.get('Authorization') == null || this.cookie.get('Authorization') == "") {
			this.router.navigate(['/login']);
		} else {
			var hostInfo = atob(this.cookie.get('Authorization').slice(6));
			let hostInfoParts = hostInfo.split('&');
			let userId = +hostInfoParts[0];

			this.http.get("http://localhost:8083/user/" + userId)
			.subscribe((response) =>
			{
				this.response = response;
				this.user = this.response;
				if(this.user.confirmed == 'activated')
				{
					this.activated = "activated";
				}
				else if(this.user.confirmed == 'blocked')
				{
					this.activated = "blocked";
				} else {
					this.activated = "waiting";
				}

			});
		}
	}
}
