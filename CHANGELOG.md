## 1.0.0 (2024-06-17)

### BREAKING CHANGE

- first full-working version!

### Feat

- **readme**: update readme

## 0.11.0 (2024-06-17)

### Feat

- **delete-task**: add test to the command test that check the case when the task is not found
- **delete-task**: implement repository

## 0.10.0 (2024-06-17)

### Feat

- **use-case**: delete task use case implemented and tested, also adapted the command test

## 0.9.0 (2024-06-17)

### Feat

- **delete-task-command**: added a command to delete a task with a fake implementation

## 0.8.0 (2024-06-17)

### Feat

- **update-task**: implement update task repository method and added error in case the task is not found

## 0.7.0 (2024-06-17)

### Feat

- **update-task**: implement use case logic o update task

## 0.6.0 (2024-06-17)

### Feat

- **update-task-command**: add update task command and test with fake implementation

## 0.5.0 (2024-06-17)

### Feat

- **list-task**: finish implementation
- **repository**: add findAll method and adapt tests, still with dummy data
- **use-case**: fake use case implementation

## 0.4.0 (2024-06-17)

### Feat

- **list-task**: add command to list tasks and happy path test

### Fix

- adapt command adding status

## 0.3.0 (2024-06-17)

### Feat

- **task-status**: add enum with status and add it as a property to task entity

## 0.2.0 (2024-06-17)

### Feat

- **save**: implement json repository to save task

## 0.1.0 (2024-06-14)

### Feat

- **create-task-command**: add usecase and minimal implementation of json repository following DDD comventions
- **create-task-command**: add arguments to resulting string and test it
- **create-task-command**: add create command and minimal happy path test
