### List All Member Information
* Basic Info
  ```Bash
  Request Path: /member/list
  Request Method: GET
  Interface Description: retrieve all members information
* Request Parameter
  * N/A
* Response Data
  * Format: application/json
  
    | Parameter        | Type    | Necessary (Y/N) | Note                             |
    |------------------|---------|-----------------|----------------------------------|
    | code             | number  | Y               | response code, 1 success, 0 fail |
    | msg              | string  | N               | prompt                           |
    | data             | object  | N               | returned data                    |
    | ├─ userID        | number  | Y               |                                  |
    | ├─ firstName     | string  | Y               |                                  |
    | ├─ middleName    | string  | N               |                                  |
    | ├─ lastName      | string  | Y               |                                  |
    | ├─ preferredName | string  | N               |                                  |
    | ├─ email         | string  | Y               |                                  |
    | ├─ cellphone     | string  | Y               |                                  |
    | ├─ gender        | enum    | Y               |                                  |
    | ├─ memberSince   | date    | Y               |                                  |
    | └─ remainingCredit | long  | Y               |                                  |

* Sample Response Data
  ```Bash
  {
    "code": 1,
    "msg": "success",
    "data": [
        {
            "userId": 2,
            "firstName": "Jane",
            "middleName": "Marie",
            "lastName": "Smith",
            "preferredName": "Janie",
            "email": "jane.smith@email.com",
            "cellPhone": "2345678901",
            "gender": "Female",
            "birthday": "1990-02-02T06:00:00.000+00:00",
            "memberSince": "2023-07-01T00:18:36",
            "remainingCredit": 200
        },
        {
            "userId": 3,
            "firstName": "Alex",
            "middleName": null,
            "lastName": "Johnson",
            "preferredName": null,
            "email": "alex.johnson@email.com",
            "cellPhone": "3456789012",
            "gender": "Others",
            "birthday": null,
            "memberSince": "2023-07-01T00:18:36",
            "remainingCredit": 300
        }
    ]
  }


### Delete By UserID
* Basic Info
  ```Bash
  Request Path: /member/{id}
  Request Method: DEL
  Interface Description: delete member information using userID
* Request Parameter
    * userId
* Response Data
    * Format: application/json
    * | Parameter          | Type       | Necessary (Y/N) | Note                             |
      |--------------------|------------|-----------------|----------------------------------|
      | code               | number     | Y               | response code, 1 success, 0 fail |
      | msg                | string     | N               | prompt                           |
      | data               | object [ ] | N               | returned data                    |
      
* Sample Response Data
  ```Bash
  {
    "code": 1,
    "msg": "success",
    "data": null
  }

### Add a Member
* Basic Info
  ```Bash
  Request Path: /member/add
  Request Method: POST
  Interface Description: add a member 
* Request Parameter Sample
  ```Bash
  {
    "firstName": "John",
    "middleName": "K.",
    "lastName": "Doe",
    "preferredName": "Johnny",
    "email": "john.doe@example.com",
    "cellPhone": "+123456789",
    "gender": "Male",
    "birthday": "1990-01-01",
    "remainingCredit": 100
  }

* Response Data
    * Format: application/json
    * | Parameter          | Type       | Necessary (Y/N) | Note                             |
      |--------------------|------------|-----------------|----------------------------------|
      | code               | number     | Y               | response code, 1 success, 0 fail |
      | msg                | string     | N               | prompt                           |
      | data               | object [ ] | N               | returned data                    |

* Sample Response Data
  ```Bash
  {
    "code": 1,
    "msg": "success",
    "data": null
  }
  
### Change Member Information
* Basic Info
  ```Bash
  Request Path: /member/update/{id}
  Request Method: PUT
  Interface Description: change member information
    
* Request Parameter Sample
  ```Bash
  {
    "firstName": "david",
    "middleName": "null",
    "lastName": "johnson",
    "preferredName": "DV",
    "email": "d.jhnson@company.com",
    "cellPhone": "1234567890",
    "gender": "Male",
    "birthday": "1990-01-01",
    "remainingCredit": 1000
  }
* Response Data
    * Format: application/json
    * | Parameter          | Type       | Necessary (Y/N) | Note                             |
            |--------------------|------------|-----------------|----------------------------------|
      | code               | number     | Y               | response code, 1 success, 0 fail |
      | msg                | string     | N               | prompt                           |
      | data               | object [ ] | N               | returned data                    |

* Sample Response Data
  ```Bash
  {
    "code": 1,
    "msg": "success",
    "data": null
  }