import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private http: HttpClient, private cookie: CookieService) { }

  ngOnInit() {
  }
  test()
  {
    let headers = new HttpHeaders();
    this.http.get('http://localhost:8083/user/all', { headers: headers, responseType: 'text' })
    .subscribe((response) => 
      {
        console.log(this.cookie.getAll());
        console.log('Model: ' + response)
      });
	
  }
}
