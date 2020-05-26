import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TaskInputComponent } from './task-input/task-input.component';
import { HttpClientModule } from '@angular/common/http';
import { TaskDisplayComponent } from './task-display/task-display.component';
@NgModule({
  declarations: [
    AppComponent,
    TaskInputComponent,
    TaskDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
