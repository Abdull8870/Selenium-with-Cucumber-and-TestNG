Feature: Leaftaps create lead functionality
#Author : Abdul

Scenario Outline: TC005_Create Account

Given Enter the username as <username>
And Enter the Password as <password>
When Click on the Login
Then Click on the crmsfa link
Then Click on Accounts tab
Then Click on create account button
Then Enter the Account details <accountname> <currencytype> <industrytype> <phonenumber> <emailId> 
Then Click on Create Account

Examples:
|username|password|accountname|currencytype|industrytype|phonenumber|emailId|
|Demosalesmanager|crmsfa|Accenture ae|AMD|Insurance|8890708059|Ab@testleaf.com|


