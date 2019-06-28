import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';


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
	AmenityFormComponent
  ],
  imports: [
	BrowserModule,
	AppRoutingModule,
	HttpClientModule,
	FormsModule
  ],
  providers: [ CookieService ],
bootstrap: [AppComponent]
})
export class AppModule { }
	