Rest API created with the Spring Boot framework and Java 17.

Prerequisites:
-	Install IntelliJ (or another Java IDE)
-	Download Java JDK compatible with Java 17. https://www.oracle.com/uk/java/technologies/downloads/#jdk20-windows 
-	Download PostgreSQL version 15.3. 
o	Ensure pgAdmin4 and PostgeSQL options are ticked.
-	Install Postman to hit endpoints.

Instructions
Setting up local database:
-	Open pgAdmin4.
-	Right click Servers > Register >Server. 
-	Name the server and click on the connection tab. Under “Host name/address” type “localhost”. Click Save.
-	Expend the SERVER_NAME tab underneath “Servers” where SERVER_NAME is the name you gave the server above.
-	Right click Databases > Create > Database .. Name the database “librarycatalogue”. Click save.
-	Under databases, you should now see “librarycatalogue”. Double click on library catalogue > schemas > tables to expand them.
-	Right click on “Table” and click on “Query Tool”. 
-	Copy and paste the following SQL code into the query and click on the execute button that looks like a play button, or press f5.
    o	The data output should display a response that the table was added. Keep pgAdmin open whilst running the code from the IDE so we have a local database up and running to interact with.

---------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.book
(
    isbn bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author_first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    publication_year integer NOT NULL,
    author_last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_borrowed boolean NOT NULL DEFAULT false,
    CONSTRAINT book_pkey PRIMARY KEY (isbn)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;

---------------------------------------------------------------------------------------------------------

-	To view the data in the database you can right click on the table name and select View/Edit data > All Rows.
-	Whenever changes are made, you can execute this query currently displayed “SELECT * FROM public.book” again to view the changes by pressing the execute button or pressing the f5 key.
Setting up IDE:
-	Open IntelliJ, Clone https://github.com/Oconnery/LibraryCatalogue 
-	Go to File > Project Settings. Set the SDK.
-	Click on the dropdown near the top right. Go to Edit Configurations.
-	Click the + and add a Spring Boot configuration. It may already automatically have been added. 
-	Under “build and run” ensure your java sdk is selected, next to the classpath com.library.catalogue.LibraryCatalogueApplication
-	Click on the Modify Options dropdown > Environment variables
-	Add three variables here using the + button: 
-	Name, value
    o	SPRING_DATASOURCE_PASSWORD , 
    o	SPRING_DATASOURCE_USERNAME , postgres
    o	SPRING_DATASOURCE_URL , jdbc:postgresql:///postgres
    o	The value of username is the username you created whilst creating the database server. It should be set to postgres by default. I f you are unsure go to pgAdmin, right click Servers > properties > connection tab and you can see the username there.
    o	The value of password is the password you created whilst creating the database. If you did not make a password then you can leave this blank. It is also in the properties of the server on pgAdmin.
-	Click “apply” and then click “ok”.
-	Click on the run button to the top right or use shortcut shift+f10 to run the application. It should start.
-	Now, we can hit the endpoints in the controller using postma. The paths to hit and the data required are described in the BookController class.
    o	example file path is as follows:
    o	http://localhost:8080/api/library/books/add/

Using Postman:
-	Select get/post/delete/patch according to the controller endpoint method you want to hit.
-	In the uri text box, type http://localhost:8080/ followed by the uri of the endpoint you want to hit, e.g. type http://localhost:8080/api/library/books/publication-year/between.
-	If a pathvariable is required then supply it 
-	If a body is required then
