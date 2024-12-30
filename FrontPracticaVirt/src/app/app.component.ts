import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PersonaService, Persona } from './persona.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'FrontPracticaVirt';

  personas: Persona[] = [];

  nuevapersona: Persona = {
    dni: '',
    nombre: '',
    apellido: '',
    edad: 0
  };

  constructor(private servicio:PersonaService){}

  ngOnInit(){
    this.servicio.obtenerpersonas().subscribe((data:any)=>{
      this.personas = data;
    });
  }

  guardar(){
    this.servicio.crearpersona(this.nuevapersona).subscribe({
      next: (data:any)=>{
        this.personas.push(data);
        
      },
      error: (error:any)=>{
        console.log(error);
      }
      
    });
  }
}
