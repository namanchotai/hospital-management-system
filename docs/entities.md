# Entity Models

This system uses **JPA entities** to represent database tables.

All entities inherit from **BaseEntity**.

---

## BaseEntity

Common fields shared by all entities.

Fields:

* id
* createdAt
* updatedAt

Features:

* Auto-generated ID
* Automatic timestamp management

---

## Patient

Represents hospital patients.

Fields:

* id
* name
* email
* phone
* gender
* dateOfBirth
* bloodGroup

Relationships:

* OneToMany → Appointments
* OneToOne → Insurance

Business Rules:

* Email must be unique
* Age must be greater than 1 year

---

## Doctor

Represents hospital doctors.

Fields:

* id
* name
* email
* phone
* specialization

Relationships:

* OneToMany → Appointments
* ManyToMany → Departments
* OneToOne → Department (Head Doctor)

Business Rules:

* Email must be unique
* Can work in multiple departments
* Can head only one department

---

## Department

Represents hospital departments.

Fields:

* id
* name
* headDoctor

Relationships:

* ManyToMany → Doctors
* OneToOne → Head Doctor

Business Rules:

* Department name must be unique
* Only one head doctor allowed

---

## Appointment

Represents scheduled patient-doctor meetings.

Fields:

* id
* patient
* doctor
* appointmentTime
* reason
* status

Relationships:

* ManyToOne → Patient
* ManyToOne → Doctor
* OneToOne → Bill

Business Rules:

* Appointment time must be in the future
* Doctors cannot have conflicting appointments

---

## Bill

Tracks appointment payments.

Fields:

* id
* amount
* status
* appointment

Relationships:

* OneToOne → Appointment

Business Rules:

* Default amount ₹500
* Initial status PENDING

---

## Insurance

Stores patient insurance information.

Fields:

* id
* policyNumber
* provider
* validUntil

Relationships:

* OneToOne → Patient

Business Rules:

* Policy number must be unique
* One insurance per patient
