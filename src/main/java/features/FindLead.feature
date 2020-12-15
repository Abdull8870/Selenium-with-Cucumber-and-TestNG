Feature: Leaftaps delete lead functionality
#Author : Abdul

Scenario Outline: TC006_Find lead with lead ID

Given Enter the username as <username>
And Enter the Password as <password>
When Click on the Login
Then Click on the crmsfa link
Then Click on leads tab
Then Click on find lead button
Then Find the lead with lead id <leadId>


Examples:
|username|password|leadId|
|Demosalesmanager|crmsfa|10365|
