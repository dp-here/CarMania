import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {}

  intercept(request: HttpRequest<object>, next: HttpHandler): Observable<HttpEvent<object>> {
    let newReq= request;

    let token=this.loginService.getToken();
    console.log("INTERCEPTOR", token);

    if(token){
        newReq=newReq.clone({setHeaders:{Authorization:`Bearer ${token}`}})
    }

    return next.handle(newReq);

  }
}
