import { CanActivateFn } from '@angular/router';
import { LoginService } from '../services/login.service';
import { ɵɵinject } from '@angular/core';


export const authGuard: CanActivateFn = (route, state) => {
  const authService =  ɵɵinject(LoginService);

  if (authService.isLoggedIn()) {
    return true; 
  } else {
    window.location.href="/login"
    return false; 
  }
};
