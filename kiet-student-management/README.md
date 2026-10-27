# KIET Deemed to be University – Spring Boot Coding Assessment
## Student Academic Management REST API

### 📌 Project Structure
```text
kiet-student-management/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── kiet/
    │   │           └── student/
    │   │               ├── StudentApplication.java
    │   │               ├── controller/
    │   │               │   └── StudentController.java
    │   │               └── model/
    │   │                   └── Student.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
            └── com/
                └── kiet/
                    └── student/
                        └── StudentApplicationTests.java
```

---

### 🚀 How to Run the Application

#### Option 1: Using IntelliJ IDEA / Eclipse / VS Code
1. Open the project folder `kiet-student-management` in your IDE.
2. Allow Maven to import dependencies from `pom.xml`.
3. Locate `src/main/java/com/kiet/student/StudentApplication.java`.
4. Right-click and choose **Run 'StudentApplication'**.

#### Option 2: Using Maven Command Line
```bash
mvn clean spring-boot:run
```

---

### 📋 Startup Console Output (Question 2 - Logger Verification)
When the application starts, you will see the following logs on the console:
```text
INFO  com.kiet.student.StudentApplication : KIET Student Management System Started
INFO  com.kiet.student.StudentApplication : Loading Student Data...
INFO  com.kiet.student.StudentApplication : Student Management REST API is Ready
```

---

### 🌐 REST API Endpoints & Testing Documentation

#### 1. Question 3.A – Get All Students
- **Method:** `GET`
- **URL:** `http://localhost:8080/students`
- **cURL:**
  ```bash
  curl -X GET http://localhost:8080/students
  ```
- **Response (200 OK):**
  ```json
  [
    {
      "studentId": 101,
      "name": "Rahul Sharma",
      "email": "rahul@kiet.edu",
      "branch": "CSE(AI & ML)",
      "year": 2,
      "cgpa": 8.65
    },
    {
      "studentId": 102,
      "name": "Priya Singh",
      "email": "priya@kiet.edu",
      "branch": "CSE",
      "year": 2,
      "cgpa": 9.1
    },
    {
      "studentId": 103,
      "name": "Aman Verma",
      "email": "aman@kiet.edu",
      "branch": "IT",
      "year": 3,
      "cgpa": 7.8
    },
    {
      "studentId": 104,
      "name": "Sneha Patel",
      "email": "sneha@kiet.edu",
      "branch": "CSE",
      "year": 2,
      "cgpa": 8.9
    },
    {
      "studentId": 105,
      "name": "Vikas Gupta",
      "email": "vikas@kiet.edu",
      "branch": "ECE",
      "year": 4,
      "cgpa": 9.25
    }
  ]
  ```

---

#### 2. Question 3.B – Get Student by ID
- **Method:** `GET`
- **URL:** `http://localhost:8080/students/101`
- **cURL:**
  ```bash
  curl -X GET http://localhost:8080/students/101
  ```
- **Response (200 OK):**
  ```json
  {
    "studentId": 101,
    "name": "Rahul Sharma",
    "email": "rahul@kiet.edu",
    "branch": "CSE(AI & ML)",
    "year": 2,
    "cgpa": 8.65
  }
  ```
- **If Not Found (e.g. ID: 999) - Response (404 Not Found):**
  ```json
  {
    "message": "Student not found"
  }
  ```

---

#### 3. Question 4 – Add New Student (POST)
- **Method:** `POST`
- **URL:** `http://localhost:8080/students`
- **Headers:** `Content-Type: application/json`
- **cURL:**
  ```bash
  curl -X POST http://localhost:8080/students \
    -H "Content-Type: application/json" \
    -d '{
      "studentId": 106,
      "name": "Amit Kumar",
      "email": "amit@kiet.edu",
      "branch": "CSE(AI)",
      "year": 2,
      "cgpa": 8.75
    }'
  ```
- **Request Body:**
  ```json
  {
    "studentId": 106,
    "name": "Amit Kumar",
    "email": "amit@kiet.edu",
    "branch": "CSE(AI)",
    "year": 2,
    "cgpa": 8.75
  }
  ```
- **Response (201 Created):**
  ```json
  {
    "studentId": 106,
    "name": "Amit Kumar",
    "email": "amit@kiet.edu",
    "branch": "CSE(AI)",
    "year": 2,
    "cgpa": 8.75
  }
  ```

---

#### 4. Question 5.A – Update Student (PUT)
- **Method:** `PUT`
- **URL:** `http://localhost:8080/students/101`
- **Headers:** `Content-Type: application/json`
- **cURL:**
  ```bash
  curl -X PUT http://localhost:8080/students/101 \
    -H "Content-Type: application/json" \
    -d '{
      "studentId": 101,
      "name": "Rahul Sharma",
      "email": "rahul@kiet.edu",
      "branch": "CSE(AI & ML)",
      "year": 3,
      "cgpa": 9.05
    }'
  ```
- **Request Body:**
  ```json
  {
    "studentId": 101,
    "name": "Rahul Sharma",
    "email": "rahul@kiet.edu",
    "branch": "CSE(AI & ML)",
    "year": 3,
    "cgpa": 9.05
  }
  ```
- **Response (200 OK):**
  ```json
  {
    "studentId": 101,
    "name": "Rahul Sharma",
    "email": "rahul@kiet.edu",
    "branch": "CSE(AI & ML)",
    "year": 3,
    "cgpa": 9.05
  }
  ```
- **If Not Found (e.g. ID: 999) - Response (404 Not Found):**
  ```json
  {
    "message": "Student not found"
  }
  ```

---

#### 5. Question 5.B – Delete Student (DELETE)
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/students/105`
- **cURL:**
  ```bash
  curl -X DELETE http://localhost:8080/students/105
  ```
- **Response (200 OK):**
  ```json
  {
    "message": "Student deleted successfully"
  }
  ```
- **If Not Found (e.g. ID: 999) - Response (404 Not Found):**
  ```json
  {
    "message": "Student not found"
  }
  ```

---

#### 6. Question 6.A – Search by Branch and Year
- **Method:** `GET`
- **URL:** `http://localhost:8080/students/search?branch=CSE&year=2`
- **cURL:**
  ```bash
  curl -X GET "http://localhost:8080/students/search?branch=CSE&year=2"
  ```
- **Response (200 OK):**
  ```json
  [
    {
      "studentId": 102,
      "name": "Priya Singh",
      "email": "priya@kiet.edu",
      "branch": "CSE",
      "year": 2,
      "cgpa": 9.1
    },
    {
      "studentId": 104,
      "name": "Sneha Patel",
      "email": "sneha@kiet.edu",
      "branch": "CSE",
      "year": 2,
      "cgpa": 8.9
    }
  ]
  ```

---

#### 7. Question 6.B – Find Students Based on CGPA (Toppers)
- **Method:** `GET`
- **URL:** `http://localhost:8080/students/topper?cgpa=9.0`
- **cURL:**
  ```bash
  curl -X GET "http://localhost:8080/students/topper?cgpa=9.0"
  ```
- **Response (200 OK):**
  ```json
  [
    {
      "studentId": 102,
      "name": "Priya Singh",
      "email": "priya@kiet.edu",
      "branch": "CSE",
      "year": 2,
      "cgpa": 9.1
    },
    {
      "studentId": 105,
      "name": "Vikas Gupta",
      "email": "vikas@kiet.edu",
      "branch": "ECE",
      "year": 4,
      "cgpa": 9.25
    }
  ]
  ```
