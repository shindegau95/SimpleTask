import { Task } from './../../models/Task';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
private hostUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getTasks(): Observable<any> {
    return this.http.get(this.hostUrl + 'getTasks');
  }

  addTask(description: string){
    const task: Task = new Task();
    task.taskDescription = description;
    task.done = false;
    return this.http.post(this.hostUrl + 'addTask', task);
  }

  updateTask(task: Task) : Observable<any>{
    return this.http.put(this.hostUrl + 'updateTask', task);
  }

  deleteTask(task: Task) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      }),
      body: task
    };

    return this.http.delete(this.hostUrl + 'deleteTask', httpOptions);
  }
}
