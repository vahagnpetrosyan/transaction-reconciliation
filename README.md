# Transaction Reconciliation APP  

## Intro  
The project has been built using Java 8 and Spring Boot MVC with Thymeleaf templating.  


## Deployment  
he project is deployed using [Docker](https://www.docker.com/) and [Spotify Docker maven plugin](https://github.com/spotify/docker-maven-plugin) on [Heroku](https://heroku.com). 
You can try the demo [here](https://vahagn-app-tr.herokuapp.com/) ( This may take a bit longer for entering first time ).

## Building the application  
```
$ mvn clean install
```  

## Running the application  

```
$ mvn spring-boot:run
```  

After the successful run you can open [http://localhost:8080](http://localhost:8080) and use the app.  
Chrome is the recommended browser to use.  

## Application Usage  

When you open `http://localhost:8080` or [Heroku deploy](https://vahagn-app-tr.herokuapp.com/)
you can see home page loaded where you can upload 2 csv files and press `Submit` button. Once the processing is finished, you can see two
tables containing the reconciliation result summary which will include the following:  
* Number of total records in file 1
* Number of total records in file 2
* Number of matching records
* Number of similar records ( not perfectly matched )
* Number of unmatched records in file 1
* Number of unmatched records in file 2  

If there are unmatched items the button `Unmatched items` will be shown and once you click on
it, there will be displayed two tables in the bottom.  

These two tables represent the records for each above files that failed reconciliation. The tables columns will contain only essential info related to the transaction
(in my case they are Transaction date, Transaction reference and Transaction amount).  

## Main components and architecture  

The entire business logic is implemented in `Service part`, which is divided to two main flows, which are `Parsing flow` and `Handling flow`  

### Service  
**ReconciliationService**: Accepts two files names, loads them using `FileService`  gives the responsibility to `CsvTransactionFileParser`
, after parsing it gives the responsibility to `TransactionsHandler` to handle the parsed result.  

### Parser
**CsvTransactionFileParser**: accepts a `*.csv` file and parses into a list of `Transaction` model.  

### Handler
Here the Chain of responsibility pattern is applied. There are three handlers (this can be extended), which
are doing their specific jobs and forwarding the data to the next handler.  

**IdenticalTransactionsHandler**: accepts two Hashsets of `Transaction` model making sure we are working only with unique
transactions. This handler removes identical transaction in Hashset level (which relies on hashcode and equals implementation) from the Hashsets.
So it removes the intersection from two Hashsets.  

**SimilarTransactionsHandler**: accepts two Hashsets, which may contain not perfectly matched transactions and totally unmatched transaction.  
This handler filters not perfectly matched transactions (from now **similar** transactions) using below algorithm.  
The algorithm iterates over the two Hashsets and for each transaction from the first Hashset tries to find maximum in common fields 
transaction from the second Hashset. If the number of common field is greater than 50% of number of all fields, these two 
transaction are considered as similar transactions.  

**FinalHandler**: accepts Hashsets already containing fully unmatched items and sums up the final result.  

Each handler puts its calculation to the ReconciliationResult singleton object, which is considered as final dto object for the frontend.  


* **Vahagn Petrosyan**