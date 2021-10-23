# NWGTest
RESTful API for transfers between accounts.

# TODO List
- [x] Create this repository
- [x] Create YAML file to define JSON service interface
- [x] Create project in STS 4
- [x] Develop code
- [x] Create JUnit tests
- [x] Test and add few comments

# Tools used
* STS v.4.7.2
* Java SE v17.0.1
* SpringBoot v2.5.6
  * Spring Boot Web
  * Spring Boot DevTools
  * Spring Boot Actuators
* GitHub
* SwaggerHub

# API Definition
The API definition is described as an OPEN API 3.0 YAML file.

To see the formatted API click https://app.swaggerhub.com/apis/NWG/TransferService/1.0.0#/default/GetBalance
To see design decision explanations download PPT https://github.com/HaroldoMacedo/NWGTest/blob/main/TransferService%20Design%20Decisions.pptx

```YAML
openapi: 3.0.0

servers:
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: https://virtserver.swaggerhub.com/NWG/TransferService/1.0.0

info:
  description: This is the NWG TransferService test API
  version: "1.0.0"
  title: TransferService
  contact:
    email: haroldo.macedo@gmail.com

paths:
  /current-account/{accountNumber}:
    get:
      summary: Gets the balance for the user's current account
      description: Gets the balance for the user's current account
      operationId: GetBalance
      parameters: 
        - name: accountNumber
          in: path
          required: true
          description: Current account number
          schema:
            $ref: '#/components/schemas/AccountNumber'
      responses:
        '200':
          description: Operation executed.
          content:
            application/json:
              schema:
                allOf:
                  - $ref: '#/components/schemas/Balance'
                  - $ref: '#/components/schemas/Error'

  /current-account/{accountNumber}/transfer:
    post:
      summary: Transfer money to another account
      description: Transfer values from the user's current account to another account
      operationId: TransferMoney
      parameters: 
        - name: accountNumber
          in: path
          required: true
          description: Current account number
          schema:
            $ref: '#/components/schemas/AccountNumber'
      responses:
        '200':
          description: Operation not executed.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Error'
        '201':
          description: Operation executed.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Balance'
      requestBody:
        description: Destination account number and amount to transfer
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Transfer'

components:
  schemas:
    AccountNumber: 
      type: string
      description: Account number, sort code and number.
      format: ^[0-9]{2}\.[0-9]{2}\.[0-9]{2} [0-9]{1,6}$
      example: '12.34.56 123456'

    Balance:
      type: object
      required:
        - balance
      properties:
        balance:
          $ref: '#/components/schemas/Amount'
        available:
          $ref: '#/components/schemas/Amount'
        limit:
          $ref: '#/components/schemas/Amount'

    Transfer:
      type: object
      required:
        - destinationAccountNumber
        - destinationFullName
        - transferAmount
      properties:
        destinationAccountNumber:
          $ref: '#/components/schemas/AccountNumber'
        destinationFullName:
          type: string
          example: John Dole
        description:
          type: string
          example: From Mr X.
        transferAmount:
          $ref: '#/components/schemas/Amount'

    Amount:
      type: number
      format: ^-?[0-9]{1,6}\.[0-9]{2}$
      example: 123.45

    Error:
      type: object
      required:
        - errorCode
        - errorDescription
      properties:
        errorCode:
          type: integer
        errorDescription:
          type: string

```
# What to do
## Java Case Study
Design and implement a RESTful API for doing transfers between accounts. That should include data models and service implementation.
### Minimum model definition
* Account
  * Account number
  * Balance
* Transaction
  * Source account number
  * Destination account number
  * Amount
### Requirements
* You are required to provide an implementation of a TransferService API. The service API should accept as input source account number, destination account number and the amount. It returns the appropriate response.
* Data should run in-memory
* You can use any framework you fancy, but please stick to Java8+ as the programming language
* Authentication is not required for this case study
* Use tests to prove that the endpoints work as expected
* Ensure that code is always failsafe
### We expect
* This exercise should take you between 2 and 3 hours maximum.
* We are interested in code quality, readability, structure and use of best practices and principles.
* Simple implementation and to the point
* A repo with your code on BitBucket or GitHub along with Readme file.


