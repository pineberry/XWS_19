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
import { AgentReservationsComponent } from './agent-reservations/agent-reservations.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminAgentsComponent } from './admin-agents/admin-agents.component';
import { SifrarnikComponent } from './sifrarnik/sifrarnik.component';


const routes: Routes = [
	{ path: 'search', component:SearchComponent },
	{ path: '', component:ContentComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'content', component: HeaderComponent },
	{ path: 'user-home', component: UserHomeComponent },
	{ path: 'agent-home', component: AgentHomeComponent },
	{ path: 'amenity', component: AmenityFormComponent },
	{ path: 'agent-accommodations', component: AgentAccommodationsComponent },
	{ path: 'agent-reservations', component: AgentReservationsComponent },
	{ path: 'admin-home', component: AdminHomeComponent },
	{ path: 'admin-users', component: AdminUsersComponent },
	{ path: 'admin-agents', component: AdminAgentsComponent },
	{ path: 'sifrarnik', component: SifrarnikComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
