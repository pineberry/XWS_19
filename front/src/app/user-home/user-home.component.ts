import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {
	
  auth: string = "";
	response: any;
  
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }

  ngOnInit() 
  {
  	this.auth = this.cookie.get('Authorization');
  	const body = new HttpParams().set('id', this.auth);
  	this.http.get('http://localhost:8083/user/' + this.auth + '/reservations')
  	.subscribe((response) => 
  	{
  		this.response = response;
  		console.log(response);
  	});
  }
  
  logOut()
  {
    this.cookie.delete('Authorization');
    if (this.cookie.get('Authorization') == '') {
      console.log('User logged out!');
    }
    this.router.navigate(['/']);
  }
}
