# Forward Inc.

## Team Members

| Name | Student Number |
| --- | --- |
| Patrik Kelemen | 300059871 |
| Nolan Belnap | 300059878 |
| Angelica Paynter | 300062844 |
| Zhen Wang | 300057304 |
| Mathieu Bellefeuille | 6501369 |


### Add CircleCi here?


## Deliverable 1

The purpose of this deliverable was to: 

- create our git repo and assign tasks to all team members
- have all teammates commit at least once
- create a UML class diagram of our domain model
- have an admin account with the username: admin and the password: 5T5ptQ
- be able to create any number of patient and employee accounts
- be able to login on any valid account and have a welcome message display: 
```
Welcome [firstname]!
You are logged in as [role].
```
- validate all fields in both the login screen and the register screen
- passwords are stored as SHA-256
- Bonus: use a database (we used SQLite)

## Deliverable 2

The purpose of this deliverable was to:

- update our UML diagram to reflact changes to the classes
- submit a working [APK](https://github.com/professor-forward/project-lab01-variable/tree/master/app/p1-master/app/build/outputs/apk/debug/)
- create 5 Unit tests for our app (not testing instrumentation or Espresso UI testing)
- admin can create services
- admin can remove services
- admin can edit services
- all fields are validated with error messages (implemented as toasts)
- Bonus: integrate CircleCi

- Additional: Admin can delete Patient and Employee accounts
- Note: Database was changed to Firebase from SQLite in Deliverable 1, google-services.json is not tracked by GitHub for security purposes
- There is one Admin account. The login is username: admin, password: 5T5ptQ
