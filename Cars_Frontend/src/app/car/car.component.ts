import { Component, OnInit, ViewChild, signal} from '@angular/core';
import { NavBarComponent } from '../user/nav-bar/nav-bar.component';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CarAddEditComponent } from '../car-add-edit/car-add-edit.component';
import { CarsService } from '../services/cars.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CoreService } from '../services/core.service';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInput, MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-car',
  standalone: true, 
  imports: [NavBarComponent, MatToolbar, MatButtonModule, MatPaginator, MatInputModule, FormsModule,
    MatDialogModule, MatFormField, MatLabel, MatSort, MatIconModule, MatTableModule, MatInput
  ],
  templateUrl: './car.component.html',
  styleUrl: './car.component.css'
})
export class CarComponent implements OnInit{

  displayedColumns: string[] = ['id', 'name', 'origin', 'price', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  nameInput: string = '';
  originInput: string = '';

  constructor(private _dilaog: MatDialog, private _carService: CarsService, private _coreService: CoreService){

  }
  ngOnInit(): void {
   this.getCarDetails();
  }

  openDialog(){
    this._dilaog.open(CarAddEditComponent);
  }

  getCarDetails(){
    this._carService.getCarList().subscribe({
      next: (res) =>{
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
  error: (err) => {console.log(err)}})
  }

  protected readonly value = signal('');

  protected onInput(event: Event) {
    this.value.set((event.target as HTMLInputElement).value);
  }

  deleteCar(id: number) {
    this._carService.deleteCar(id).subscribe({
      next: (res) => {
        this._coreService.openSnackBar('Car details deleted!', 'done');
        this.getCarDetails();
      },
      error: console.log,
    });
  }

  openEditForm(data: any) {
    const dialogRef = this._dilaog.open(CarAddEditComponent, {
      data
    });

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getCarDetails();
        }
      },
    });

  }

  search(name: string, origin: string): void {
    if (name || origin) {
      this._carService.searchCar(name, origin).subscribe({
        next: (res) => {
          this.dataSource = new MatTableDataSource(res['content']);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        },
        error: (err) => {
          console.error(err);
        }
      });
    } else {
      this.getCarDetails(); 
    }
  }

  reset(): void {
    this.nameInput = ''; 
    this.originInput = '';
    this.getCarDetails();
  }
}
