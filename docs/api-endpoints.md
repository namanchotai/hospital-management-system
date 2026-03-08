# API Endpoints

Base URL

```
http://localhost:8080
```

---

# Patient APIs

POST /patients
Create a new patient

GET /patients
Retrieve all patients

GET /patients/{id}
Retrieve patient by ID

PUT /patients/{id}
Update patient

DELETE /patients/{id}
Delete patient

---

# Doctor APIs

POST /doctors
Create doctor

GET /doctors
Get all doctors

GET /doctors/{id}
Get doctor by ID

GET /doctors/specialization/{specialization}
Get doctors by specialization

PUT /doctors/{id}
Update doctor

DELETE /doctors/{id}
Delete doctor

---

# Department APIs

POST /departments
Create department

GET /departments
Get all departments

PUT /departments/{id}
Update department

DELETE /departments/{id}
Delete department

PUT /departments/{id}/head-doctor/{doctorId}
Assign head doctor

POST /departments/{id}/doctors/{doctorId}
Add doctor to department

DELETE /departments/{id}/doctors/{doctorId}
Remove doctor from department

---

# Appointment APIs

POST /appointments
Create appointment

GET /appointments
Get all appointments

GET /appointments/{id}
Get appointment by ID

GET /appointments/doctor/{doctorId}
Get doctor appointments

GET /appointments/patient/{patientId}
Get patient appointments

PUT /appointments/{id}/status
Update appointment status

DELETE /appointments/{id}
Cancel appointment

---

# Bill APIs

GET /bills/{id}
Get bill

GET /bills/appointment/{appointmentId}
Get bill by appointment

PUT /bills/{id}/payment
Process payment

GET /bills/status/{status}
Filter bills

GET /bills/revenue-report
Get revenue report

---

# Insurance APIs

POST /insurances
Create insurance

GET /insurances/{id}
Get insurance

GET /insurances/valid
Get valid insurances

GET /insurances/policy/{policyNumber}
Lookup by policy

PUT /insurances/{id}
Update insurance

DELETE /insurances/{id}
Delete insurance
