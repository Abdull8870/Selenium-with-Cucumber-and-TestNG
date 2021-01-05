Feature: File uploading with the help of AutoIT
#Author : Abdul

#1
Scenario Outline: Sample scenario for uploading file using AutoIt

Given Navigate to the website <WebSite> and upload the file <fileName>

Examples:
|WebSite|fileName|
|https://altoconvertpdftojpg.com/|fileupload|
