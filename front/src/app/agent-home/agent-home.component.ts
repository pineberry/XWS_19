import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-agent-home',
  templateUrl: './agent-home.component.html',
  styleUrls: ['./agent-home.component.css']
})
export class AgentHomeComponent implements OnInit {

  constructor(private cookie: CookieService, private router: Router) { }

  ngOnInit() {
  }

  logOut()
  {
  	this.cookie.delete('Authorization');
  	if (this.cookie.get('Authorization') == '') {
  		console.log('Agent logged out!');
  	}
  	this.router.navigate(['/']);
  }
}
