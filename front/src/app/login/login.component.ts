import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { User } from "../shared/models/user.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	isAgent: boolean = false;
  user: User = new User();
  headers: HttpHeaders;
  response: any;
  registrationForm: FormGroup;
  loginForm: FormGroup;

  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }
    

  ngOnInit() {
     this.registrationForm = new FormGroup
    ({
      typeOfUser: new FormControl(),
      firstName: new FormControl(),
      lastName: new FormControl(),
      username: new FormControl(),
      password: new FormControl(),
      address: new FormControl(),
      pib: new FormControl()
    });
    this.loginForm = new FormGroup
    ({
      username: new FormControl(),
      password: new FormControl()
    });
  }

  isType(type: string)
  {
    if (type == 'agent') {
      this.isAgent = true;
    } else if (type = 'user') {
      this.isAgent = false;
    }
  }

  routeLoggedUser(typeOfUser: string)
  {
    if (typeOfUser == 'agent') 
    {
      this.router.navigate(['/agent-home']);
    }
    else if (typeOfUser == 'user') 
    {
      this.router.navigate(['/']);
    }
  }

  register()
  {
    this.user = new User().deserialize(this.registrationForm.value);
    this.headers = new HttpHeaders().set('Authorization', 'Basic ' + btoa(this.user.username +"&"+ this.user.password));
   
    this.http.post('http://localhost:8083/user/registration', this.user, { headers : this.headers})
      .subscribe((res) => {
        this.response = res;
        
        this.user = this.response.user;
        console.log(this.response);
        var expireDate = new Date().getTime() + (1000 * 1000);
        var auth = 'Basic ' + btoa(this.user.id +"&"+ this.user.username +"&"+ this.user.password);
        this.cookie.set('Authorization', auth, expireDate);
        console.log(this.cookie.getAll());

        this.routeLoggedUser(this.user.typeOfUser);
      },
        error => console.log(error));
  }

  login()
  {
    this.headers = new HttpHeaders().set('Authorization', 'Basic ' + btoa(this.loginForm.value.username +"&"+ this.loginForm.value.password));

     this.http.get('http://localhost:8083/login/', { headers: this.headers })
      .subscribe((response) => {
        this.response = response;
        this.user = this.response.user;
        console.log(this.response);
        var expireDate = new Date().getTime() + (1000 * 1000);
        var auth = 'Basic ' + btoa(this.user.id +"&"+ this.user.username +"&"+ this.user.password);
        this.cookie.set('Authorization', auth, expireDate);
        console.log(this.cookie.getAll());

    this.routeLoggedUser(this.response.user.typeOfUser);
        });
  }
}
