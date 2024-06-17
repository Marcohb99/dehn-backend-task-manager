# Dehn Backend Task Manager

Backend coding task for Dehn. This consist of a simple task manager that allows you to perform simple
CRUD operations (create, read, update and delete) on tasks.

## Design decisions

### Language and framework

I chose Java 17 and SpringBoot as those are the language and framework mainly required for this position.
In addition, Spring provides a simple way to 

- generate shell commands using the `@ShellComponent` annotation
- autowire services
- basically test everything

### Architecture and programming paradigm

![DDD architecture](DDD.png) 
For the architecture, TDD (Test Driven Development) and DDD (Domain Driven Design) were used.
This combination is highly effective to have functional, clean and tested code in production from the start.
I also worked with git branches for each little feature and merged them to the main branch when they were working.

#### Application layer

Here there are only the use cases that receive the parameters from the infrastructure layer and call the repository layer.
They also are in charge of instantiating the domain objects and returning the results.

#### Domain layer

Here are the

- entities: Task entity that represents the task and encapsulates the data 
- value objects: smaller objects that contain some business logic
- interfaces that define the contracts that the infrastructure layer must implement
- domain exceptions that represent failures related to the business logic

#### Infrastructure layer

Here are the

- commands: shell commands that receive the parameters from the user and call the application layer
   -  they also format the output to the user
- repositories: classes that implement the interfaces defined in the domain layer
   - they are responsible for the data access and manipulation


The process of implementing the task has been as follows:

1. Create a branch for the feature
2. Start from the outside (shell commands) with a fake implementation
   1. Test the shell commands with the fake implementation and make sure they are working
   2. Merge the branch to the main branch
3. Implement the application layer (use case) moving the fake implementation there
   1. Adapt and test everything
   2. Merge the branch to the main branch
4. Implement the repository layer with the real implementation
    1. Adapt and test everything
    2. Merge the branch to the main branch

Some notes:

- Sometimes in step 3 I also sketch the repository methods because they were obvious
- Some refactors are made throughout the process
- Of course, I did not follow this pattern to the letter, but it was the general idea

### Other design decisions

- The Task class was created to represent the task entity and encapsulate the data
- The TaskStatus enum was created to represent the status of the task and better control the possible values


### Database

It was required to use a file-based database. I chose a json file as it is simple to use and parse.
I could have used libraries that work with json files, but I decided to implement it myself to control 
the auto incremental ID and also to eliminate the need for a library.

## Requirements

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Installation

To generate the jar file, run the following command:

```bash
make clean-package
```

This will generate the [executable jar file](target/dehn-backend-task-manager-0.0.1-SNAPSHOT.jar) in the `target` directory.

## How to run

### Create a task

To create a task, run the following command:

```bash
make run-create-task args="task_id task_title task_description task_due_date task_status"
```

Note: the available status are `PENDING` and `COMPLETED`.

### List all tasks

To list all tasks, run the following command:

```bash
make run-list-tasks
```

This will output all the tasks in the database with the fields tab separated.

### Update a task

To update a task, run the following command:

```bash
make run-update-task args="task_id task_title task_description task_due_date task_status"
```

Note: again, the available status are `PENDING` and `COMPLETED`.

### Delete a task

To delete a task, run the following command:

```bash
make run-delete-task args="task_id"
```

## Example successful run

```bash
make run-create-task args="Task1 Description1 2022-12-31 PENDING"
make run-create-task args="Task2 Description2 2022-12-31 PENDING"
make run-list-tasks
make run-update-task args="1 Task1 Description1 2022-12-31 COMPLETED"
make run-list-tasks
make run-delete-task args="2"
make run-list-tasks
```

## Database

The database is a json file located [here](src/main/resources/db/task-manager.json).

For parsing, I used the [simple-json library](https://code.google.com/archive/p/json-simple/)

## Testing

To run the tests, run the following command:

```bash
make test
```

## Improvements

I did what was required, but there are some improvements that could be made:

- Value objects for the task fields to ensure the data is correct
  - Example: use date type for due date
- Better exception handling from the commands and return a message to the user
- Dockerize the application
  - I have a branch for this but I did not have time to finish it as the mapping with the database file was not working
- This was not required, but adding an event bus implementation would be nice to have for when a task has been created, deleted or updated

## Author

Marco Hurtado Bandrés:

- LinkedIn: [marco-hurtado-bandres](https://www.linkedin.com/in/marco-hurtado-bandres/)
- GitHub: [marcohb99](https://github.com/marcohb99)