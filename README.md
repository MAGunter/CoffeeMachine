# Coffee Machine Application

A web application simulating the functionality of a coffee machine, with features like drink creation, ingredient management, statistics tracking, and API integration.

## Features

1) Create and manage drinks with customizable recipes.

2) Keep track of ingredient stock levels and refill them when needed.

3) Automatically detect public holidays using the Nager.Date API.

4) Swagger UI for API documentation and testing.

5) Collect statistics and identify the most popular drink.

6) By default have 3 drinks: cappuccino, americano and espresso

## Technology Stack

Backend Framework: Spring Boot

Database: PostgreSQL

API Documentation: Swagger (Springdoc OpenAPI)

External API: Nager.Date for fetching public holidays

### Drinks Table

```sql
CREATE TABLE drink (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    recipe JSONB NOT NULL,
    status BOOLEAN DEFAULT FALSE,
    custom BOOLEAN DEFAULT FALSE,
    preparation_count INT DEFAULT 0
);
```

### Visit http://localhost:8080/swagger-ui.html to explore and test the API.

## API Documentation

### Drinks API

Endpoint	Method	Description

/api/coffee/drinks	GET	Get all available drinks.

/api/coffee/create	POST	Create a new drink with a recipe.

/api/coffee/{name}	POST	Prepare a drink by name.

/api/coffee/most-popular	GET	Get the most popular drink.

### Ingredients API

Endpoint	Method	Description

/api/ingredients	GET	Get all ingredients and their stocks.

/api/ingredients/add	POST	Add a new ingredient.

/api/ingredients/refill	PATCH	Refill an existing ingredient.

### The application uses Nager.Date to fetch public holidays for determining machine availability. The machine does not operate on weekends or public holidays.

### How It Works:

Create a custom drink

```http
POST /api/coffee/create
Content-Type: application/json

{
  "name": "Latte",
  "recipe": {
    "Coffee Beans": 10,
    "Milk": 50
  }
}
```

Response:

```json
{
  "id": 1,
  "name": "Latte",
  "recipe": {
    "Coffee Beans": 10,
    "Milk": 50
  },
  "status": false,
  "custom": true,
  "preparationCount": 0
}
```

Prepare a Drink

Request:

```http
POST /api/coffee/Cappuccino
```

Response:

preparationCount = 1 -> for every preparing drinks leads to became a most popular drink

```json
{
  "message": "Status: true"
}
```
