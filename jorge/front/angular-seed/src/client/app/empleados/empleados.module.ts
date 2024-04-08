import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpleadosComponent } from './empleados.component';
import { EmpleadosRoutingModule } from './empleados-routing.module';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [CommonModule, EmpleadosRoutingModule, SharedModule],
  declarations: [EmpleadosComponent],
  exports: [EmpleadosComponent]
})
export class EmpleadosModule { }
