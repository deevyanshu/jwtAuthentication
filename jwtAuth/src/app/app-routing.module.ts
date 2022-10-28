import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashComponent } from './dash/dash.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:"",redirectTo:"/login",pathMatch:'full'},
  {path:"login",component:LoginComponent},
  {path:"dash",component:DashComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
