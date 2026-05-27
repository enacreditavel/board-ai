# Board AI

Board AI is a simple task manager that uses artificial intelligence to create new tasks from voice commands.

## 🚀 Getting Started

Follow these instructions to get a copy of the project running on your local machine for development and testing purposes.

### Prerequisites

What you need to install the software:

- Java 21
- Gradle

### Installation

A step-by-step guide that tells you how to get a development environment running:

1. Clone the repository
   ```sh
   git clone https://github.com/enacreditavel/board-ai.git
   ```
2. Navigate to the project directory
   ```sh
   cd board-ai
   ```
3. Install the dependencies
   ```sh
   mvn install
   ```
4. Run the application
   ```sh
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080`.

## 🛠️ Technologies

The following technologies were used in the construction of the project:

- [Spring Boot](https://spring.io/projects/spring-boot) - Web framework
- [Spring AI](https://spring.io/projects/spring-ai) - AI library
- [Maven](https://maven.apache.org/) - Dependency manager

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