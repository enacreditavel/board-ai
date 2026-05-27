# Board AI

Board AI is a simple task manager that uses artificial intelligence to create new tasks from voice commands.

## API

The API allows you to create tasks in two ways:

### 1. Create a task via JSON

- **Endpoint:** `POST /api/tasks`
- **Description:** Creates a new task.
- **Request Body:**
  ```json
  {
    "description": "Description of my new task"
  }
  ```
- **Response:**
  ```json
  {
    "id": "c3f6f3f1-a4f9-4b4f-8a7a-9e6f9e6f9e6f",
    "description": "Description of my new task",
    "status": "TO_DO",
    "createdAt": "2026-05-27"
  }
  ```

### 2. Create a task with AI (via audio)

- **Endpoint:** `POST /api/tasks/ai`
- **Description:** Creates a new task using an audio file as input. The API will transcribe the audio, create the task, and return an audio file with the response.
- **Request:**
  - `file`: The audio file to be transcribed (`multipart/form-data` format).
- **Response:**
  - An audio file (`audio/mp3`) with the response.