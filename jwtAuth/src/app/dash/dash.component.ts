import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.css']
})
export class DashComponent implements OnInit {

  welcome:string;
  constructor(private service:AuthService,private router:Router) { }

  ngOnInit(): void {
    this.service.hello().subscribe(data=>{
      this.welcome='welcome' + ' '+sessionStorage.getItem('username');
      
    })
  }

  logout()
  {
    this.service.logout();
    this.router.navigate(['/login'])
  }



}
