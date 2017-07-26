Feature: CreateUser
	This feature deals with the creation of a User
Scenario: Create a Record 
	Given User navigates to login page 
	And User enters the following credentials userName as "sai.prashanthgarlapati@gmail.com" and password as "P@ssw0rd" 
	And User clicks on "Login" button 
	Then Validate Whether the User logged in "sai.prashanthgarlapati@gmail.com"
	And User clicks on "Users"
	And User clicks on "Add/Edit User"
	And User clicks on "New"
	And User inputs "Username" as "Prashanth"
	And User inputs "First Name" as "Prashanth"
	And User inputs "Last Name" as "Prashanth"
	And User inputs "Email" as "Prashanth.021@gmail.com"
	And User inputs "Initial Password" as "Prashanth021@"
	And User inputs "Initial Password Confirm" as "Prashanth021@"
	And User click on checkbox of "Right to create forms"
	And User click on checkbox of "Company Admin"
	And User click on checkbox of "Right to sync from"
	And User clicks "Create User"
	Then validate the Inserted User data
	|UserName|First Name|Last Name|Email|status|Right to create forms|Company Admin|
	|Prashanth|Prashanth|Prashanth|Prashanth.021@gmail.com|ok|Yes|Yes|
	And User clicks on "Logout" button
	And User enters the following credentials userName as "Prashanth" and password as "Prashanth021@" 
	And User clicks on "Login" button 
	Then Validate Whether the User logged in "Prashanth"
	And User clicks on "Logout" button