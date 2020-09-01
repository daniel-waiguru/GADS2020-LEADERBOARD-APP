# GADS2020-LEADERBOARD-APP - Work in Progress :wrench: :hammer:
This app interacts with GADS 2020 api and displays a list of Top 20 Learners in Learning Leaders' category and Top 20 Learners in the Skill IQ Leaders' category  

### Key concepts covered
* Android Networking 
* MVVM Architecture
* DI


I have used the following components in development
* ViewModel to manage UI's data in a lifecycle concious way
* LiveData to handle data in lifecyle-aware manner helping to reduce memory leaks and ensure the UI is always up to date with the data

## User interface
#### Launch/Splash Screen

<image src="snapshots/splash.png" height="600px" />

##### Top 20 learners and Top 20 skill IQ leaders 
Top 20 learning and Top 20 skill IQ leaders screens populated with data respectively


<image src="snapshots/learners1.png" height="600px" />    <image src="snapshots/iq1.png" height="600px" />

#### Project Submit screen


<image src="snapshots/submit1.png" height="600px" />

#### Network Error and Server Error
No internet screen  and server response error screens respectively 


<image src="snapshots/not_internet.png" height="600px" />   <image src="snapshots/error.png" height="600px" />

#### Additional Features - uncomplete
* Offline First - add Room to support offline caching pf data
* Synchronize data :repeat: - add WorkManager to sysnchronize data with remote data
