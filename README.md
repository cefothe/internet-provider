# Spring Boot - Training courses
## Task description
An internet provider needs a platform which will be used by customers to pay their monthly fees.
It has three products: silver, gold and platinum. Each product has different bandwidth and monthly fee. A client can subscribe for one of those products. Every single client can be either a physical person or legal entity. On a certain date a bill appears in the platform which the client has to pay. When the payment is completed the customer must receive a document. The document type depends on customer’s type as follows the physical person receives a ticket and
the legal entity - an invoice.

### Customer
There would be two types of customer in the system. The first one is a legal entity, which contains all regarding BG law. The second one is a physical person. The difference between them is that legal entities can have more than one address and phone number.

### Administrator
Administrator is a role in the system that will manage the entity product, will be responsible to list all existing clients, and all bilings related to them.

### User stories    
1) As a physical person I need to be able to purchase exactly one product.
2) As a legal entity I need to be able to purchase more than one product.
3) As an administrator I need to be able to see all products.
4) As a client(physical or legal) in a certain time during the month I will receive a receipt from the system.
5) As a client(physical or legal) I need to be able to pay the receipt.
6) As a system if there is a not paid receipt I need to stop their product.
7) As an administrator I need to be able to see all receipts and information about billing related to them.
	

## Tasks
Regarding to the User stories and the description on the ticket you need to create following tasks:


1) Create a spring boot project with n-layer architecture
2) Configure the spring boot application to connect to database
3) Create all needed entities to cover all the requirement
4) Create a repositories related to entities
5) Create a REST controllers to handle operations
6) Add a security to entity application


Tasks 3,4,5 will be iterative based on the user stories. Because we don’t want to create an entire database and REST part at once. We want to introduce the value per iteration. 

## Technical Requirements
The system will be REST based. Authentication and authorization will be provided via JWToken. Products need to be written in Java 11+ with Spring Boot 2+.
1) https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
2) https://spring.io/projects/spring-boot
3) https://spring.io/projects/spring-data
4) https://spring.io/projects/spring-security
5) https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc


What you will learn at the end
	At the end of this course, you will be able to create fully functional products with Spring Boot and Java. You will be able to understand the user stories and apply them to the product. And last but not least, you will work with some of the most popular Spring projects. 
