import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	isTypeF: boolean = false;
  user: any;
  response: any;

  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }
    

  ngOnInit() {
  }

  isType()
  {
  	this.isTypeF = !this.isTypeF;
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
        this.router.navigate(['/']);
      }

      this.cookie.set('Authorization', auth, expireDate);
      console.log(this.cookie.getAll());
    }, 
      error => console.log(error));
  }

  register(username: string, password: string, firstName: string, lastName: string, typeOfUser: string, address: string, pib: string)
  {
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + btoa(username +"&"+ password)).set('Content-Type', 'text/plain');


    if (typeOfUser == 'user') {
      address = "";
      pib = "";
    }

    this.http.post('http://localhost:8083/user/registration?'
      + 'firstName=' + firstName 
      + '&lastName=' + lastName 
      + '&username=' + username
      + '&password=' + password
      + '&typeOfUser=' + typeOfUser
      + '&address=' + address
      + '&pib=' + pib , { headers: headers })
    .subscribe((response) => {
      console.log(response);
      this.user = response;
      var expireDate = new Date().getTime() + (1000 * 1000);
      var auth = this.user.id + "&" + this.user.typeOfUser;

      if (typeOfUser == 'agent') 
      {
        this.router.navigate(['/agent-home']);
      }
      else if (typeOfUser == 'user') 
      {
        this.router.navigate(['/']);
      }

      this.cookie.set('Authorization', auth, expireDate);
      console.log(this.cookie.getAll());
    }, 
      error => console.log(error));
  }
}
