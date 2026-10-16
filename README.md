# Chemist Management System

This is a backend API I built for running a pharmacy. It handles drug inventory and sales, and each pharmacy only sees its own stock. It's written in Java with Spring Boot and uses MongoDB for storage and JWT for login.

I built it to practice designing a proper REST API end to end: authentication, business rules, error handling, tests, and deployment.

## What it does

- Register and log in. Passwords are hashed with BCrypt and need at least 8 characters, one uppercase letter and one digit.
- When you register you give a pharmacy name. If that pharmacy already exists (case doesn't matter) you join it, otherwise it gets created.
- Add, update, search, view and delete drugs. Every drug belongs to a store, and you only ever see your own store's drugs.
- Dispense drugs. You send a list of drugs and quantities, and it checks the drug exists, the dosage matches and there's enough stock. It then reduces the stock, works out the total and saves a sales record.
- Errors come back as JSON with a sensible status code instead of a stack trace.

## Built with

Java 17, Spring Boot (Web, Security, Data MongoDB), MongoDB, JWT (jjwt), Lombok, JUnit, Maven and Docker.

## How the code is laid out

```
src/main/java/ng/Chemist/
├── controllers/     # Auth, DrugManagement and Sales endpoints
├── service/         # business logic
├── Data/
│   ├── model/       # User, Store, Drug, DispensedDrugsRecord
│   └── repositories/
├── dtos/            # request and response objects
├── security/        # JWT service, auth filter, security config, exception handler
├── exceptions/      # custom exceptions
├── config/          # MongoDB config
└── util/            # password checks and mapping helpers
```

Tests are in `src/test`, split into `repositoryTest` and `serviceTest`.

## Running it

You need JDK 17, Maven, and MongoDB running locally (or an Atlas connection string).

```bash
git clone https://github.com/BIGDUKE004/ChemistProject.git
cd ChemistProject
mvn spring-boot:run
```

It starts on `http://localhost:8080`. These environment variables are optional:

| Variable | What it's for | Default |
|---|---|---|
| `MONGODB_URI` | Mongo connection string | `mongodb://localhost:27017/chemist` |
| `JWT_SECRET` | secret used to sign tokens | a dev-only fallback |
| `PORT` | server port | `8080` |

Set your own `JWT_SECRET` if you deploy this anywhere. You can make one with `openssl rand -base64 32`.

To run the tests, use `mvn test`. They talk to a real MongoDB, so have it running first.

### Docker

```bash
docker build -t chemist-api .
docker run -p 8080:8080 -e MONGODB_URI="<your uri>" -e JWT_SECRET="<your secret>" chemist-api
```

## Endpoints

Everything except the three auth endpoints needs a token in the header: `Authorization: Bearer <token>`. You get the token (`jwtId`) from login.

**Auth**

| Method | Path | |
|---|---|---|
| POST | `/Authorization/Register` | create account + pharmacy |
| POST | `/Authorization/Login` | returns the JWT |
| POST | `/Authorization/Logout` | log out |

Register body:
```json
{
  "userName": "jdoe",
  "passWord": "StrongPass1",
  "fullName": "John Doe",
  "storeName": "Sunrise Pharmacy"
}
```

**Drugs**

| Method | Path | Input |
|---|---|---|
| POST | `/DrugManagement/AddDrug` | JSON body |
| POST | `/DrugManagement/UpdateDrug` | JSON body |
| GET | `/DrugManagement/GetAllDrugs` | none |
| GET | `/DrugManagement/ViewDrugDetails` | `?brandName=` |
| GET | `/DrugManagement/SearchDrug` | `?id=` or `?genericName=` |
| GET | `/DrugManagement/GetAmountOfDrugs` | none |
| DELETE | `/DrugManagement/DeleteDrug` | `?brandName=` |
| DELETE | `/DrugManagement/DeleteAllDrug` | JSON body with `option` |

Add drug body:
```json
{
  "id": 1,
  "brandName": "Panadol",
  "genericName": "Paracetamol",
  "strength": "500mg",
  "dosage": "Tablet",
  "manufacturer": "GSK",
  "batchNumber": "B-2026-001",
  "manufactureDate": "2026-01-15",
  "expiryDate": "2028-01-15",
  "price": 500,
  "quantityInStock": 100
}
```

**Sales**

| Method | Path | |
|---|---|---|
| POST | `/Sales/dispenseDrug` | dispense drugs and save the sale |

```json
{
  "name": "jdoe",
  "drugs": [
    { "id": 1, "drugName": "Panadol", "dosage": "Tablet", "batchId": "B-2026-001", "quantity": 2 }
  ]
}
```

The user has to be logged in. The response is the saved sales record with the date, items, total quantity and total amount.

**Errors** look like this:
```json
{ "error": "Not enough stock for Panadol" }
```
I use 400 for bad input or not enough stock, 401 for wrong login or not logged in, 404 when a drug or record isn't found, and 500 for anything unexpected.

## CORS

Allowed origins are set in `SecurityConfig`: `http://localhost:5500` and any `*.vercel.app` site. Change these to match your own frontend.

## Still to do

- Add `@Valid` request validation on the DTOs
- Take the user from the JWT in the sales endpoint instead of the request body
- Pagination for the drug list
- Low-stock and expiry alerts
- Sales history and reports
- Swagger docs

## Author

Elijah Miracle, [@BIGDUKE004](https://github.com/BIGDUKE004)
