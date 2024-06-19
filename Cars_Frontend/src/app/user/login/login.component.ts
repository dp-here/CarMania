import { Component, NgModule } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { LoginService } from '../../services/login.service';
import { response } from 'express';
import { error } from 'console';



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, NavBarComponent, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userForm: any;

  constructor(private formBuilder: FormBuilder,
    private router: Router, private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  submitForm(): void {
    if (this.userForm?.valid) {
     this.loginService.generateToken(this.userForm.value).subscribe(
      (response: any)=>{
        console.log(response.token);
        this.loginService.loginUser(response.token)
        // window.location.href="/dashboard"
      },
      error=>{
        console.log(error);
      }
     )
    }
  }



}
