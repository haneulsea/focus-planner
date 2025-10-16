import type { Task } from "../tasks/Task.ts";

export interface Planner {
    id: number;
    title: string;
    tasks: Task[];
}
