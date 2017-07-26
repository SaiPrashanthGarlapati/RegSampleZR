Feature: CreateRecord 
	This feature deals with the creation of a Record

Scenario: Create a Record 
	Given User navigates to login page 
	And User enters the following credentials userName as "sai.prashanthgarlapati@gmail.com" and password as "P@ssw0rd" 
	And User clicks on "Login" button 
	Then Validate Whether the User logged in "sai.prashanthgarlapati@gmail.com"
	And User clicks on "Hello World"
	And User clicks on "List"
	And User clicks on "Create New Record"
	And User inputs "First Name" as "Sai"
	And User inputs "Last Name" as "Prashanth"
	And User inputs "phone" as "4698266724"
	And User clicks on "Save"
	Then Validate record with data in recordtable
		|First Name|LastName|phone|
		|Sai|Prashanth|4698266724|
	And User clicks on "Logout" button
	