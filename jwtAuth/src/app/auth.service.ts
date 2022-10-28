import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtRequest } from './jwt-request';
import { JwtResponse } from './jwt-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseurl="http://localhost:9000/user";

  constructor(private http:HttpClient) { }

  auth(jwtRequest:JwtRequest):Observable<JwtResponse>
  {
    return this.http.post<JwtResponse>(`${this.baseurl}/auth`,jwtRequest);
    
  }

  getLoggedinUsername()
  {
    let user=sessionStorage.getItem('username');
    if(user==null)
    {
      return null;
    }else
    {
      return user;
    }
  }

  isUserLoggedIn()
  {
    let user=sessionStorage.getItem('username');
    if(user==null)
    {
      return false;
    }else
    {
      return true;
    }
  }

  logout()
  {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
  }

  hello():Observable<Object>{
    return this.http.get(`${this.baseurl}/welcome`);
  }

}
