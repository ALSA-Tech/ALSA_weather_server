# Overall structure: 
- *Client* (class)
    - Model
    - Used for automatic object mapping with HTTP bodies (JSON) and DB tables.
    - @Entity to create JPA Entity for ORM.
    - @Table(name=”the-table-name”) to mapping object with DB table (ORM)
    - @Id to mark the instance variable mapping to the primary key in DB. 
- *ClientController* (class)
    - Client endpoint
    - @RestController (Spring bean)
    - REST API endpoint
    - Holds reference to ClientService (autowired)
- *ClientService* (class)
    - @Service (Spring bean - logically does the same as @Component)
    - A business logic layer
    - Bridge between controller and data access
    - Holds reference to ClientRepository (autowired), which handles database transactions. 
- *ClientRepository* (interface)
    - Data access layer (DB)
    - Extends CrudRepository<Client, Integer>  --> <Class type, primary key type>
    - The JPA hibernate actions.
    - Interface, which when instantiated as object serves the data access operations.
    - Convenient methods for common database operations, without need for SQL coding.
    - Can be configured with abstract methods for more custom database operations.

## In addtion:
*ClientNotFoundException* is used to pass custom HTTP exception response, with suitable status code
 and message, when a database search did not find the requested client.
 
# HTTP request bodies

Root URI: `/api/example/clients`

### POST `/register`
```json
{
  "name": "Olof",
  "password": "XXXXX"
}
```
> All fields except ID

**NOTE:** ID will be generated using auto increment in db. The returned client object will include the new ID.
There is no harm in passing a random ID (like -1), as it will be ignored.

### POST `/update`
```json
{
  "id": 5,
  "name": "Olof",
  "password": "XXXXX"
}
```
> All fields including ID

### DELETE `/delete`
Empty HTTP body. ID is passed as path parameter in URL.
