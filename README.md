# flywithus

Backend API for managing flight 

user can search for flight 

user can make a reservations for flight

user can pay for flight by reservation secret code

user can cancel flight by reservation secret code 

## API :

GET /api/flight - get all flights [ search by fields will be done at frontend part ]

POST /api/user - save user 
```
{
  "email" : "XXX@gmail.com"
  "password" : "XXXXX"
}
```

POST api/reservation - save resevation for flight
```
{
  "flightId" : "1",
  "email" : "XXX@gmail.com
}
```

POST api/reservation/{reservationSercretCode} - cancel reservation for flight


POST api/reservation/pay/{reservationSercretCode} - pay reservation  [ payment gateway will be opened soon ]
```
{
  "cardNumber" : "2222222222222222",
  "name" : "John Doe",
  "cvv" : "293"
  "expirationDate" : "05/2023",
}
```

### troubleshooting

To have test data uncomment generation data test in FlyWithUsApplication

To run all tests execute mvn test 
