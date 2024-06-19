import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CarsService } from '../services/cars.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CoreService } from '../services/core.service';


@Component({
  selector: 'app-car-add-edit',
  standalone: true,
  imports: [MatFormFieldModule, 
    MatButtonModule, MatInputModule, MatDialogModule, ReactiveFormsModule],
  templateUrl: './car-add-edit.component.html',
  styleUrl: './car-add-edit.component.css'
})
export class CarAddEditComponent {
  carForm: FormGroup;

  constructor(
    private _fb: FormBuilder,
    private _carService: CarsService,
    private _dialogRef: MatDialogRef<CarAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService
  ) {
    this.carForm = this._fb.group({
      name: '',
      origin: '',
      price: '',
    });
  }

  ngOnInit(): void {
    this.carForm.patchValue(this.data);
  }

  onSubmit() {
    if (this.carForm.valid) {
      if (this.data) {
        this._carService
          .updateCar(this.data.id, this.carForm.value)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('Car detail updated!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
      } else {  
        this._carService.addCar(this.carForm.value).subscribe({
          next: (val: any) => {
            this._coreService.openSnackBar('Employee added successfully');
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err);
          },
        });
      }
    }
    console.log(this.carForm.value)
  }
}
