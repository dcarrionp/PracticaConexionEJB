import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

export interface Persona {
  dni: string;
  nombre: string;
  apellido: string;
  edad: number;
}

@Injectable({
  providedIn: 'root',
})
export class PersonaService {
  private readonly BASE_URL = 'http://54.86.3.241/server2/api/persona';

  constructor(private http: HttpClient) {}

  // Crear una persona
  crearpersona(data: Persona): Observable<Persona> {
    return this.http
      .post<Persona>(this.BASE_URL, data)
      .pipe(catchError(this.handleError)); // Manejar errores
  }

  // Obtener todas las personas
  obtenerpersonas(): Observable<Persona[]> {
    return this.http
      .get<Persona[]>(this.BASE_URL)
      .pipe(catchError(this.handleError)); // Manejar errores
  }

  // Manejo de errores HTTP
  private handleError(error: any): Observable<never> {
    let errorMessage = 'Ocurrió un error desconocido';
    if (error.error instanceof ErrorEvent) {
      // Error en el cliente
      errorMessage = `Error en el cliente: ${error.error.message}`;
    } else {
      // Error en el servidor
      errorMessage = `Error en el servidor: ${error.status} - ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage)); // Retorna el error para manejarlo en el componente
  }
}
