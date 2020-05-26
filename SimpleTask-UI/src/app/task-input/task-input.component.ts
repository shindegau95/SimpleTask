import { TaskService } from './../services/task-service/task.service';

import { Component, OnInit, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'task-input',
  templateUrl: './task-input.component.html',
  styleUrls: ['./task-input.component.scss']
})
export class TaskInputComponent implements OnInit {
  private taskInput: string;
  @Output() tasksAdded = new EventEmitter();

  constructor(private taskService: TaskService) { }

  ngOnInit() {
  }

  addTask() {
    this.taskService.addTask(this.taskInput)
    .subscribe(response => {
      this.taskInput = '';
      this.tasksAdded.emit('success');
    });
  }

}
