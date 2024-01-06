# attendance

A system which equipped with authentication and authorisation system that store the details of attendance of employees/students in MySQL database.This system is implemented basic CRUD system and file upload system.

- [x] Registration
  - Details (JSON)
  - Encrypted password
  - Provide JWT key
  - Role-Based Access Control 
- [x] Login
  - Username and password (String Parameter) Noted: It is impratical to authenticate such method but I meant to do so.
  - Provide JWT key
- [x] Attend
  - Store file (evidence of attendance) with date (Multipart/form-data)
  - Needs to JWT key to attend (Header)
- [ ]  Update attendance
- [ ]  Delete attendance
- [ ]  Delete user (admin role)
