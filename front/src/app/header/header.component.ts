import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;
  user: string;
  
  constructor(private http: HttpClient, private cookie: CookieService, private router: Router) { }
  
  ngOnInit() {
    var auth = this.cookie.get('Authorization');
    if (auth != '') {
      auth = auth.substring(6);
      auth = atob(auth);
      var parts: string[] = auth.split('&');
      this.isLogged = true;
      this.user = parts[1];
    }
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
