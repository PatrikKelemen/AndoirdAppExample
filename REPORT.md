# Final Report

#### Team Variable: Patrik Kelemen, Nolan Belnap, Angelica Paynter, Zhen Wang, Mathieu Bellefeuille
#### SEG 2105, Prof Andrew Forward
#### Due: Dec. 4, 2019

## Introduction

In this project we implemented an App for Android using Android Studio. The app developed was designed for walk-in 
clinics. The app had three different users, each with their own type of account, Admin, who runs the app,
Employee, who works at the clinics, and Patient, who visits the clinics. There is only one Admin account that is 
predefined in the database to have the username admin and the password 5T5ptQ. Both Employee and Patient accounts
can be created in the app. The set of screens and activities a user can do is determined by the type of account 
they have logged in with. An Admin can create, edit and delete services that all the clinics can choose from. They
can also delete Accounts of other users and clinics. Employees can create or join pre-existing clinics. They must 
be part of a clinic to do anything else. Once they are in a clinic they can choose services for the clinic too offer. 
They can also set their working hours and the clinics working hours. Patients can search for clinics by name, 
address, working hours, or services offered. Patients can then book an appointment and check in to that appointment. 
Once they've checked in to the appointment they can write a review for the clinic. Clinics show their appoximate 
waiting time and rating from patients. 

## UML Class Diagram

## Team Roles

|Team Member| Deliverable 1| Deliverable 2| Deliverable 3| Deliverable 4|
|---|---|---|---|---|
|Patrik Kelemen | Setup database and add to app, validate fields | Field validation| Employees can create clinics and add/remove services from clinics | Admin can delete clinics, 2 unit tests |
| Nolan Belnap | Setup database and add to app | Add, edit and remove Services by Admin| Employees can create clinics and add/remove services from clinics | 2 Unit Tests, Fixing up adding/removing services from clinics|
| Angelica Paynter | Create Account and subclasses | Admin can delete Patient and Employee accounts | Employees can view and edit both their own working hours and those of the clinic| Patients can book and check in to an appointment and rate a clinic, 2 Unit Tests, final report | 
| Zhen Wang | Create UI for Deliverable 1 (login screen and welcome screen) | Adding error messages for invalid field entries | Add 2 new Unit Tests | Make UI look pretty, 2 Unit tests|
| Mathieu Bellefeuille | Create UML diagram, store passwords in SHA-256 | Update UML diagram and add 5 Unit Tests | Update UML diagram | Integrate CircleCi, clinis show wait time and rating, Patients can search for clinics, 2 Unit Tests |

** For Deliverable 2 and onward Patrik integrated the database into other's work since he was the only one with the google-services.json file.

## App Screenshots

## Lessons Learned

In building this app we learned many things by overcoming the numerous challenges we encountered. The largest of these challenges was to 
integerate the database into the app. We had a persistant error where our app would crash as soon as we opened it 
when we originally set it up with the database. It took a lot of debugging to eventually figure out that anything that gets 
stored in the database needs an empty constructor. During this we learned where the error messages are printed 
in Android Studio and much more about integrating Firebase into Android apps. 

After we got the database working we learned that Firebase needs a 
google-services.json file to connect to the database. Git automatically ignores this when commiting for security
reasons. We could override this but we decided against it, also for security reasons. We learned that in the future we 
should make the database with a throwaway gmail account so we don't have to worry about somebody's account being 
exposed to risk. That way we could upload the google-services.json file so that everyone has access to the database.

Right near the end of developping our app we discovered how to store links between objects in the database. Throughtout
our entire app when we needed to link anything to an account or clinic we would save the name of the specific account
or clinic in the instance variables of the object. Then when we got the object from the database we would search and 
find the account or clinic that had that name. This is why our UML looks like it is missing associations. 
We couldn't figure out how to store links between objects until the very
end when it was already too late to change everything. However, we now know how to do this for our next project.

We learned the basics of using Android Studio to build Android apps and how Android apps function. We started with 
Activities and how to navigate between them while passing information. Many of the things we learned while designing 
the GUI and each of the screens were small little tricks and such. One big thing we did learn for the GUI was how to 
use RecyclerView. This was used in most of the places where we have lists of items in the app. It is a multistep process
that involves creating an adapter that holds all the smaller views that form the list, binding those views when 
they are added to the list, and creating the views themselves in a separate XML file. The process got more complicated 
if there were buttons or pop-up dialogs on each view in the list. Those required onClickListeners to be assigned and 
the use of interfaces to allow the methods to be defined in the activity class but assigned to the individual views 
and called in the adapter class. 

Among all the technical things we learned we were also able to work on our soft skills. We were able to improve our
skills for working with a team of completely unknown people. We learned that things like establishing weekly meetings 
and a single group chat will help speed up the process and allow everyone to stay on the same page. We learned how to
better coordinate our efforts for dividing up work and making sure everyone gets their parts done on time.  

All the lessons we learned will hopefully help us in future team projects. Our soft skills will be easy to transfer 
forward to new projects that aren't necessarily related to app development. The Android specific skills may only be 
useful in future app projects or similar projects where through our understanding of this we can understand the 
new app development IDE quicker. The Firebase knowledge will likely be useful in any future project which requires a database. 