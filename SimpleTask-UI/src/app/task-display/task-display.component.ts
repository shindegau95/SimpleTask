import { Task } from './../models/Task';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'task-display',
  templateUrl: './task-display.component.html',
  styleUrls: ['./task-display.component.scss']
})
export class TaskDisplayComponent implements OnInit {
  @Input() task: Task;

  constructor() { }

  ngOnInit() {
  }

}
