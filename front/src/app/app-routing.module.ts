import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { ContentComponent } from './content/content.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { AgentHomeComponent } from './agent-home/agent-home.component';
import { AmenityFormComponent } from './amenity-form/amenity-form.component';
import { AgentAccommodationsComponent } from './agent-accommodations/agent-accommodations.component';


const routes: Routes = [
	{ path: 'search', component:SearchComponent },
	{ path: '', component:ContentComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'content', component: HeaderComponent },
	{ path: 'user-home', component: UserHomeComponent },
	{ path: 'agent-accommodations', component: AgentHomeComponent },
	{ path: 'amenity', component: AmenityFormComponent },
	{ path: 'agent-home', component: AgentAccommodationsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
