Feature: Leaftaps login functionality
#Author : Abdul

#1
Scenario Outline: TC001_Positive flow

Given Enter the username as <username>
And Enter the Password as <password>
When Click on the Login
Then Demo should be displayed in HomePage

Examples:
|username|password|
|Demosalesmanager|crmsfa|

#2
Scenario Outline: TC002_Positive flow second

Given Enter the username as <username>
And Enter the Password as <password>
When Click on the Login
Then Demo should be displayed in HomePage

Examples:
|username|password|
|Demosalesmanager|crmsfa|
