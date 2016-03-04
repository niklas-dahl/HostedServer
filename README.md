# HostedServer - The Server Side of Opportunity


It provides all users and executes all commands that a user can make.

<h2>Guide:</h2>

<h4> DAOs: </h4>
    - DAOs should be used to insert new entries into tables
    - all DAOs are currently in the package com.dbegnis.tables.daos
    - to add a new entry first make a new object of your DAO class and then call the create method
    - create() returns a boolean, true for sucsess, false for an error

<h4> Createing a New Command: </h4>
    - create a java class with the following pattern in package com.dbegnis.base.command
    - extend from BaseCommand from the package com.dbegnis.base.command
    - BaseCommand expects an integer rightsgroup, this is the rightsgroup a user needs to execute this command
      (you'll lern more about RightsGroups later)
    - add your command to the command manger in the class com.dbegnis.base.BaseServer in the setupCommands() method
    
<h4> Right Groups: </h4>
    - currently there are five existing right groups
    - from zero to four where four is the highest
    - zero means the client hasn't authorise himself yet so the only commands with zeo should be login or register
    - one represent normal users, two insn't declared yet (maybe premium or something)
    - users with rights group three are admins and users with four are system-admins (which shouldn't be normal users)
    

    
  

