import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Simple Tasks';
  mode: number = 1;

  changeMode(mode : number){
    this.mode = mode;
  }
}
