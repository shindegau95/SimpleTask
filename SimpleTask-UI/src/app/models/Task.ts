export class Task {
    id: number;
    taskDescription: string;
    done: boolean;

    constructor(id?: number, taskDescription?: string, done?: boolean) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.done = done;
    }
}