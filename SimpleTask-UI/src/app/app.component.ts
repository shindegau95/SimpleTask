import { TaskService } from './services/task-service/task.service';
import { Task } from './models/Task';
import { Component, OnInit } from '@angular/core';

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
    const refreshTasks = setInterval(() => this.refreshTasks(), 2000);
  }

  changeMode(mode: number) {
    this.mode = mode;
  }


  refreshTasks() {
    this.taskService.getTasks()
      .subscribe(response => {
        this.tasks = response;
      });
  }
}
