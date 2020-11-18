# Spring Boot REST Client

Example opinionated project for using Spring Boot as a REST client:
* Uses nested JSON object model from public APIs
* JSON-to-Object model conversion done manually clean and simple with Lombok
* Lombok @Value immutable classes used for simplicity
* Simple Spring Boot skeleton project with JUnit 5 test coverage



## REST format

Example endpoint: https://jsonplaceholder.typicode.com/users
```json
[
  {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  },
]
```

Example JSON from https://api.predic8.de:443/shop/customers/
```json
https://api.predic8.de:443/shop/customers/
Response Body
{
  "meta": {
    "count": 21,
    "limit": 10,
    "page": 1,
    "next_url": "/shop/customers/?page=2&limit=10"
  },
  "customers": [
    {
      "firstname": "Michael",
      "lastname": "Lachappele",
      "customer_url": "/shop/customers/2"
    },
    {
      "firstname": "David",
      "lastname": "Winter",
      "customer_url": "/shop/customers/7"
    },
    {
      "firstname": "Anne",
      "lastname": "Hine",
      "customer_url": "/shop/customers/102"
    },
    {
      "firstname": "Alice",
      "lastname": "Eastman",
      "customer_url": "/shop/customers/342"
    },
    {
      "firstname": "AAA1",
      "lastname": "BBB2",
      "customer_url": "/shop/customers/1"
    },
    {
      "firstname": "ajith",
      "lastname": "kumar",
      "customer_url": "/shop/customers/343"
    },
    {
      "firstname": "Jon",
      "lastname": "Doe",
      "customer_url": "/shop/customers/345"
    },
    {
      "firstname": "John",
      "lastname": "Cena",
      "customer_url": "/shop/customers/346"
    },
    {
      "firstname": "Dan",
      "lastname": "H",
      "customer_url": "/shop/customers/347"
    },
    {
      "firstname": "Dan",
      "lastname": "H",
      "customer_url": "/shop/customers/348"
    }
  ]
}
```
