import { Component } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { NgClass, NgIf } from '@angular/common';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, NgIf, NgClass],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

  public loggedIn = false;

  constructor(private loginService: LoginService){

  }

  ngOnInit(): void{
    this.loggedIn = this.loginService.isLoggedIn();
  }

  logoutUser(){
    this.loginService.logout();
    location.reload();
  }


}
