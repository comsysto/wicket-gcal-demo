#Wicket GCal Demo

Wicket GCal Demo demonstrates how to integrate Google Calendar into a Wicket application. Authorization is based on Spring OAuth 2. For more details please see the [introductory blog post](http://blog.comsysto.com/2013/05/13/integrating-google-calendar-into-a-wicket-application/).

##Prerequisites

* Java 7
* Gradle 1.4

If you want to try the application you also need a Google account.

##Getting Started

1. Grab the source code
2. Register an application using [Google's API console](https://code.google.com/apis/console#access) as described in the [blog post](http://blog.comsysto.com/2013/05/13/integrating-google-calendar-into-a-wicket-application/)
3. Provide client id and client secret in ui/src/main/resources/com/github/gcaldemo/application.properties
4. Run `gradle runApp`
5. Open http://localhost:8080

##License

Wicket GCal Demo is distributed under the terms of the [Apache Software Foundation license, version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).