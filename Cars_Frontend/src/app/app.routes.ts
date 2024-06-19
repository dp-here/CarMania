import { Routes } from '@angular/router';
import { CarComponent } from './car/car.component';
import { LoginComponent } from './user/login/login.component';
import { SignupComponent } from './user/signup/signup.component';
import { authGuard } from './services/auth.guard';

export const routes: Routes = [
{path:'', redirectTo:'login', pathMatch:'full'},
{path:"dashboard",  component: CarComponent, canActivate:[authGuard]},
{path:"login",  component: LoginComponent},
{path:"register",  component: SignupComponent},
{path:'addCar', component: CarComponent},
{path:"**", component: LoginComponent}
];
