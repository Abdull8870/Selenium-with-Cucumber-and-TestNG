Feature: Leaftaps delete lead functionality
#Author : Abdul

Scenario Outline: TC005_Delete lead

Given Enter the username as <username>
And Enter the Password as <password>
When Click on the Login
Then Click on the crmsfa link
Then Click on leads tab
Then Click on find lead button
Then Find the lead which is created recently and navigate to the view lead page
Then Click on delete button

Examples:
|username|password|
|Demosalesmanager|crmsfa|
