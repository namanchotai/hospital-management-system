# Database Configuration

The system uses **PostgreSQL** as the primary database.

Database Name:

```
hms_db
```

---

## application.yaml

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hms_db
    username: postgres
    password: password

  jpa:
    hibernate:
      ddl-auto: update
```

---

## Database Constraints

Patient

* Unique email

Doctor

* Unique email

Department

* Unique department name

Insurance

* Unique policy number

Bill

* One bill per appointment

---

## Relationships

Patient → Appointment
(OneToMany)

Doctor → Appointment
(OneToMany)

Doctor ↔ Department
(ManyToMany)

Patient ↔ Insurance
(OneToOne)

Appointment ↔ Bill
(OneToOne)

---

## Validation Rules

Patient

* Email must be valid
* Date of birth must be past

Doctor

* Email must be unique
* Specialization required

Appointment

* Appointment time must be future

Insurance

* Validity date must be present or future
