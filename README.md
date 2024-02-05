# HealthBridge-API

Welcome to the HealthBridge API! This API is specifically tailored for healthcare systems, allowing users to seamlessly store, modify, and access critical information regarding healthcare professionals and patient appointments. With Spring Boot, this project provides a robust framework for building RESTful services in Java.

## Features

### 1. Healthcare Professionals and Patients Management
- **Add New Professionals and Patients:** Easily add doctors, dentists, or patients to the database.
- **Update Information:** Modify existing professional or personal information with ease, ensuring accuracy and up-to-date data.
- **Retrieve Profiles:** Access detailed profiles of healthcare professionals or patients, including specialties and contact information.

### 2. Appointment Scheduling
- **Efficient Scheduling:** Schedule appointments seamlessly, considering the availability of both healthcare professionals and patients.
- **Validation Checks:** Perform multiple validations to ensure that appointments are scheduled on valid dates and times, preventing conflicts and errors.
- **Modification and Cancellation:** Easily modify or cancel appointments when necessary, with proper validation checks to maintain system integrity.


# Endpoints

### Dental Specialties:
- **Delete - Specialty:** DELETE -> /api/dental-specialties/{specialty name}
- **Register - Dental Specialty:** POST -> /api/dental-specialties
- **Get - Specialties:** GET -> /api/dental-specialties

### Dentist:
- **Get - Dentist Details:** GET -> /api/dentist/{dentist id}
- **Get - All Dentists:** GET -> /api/dentist
- **Register - Dentist:** POST -> /api/dentist
- **Delete - Dentist:** DELETE -> /api/dentist/{dentist id}
- **Update - Dentist:** PATCH -> /api/dentist/{dentist id}

### Medical Specialties:
- **Delete - Specialty:** DELETE -> /api/medical-specialties/{specialty name}
- **Register - Medical Specialty:** POST -> /api/medical-specialties
- **Get - Specialties:** GET -> /api/medical-specialties

### Doctor:
- **Get - Doctor Details:** GET -> /api/doctor/{doctor id}
- **Get - All Doctors:** GET -> /api/doctor
- **Register - Doctor:** POST -> /api/doctor
- **Delete - Doctor:** DELETE -> /api/doctor/{doctor id}
- **Update - Doctor:** PATCH -> /api/doctor/{doctor id}

### Patient:
- **Get - Patient Details:** GET -> /api/patient/{patient id}
- **Get - All Patients:** GET -> /api/patient
- **Register - Patient:** POST -> /api/patient
- **Delete - Patient:** DELETE -> /api/patient/{patient id}
- **Update - Patient:** PATCH -> /api/patient/{patient id}

### Appointment:
- **Create - Dentist Appointment:** POST -> /api/appointment/dentist
- **Create With Random Dentist - Dentist Appointment:** POST -> /api/appointment/dentist
- **Cancel - Dentist Appointment:** DELETE -> /api/appointment/dentist/{appointment id}
- **Create - Doctor Appointment:** POST -> /api/appointment/doctor
- **Create With Random Doctor - Doctor Appointment:** POST -> /api/appointment/doctor
- **Cancel - Doctor Appointment:** DELETE -> /api/appointment/doctor/{appointment id}

## Auth
This API employs dual authentication mechanisms: JWT and API KEY. The token JWT authentication is utilized for all endpoints except for login and user registration, where API KEY authentication is employed. The API KEY necessary for login and user registration can be located in the application.properties file. All API KEY endpoints have /key/ as part of the URL.

### Auth and User:
- **Register - User:** POST -> /api/key/user
- **Update - User:** PATCH -> /api/user/{user id}
- **Delete - User:** DELETE -> /api/user/{user id}
- **Login - With valid user:** POST -> /api/key/login

## Support
For any questions or assistance regarding the Healthcare System API, or if you need the collection to test it with Postman, please contact me at gustavo.rutiquewiski@outlook.com.

Thank you for choosing the HealthBridge API. We hope it serves your needs effectively and contributes to the improvement of healthcare delivery.
