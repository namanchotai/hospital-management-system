# Business Logic Flow

## Patient Lifecycle

Create Patient

* Validate email uniqueness
* Validate patient age
* Save patient

Update Patient

* Check patient exists
* Update information

Delete Patient

* Allowed only if no appointments exist

---

## Doctor Management

Create Doctor

* Validate email uniqueness
* Assign specialization

Department Assignment

* Add doctor to department
* Assign head doctor

Delete Doctor

* Remove doctor from departments

---

## Appointment Scheduling

Create Appointment

Steps:

1. Validate patient exists
2. Validate doctor exists
3. Ensure appointment time is future
4. Check doctor schedule conflict
5. Create appointment

System automatically:

* Creates bill
* Sets status SCHEDULED

---

## Appointment Status Flow

```
SCHEDULED → COMPLETED
SCHEDULED → CANCELLED
```

Completed appointments cannot be cancelled.

---

## Billing Logic

When appointment is created:

* Bill generated automatically
* Amount = ₹500
* Status = PENDING

Payment processing:

```
PENDING → PAID
```

Revenue report:

* Sum of all PAID bills

---

## Insurance Logic

Create Insurance

* Policy number must be unique
* Patient must not already have insurance

Update Insurance

* Update provider
* Update validity

Delete Insurance

* Unlink from patient
* Remove insurance record
