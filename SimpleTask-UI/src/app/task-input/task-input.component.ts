import { TaskService } from './../services/task-service/task.service';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'task-input',
  templateUrl: './task-input.component.html',
  styleUrls: ['./task-input.component.scss']
})
export class TaskInputComponent implements OnInit {
  private taskInput: string;

  constructor(private taskService: TaskService) { }

  ngOnInit() {
  }

  addTask(){
    this.taskService.addTask(this.taskInput);
  }

}
