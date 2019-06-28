import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	isType: boolean = false;
  user: any;
  response: any;

  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }
    

  ngOnInit() {
  }

  typeOfUser()
  {
  	this.isType = !this.isType;
  }
  encrypt(username: string, password: string)
  {
  	let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + btoa(username +"&"+ password));
  	
    this.http.get('http://localhost:8083/login/', { headers: headers })
	  .subscribe((response) => {
      this.response = response;
      var expireDate = new Date().getTime() + (1000 * 1000);
      var auth = this.response.user.id + "&" + this.response.user.typeOfUser;

      if (this.response.user.typeOfUser == 'agent') 
      {
        this.router.navigate(['/agent-home']);
      }
      else if (this.response.user.typeOfUser == 'user') 
      {
        this.router.navigate(['/user-home']);
      }

      this.cookie.set('Authorization', auth, expireDate);
      console.log(this.cookie.getAll());
      this.cookie.delete('Authorization');
      console.log(this.cookie.getAll());
      this.user = this.cookie.get('Authorization');
    }, 
      error => console.log(error));
  }
}
