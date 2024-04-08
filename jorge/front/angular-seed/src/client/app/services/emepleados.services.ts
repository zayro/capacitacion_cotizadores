import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Empleado } from '../interface/empleados.interface';
import { EMPLEADOS } from '../shared/empleados/mock';

@Injectable({
  providedIn: 'root',
})
export class Empleadoservice {

  empleadosUrl = 'http://localhost:9000/asesores';  

  constructor(private http: HttpClient) { }

  getEmpleadosLocal(): Empleado[] {
    return EMPLEADOS;
  }
  getEmpleadosLocalObs(): Observable<Empleado[]> {
    return of(EMPLEADOS);
  }

  getEmpleadosApiObs(): Observable<Empleado[]> {
    const headers = { 'Access-Control-Allow-Origin': '*'};
    return this.http.get<Empleado[]>(this.empleadosUrl, { headers });
  }

  postEmpleadosApiObs(empleado: Empleado): Observable<Empleado[]> {
    const headers = { 'Access-Control-Allow-Origin': '*'};
    return this.http.post<Empleado[]>(this.empleadosUrl, empleado, { headers });
  }



}
