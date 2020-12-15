Feature: Leaftaps create lead functionality
#Author : Abdul

Scenario Outline: TC004_Create Account

Given Enter the username as <username>
And Enter the Password as <password>
When Click on the Login
Then Click on the crmsfa link
Then Click on leads tab
Then Click on create lead button
Then Enter the lead details <companyname> <firstname> <lastname> <phonenumber> <emailId> 
Then Click on create lead

Examples:
|username|password|companyname|firstname|lastname|phonenumber|emailId|
|Demosalesmanager|crmsfa|Accenture af|Abdul|Rahuman|8890708059|Ab@testleaf.com|

