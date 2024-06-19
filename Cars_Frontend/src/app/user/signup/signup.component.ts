import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { CoreService } from '../../services/core.service';
import { NavBarComponent } from '../nav-bar/nav-bar.component';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule, NavBarComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  userForm: any;

  constructor(private formBuilder: FormBuilder, private _loginService: LoginService,
    private _coreService: CoreService
  ) {}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4), 
        Validators.maxLength(20)]]
      
    });
  }

  submitForm(): void {
    if (this.userForm?.valid) {
      this._loginService.getRegister(this.userForm.value).subscribe({
        next: (val: any) => {
          this._coreService.openSnackBar('Employee added successfully');
        },
        error: (err: any) => {
          console.error(err);
        },
      });
      window.location.href="/login"
    }
    }
  }
