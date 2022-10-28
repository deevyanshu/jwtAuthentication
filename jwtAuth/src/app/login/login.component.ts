import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { JwtRequest } from '../jwt-request';
import { JwtResponse } from '../jwt-response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  jwtRequest:JwtRequest=new JwtRequest();
  

  constructor(private service:AuthService,private router:Router) { }

  ngOnInit(): void {
  }

  handleLogin()
  {
    this.service.auth(this.jwtRequest).subscribe(data=>{
      sessionStorage.setItem('token',data.token);
      sessionStorage.setItem('username',this.jwtRequest.username);
      this.router.navigate(['/dash']);
    },error=>{
      alert("error")
    })
  }

}
