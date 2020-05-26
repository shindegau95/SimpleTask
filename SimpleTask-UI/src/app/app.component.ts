import { TaskService } from './services/task-service/task.service';
import { Task } from './models/Task';
import { Component, OnInit } from '@angular/core';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Simple Tasks';
  mode: number = 1;
  tasks: any;

  constructor(private taskService: TaskService) {

  }

  ngOnInit(): void {
    this.refreshTasks();
  }

  changeMode(mode: number) {
    this.mode = mode;
  }

  getTasksByMode(): Task[] {
    if (this.mode === 1) {
      return this.tasks;
    } else if (this.mode === 2){
      return this.tasks.filter(t => !t.done);
    } else if (this.mode === 3){
      return this.tasks.filter(t => t.done);
    }
  }

  refreshTasks() {
    this.taskService.getTasks()
      .subscribe(response => {
        this.tasks = response.entity;
      });
  }
}
