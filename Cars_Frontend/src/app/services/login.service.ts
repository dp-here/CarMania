import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

   url = "http://localhost:8082"
  constructor(private http: HttpClient) { }

  loginUser(token: string){
    
    localStorage.setItem('token', token);
  }

  generateToken(credentials: any){
    return this.http.post(`${this.url}/auth/login`, credentials);
  }


  isLoggedIn(){
    let token= localStorage.getItem('token');
    if(token){
      return true;
    }else{
      return false;
    }
  }


  logout(){
    localStorage.removeItem('token');
    return true;
  }

  getToken(){
    return localStorage.getItem('token');
  }

  getRegister(data: any){
    return this.http.post(`${this.url}/auth/signup`, data);
  }


}
