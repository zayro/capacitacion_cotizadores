import { Component, OnInit } from '@angular/core';
import { Empleadoservice } from '../services/emepleados.services';
import { Empleado } from '../interface/empleados.interface';

/**
 * This class represents the lazy loaded AboutComponent.
 */
@Component({
  moduleId: module.id,
  selector: 'sd-empleados',
  templateUrl: 'empleados.component.html',
  styleUrls: ['../../../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class EmpleadosComponent implements OnInit {

  newId = 60;
  empleados: Empleado[];
  empleado: Empleado;
  mostrar: boolean;
  esEditar: boolean;
  txtAccion = 'Listado de';

  constructor(private empleadoservice: Empleadoservice) {
  }

  ngOnInit() {
    this.empleado = {id: 0, names: '', email: '', country: ''};
    this.getEmpleadosLocal();
  }

  getEmpleadosLocal(): void {
    this.empleados = this.empleadoservice.getEmpleadosLocal();
  }
  
  getEmpleadosApi(): void {
    this.empleadoservice.getEmpleadosApiObs().subscribe(empleados => this.empleados = empleados);
  }

  onSelect(empleado: Empleado): void {
    this.mostrar = true;
    this.empleado = empleado;
    this.esEditar = true;
    this.txtAccion = 'Editar ';
  }

  onClickNuevo(): void {
    this.empleado = {id: 0, names: '', email: '', country: ''};
    this.mostrar = true;
    this.esEditar = false;
    this.txtAccion = 'Agregar ';

  }

  onSubmit(dataForm: any): boolean {

    let error = false;

    if (!dataForm.value.txtNames.trim()) { alert('Ingrese su names'); error = true; } else
    if (!dataForm.value.txtEmail.trim()) { alert('Ingrese su email'); error = true; } else
    if (!dataForm.value.txtCountry.trim()) { alert('Ingrese su country'); error = true; }

    if (!error) {
      
      if (!this.esEditar) {

        this.empleados.push(
          {id: this.newId, names: dataForm.value.txtNames, email: dataForm.value.txtEmail,
             country: dataForm.value.txtCountry});
        this.newId++;
        
      }

      this.empleado = {id: 0, names: '', email: '', country: ''};

      this.mostrar = false;
      this.txtAccion = 'Listado de';
    }   

    return false;

  }

  onClickCancelar(empleado: Empleado): void {
    this.empleado = empleado;
    this.mostrar = false;
    this.txtAccion = 'Listado de';
    this.empleado = {id: 0, names: '', email: '', country: ''};

  }

}
