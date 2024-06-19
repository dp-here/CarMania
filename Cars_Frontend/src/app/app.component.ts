import { Component, NgModule } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CarComponent } from './car/car.component';
import { NavBarComponent } from './user/nav-bar/nav-bar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CarComponent, NavBarComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'cars_frontend';
}
