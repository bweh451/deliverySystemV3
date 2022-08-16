# Menu driven JDBC program for Food Delivery Management

## Table of Contents
| Sections | Links | 
| ------------- |:-------------:|
| Description | [Click Me](#description) |
| Installation | [Click Me](#installation) |   
| Usage | [Click Me](#usage) |
| Get help | [Click Me](#get-help)
| Contribution | [Click Me](#contribution) |

## Discription:
The purpose of this program was prompted by a task that I had to do for my studies. The task required me to create a food delivery management as well as a database for a food delivery company. This program will be used by them to keep track of the various orders, customers and restaurants they have. This also enables them to assign a driver to an order depending on if the driver is in the same location as the customer. The program also chooses the driver with the smallest load. This program also generated an invoice with relevant details and writes this invoice to a text file when an order is finalised. This is a console-based, menu-driven Java program, wich prompts the user to select what they want to do (eg. insert a new order, update customer details etc.) This program allows for easy and straight forward interaction with the database that I have created, by making use of database connections and statmenents that issue SQL code to the database in order to do specific tasks.

## Installation:
All you have to do is create a new project in your Java IDE and then copy all the folders and files provided by me, within the folder of the new Java project that you have created. Go back to your IDE and refresh the project folder that you have created.
When the project files show up, you can then compile and run the program.

## Usage: 
Once the program is running you can pull up your console window within you IDE there will be a little menu that you can choose from (the menu contains the actions that you can perform) and then just press the number related to the action that you want to perform. Some of the actions (like the 'update customer information' and the 'search for an order' action) will give you an additional menu that you can choose from, so you can specifically update a single piece of data. 

### Here are some examples in the Eclipse IDE:

##### Here I have chosen option 6 that leads to more prompts. Here you can choose which information you would like to display, this will show you all the information stored in the table that you have chosen. I have chosen option 3, which displays everything within the customers table.
![Display all information](/README%20Screenshots/1.PNG)

##### Here I have chosen option 4 to display all the unfinalised orders within the orders table, afterward I have chosen option 5 to finalise the order and entered the order number I would like to finalise (this automatically generated the invoice). I then chose option 4 again and as you can see there is only one order that is unfinalised as the other order has been marked a finalised.
![Search unfinalised orders and finalise and order](/README%20Screenshots/2.PNG)

##### Here you can see the invoice that has been generated after finalising the order above.
![Generated invoice after finalising an order](/README%20Screenshots/3.PNG)

##### Here I have entered 0 in order to exit the program.
![Exit the program](/README%20Screenshots/4.PNG)

### Here are screenshots of how the tables within the database have been set up for a better understanding of how the database works:

###### Customers Table
![customers-table](/README%20Screenshots/customers%20table.PNG)

###### Restaurants Table
![restaurants-table](/README%20Screenshots/restaurants%20table.PNG)

###### Orders Table
![orders-table](/README%20Screenshots/orders%20table.PNG)

###### Order Items Table
![orderItems-table](/README%20Screenshots/orderItems%20table.PNG)

##### Drivers Table
![drivers-table-description](/README%20Screenshots/drivers%20table%20description.PNG)
![drivers-table-values](/README%20Screenshots/drivers%20table%20values.PNG)
![drivers-table](/README%20Screenshots/drivers%20table.PNG)

## Get help
If you are having troubles setting up the program then feel free to DM me on discord so that we can chat about it (bweh#8859).

## Contribution
I have been the sole contributor of this project.
