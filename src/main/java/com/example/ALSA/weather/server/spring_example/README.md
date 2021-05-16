# Overall structure: 
- *Client* (class)
    - Model
    - @Entity to create JPA Entity for ORM
    - @Table(name=”the-table-name”) to mapping object with DB table (ORM)
    - The instance variable mapping to the primary key is annotated with @Id
- *ClientController* (class)
    - @RestController
    - REST API endpoint
    - Holds reference to ClientService (autowired)
- *ClientService* (class)
    - @Service (logically does the same as @Component)
    - A business logic layer
    - Bridge between controller and data access
    - Holds reference to ClientRepository (autowired), which handles database transactions. 
- *ClientRepository* (interface)
    - Extends CrudRepository<Client, Integer>  --> <Class type, primary key type>
    - The JPA hibernate actions.
    - Interface, which when instantiated as object serves the data access operations.
    - Convenient methods for common database operations, without need for SQL coding.
    - Can be configured with abstract methods for more custom database operations.

## In addtion:
*ClientNotFoundException* is used to pass custom HTTP exception response, with suitable status code
 and message, when a database search did not find the requested client.
