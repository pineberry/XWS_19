import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule, FormGroup } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { DatePipe } from '@angular/common'


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ContentComponent } from './content/content.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { AgentHomeComponent } from './agent-home/agent-home.component';
import { AccommodationFormComponent } from './accommodation-form/accommodation-form.component';
import { AmenityFormComponent } from './amenity-form/amenity-form.component';
import { AgentAccommodationsComponent } from './agent-accommodations/agent-accommodations.component';
import { AgentReservationsComponent } from './agent-reservations/agent-reservations.component';

@NgModule({
  declarations: [
	AppComponent,
	HeaderComponent,
	ContentComponent,
	SearchComponent,
	LoginComponent,
	UserHomeComponent,
	AgentHomeComponent,
	AccommodationFormComponent,
	AmenityFormComponent,
	AgentAccommodationsComponent,
	AgentReservationsComponent
  ],
  imports: [
	BrowserModule,
	AppRoutingModule,
	HttpClientModule,
	FormsModule,
	ReactiveFormsModule
  ],
  providers: [ 
  	CookieService,
  	DatePipe ],
bootstrap: [AppComponent]
})
export class AppModule { }
	