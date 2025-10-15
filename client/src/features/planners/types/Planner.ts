import type { Task } from "../../tasks/types/Task.ts";

export interface Planner {
    id: number;
    title: string;
    tasks: Task[];
}