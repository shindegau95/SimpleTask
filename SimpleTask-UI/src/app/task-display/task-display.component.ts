import { Task } from './../models/Task';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { TaskService } from '../services/task-service/task.service';

@Component({
  selector: 'task-display',
  templateUrl: './task-display.component.html',
  styleUrls: ['./task-display.component.scss']
})
export class TaskDisplayComponent implements OnInit {
  @Input() task: Task;
  @Output() taskUpdated = new EventEmitter();

  constructor(private taskService: TaskService) { }

  ngOnInit() {
  }

  toggleStatus() {
    const requestTask = Object.assign({}, this.task);
    requestTask.done = !this.task.done;
    this.taskService.updateTask(requestTask)
    .subscribe(response => {
      if (response.status === 'ACCEPTED'){
        this.task.done = !this.task.done;
        this.taskUpdated.emit();
      }
    });
  }

  deleteTask() {
    this.taskService.deleteTask(this.task)
    .subscribe(response => {
      this.taskUpdated.emit();
    });
  }

}
