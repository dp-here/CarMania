import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8082/api/v1/cars';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private _http: HttpClient) { }

  private getToken(): string | null {
    return localStorage.getItem('token');
  }

  private getHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
      'Authorization': `Bearer ${token}`
    });
  }

  addCar(data: any): Observable<any>{
    const headers = this.getHeaders();
    return this._http.post<any>(`${baseUrl}/addCar`, data, { headers });  
    }
  

  updateCar(id: number, data: any): Observable<any> {
    return this._http.put(`${baseUrl}/editById/${id}`, data , { headers: this.getHeaders() });
  }

  getCarList(): Observable<any> {
    const headers = this.getHeaders(); 
    return this._http.get(`${baseUrl}/getAll`, { headers });
  }

  deleteCar(id: number): Observable<any> {
    return this._http.delete(`${baseUrl}/deleteById/${id}`,{ headers: this.getHeaders() });
  }


  searchCar(name?: string, origin?: string): Observable<any> {
    let params = new HttpParams();

    if (name) {
      params = params.set('name', name);
    }

    if (origin) {
      params = params.set('origin', origin);
    }

    const options = {
      headers: this.getHeaders(),
      params: params
    };

    return this._http.get(`${baseUrl}/search`, options);
  }
  }
