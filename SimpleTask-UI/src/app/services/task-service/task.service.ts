import { Task } from './../../models/Task';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
private hostUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getTasks() {
    return this.http.get(this.hostUrl + 'getTasks');
  }

  addTask(description: string){
    const task: Task = new Task();
    task.taskDescription = description;
    task.done = false;
    this.http.post(this.hostUrl + 'addTask', task);
  }
}
