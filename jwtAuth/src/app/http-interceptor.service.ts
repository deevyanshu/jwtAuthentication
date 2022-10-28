import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor(private service:AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(this.service.isUserLoggedIn() && req.url.indexOf('auth')===-1)
    {
      const authreq=req.clone({
        headers:new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': sessionStorage.getItem('token')
        })
      });
      return next.handle(authreq);
    }else
    {
      return next.handle(req);
    }
  }
}
