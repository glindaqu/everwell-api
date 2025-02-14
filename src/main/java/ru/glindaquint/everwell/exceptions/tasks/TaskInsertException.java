package ru.glindaquint.everwell.exceptions.tasks;

public class TaskInsertException extends RuntimeException {
    public TaskInsertException() {
        super("Server error when inserting task");
    }
}
