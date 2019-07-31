# TMT_Mobile
A Mobile Application for Attendance recording using Web Server, Database, and NFC sensor.
## Contents
- [Changelogs](https://github.com/708817/TMT_Mobile#changelogs)
- [Checklist](https://github.com/708817/TMT_Mobile#checklist)
- [Versioning](https://github.com/708817/TMT_Mobile#versioning)
## Changelogs
###### vNEW
- Renamed MDActivity.java to CoursesActivity.java
- Renamed activity_md.xml to activity_courses.xml
- Updated comments in AttendanceActivity, LoginActivity, CoursesActivity, and SectionsActivity
- Added "Remember Me" feature in LoginActivity.java (lines 60-70 and 100-108)
- Updated LoginActivity UI (activity_login.xml)
###### v0.5a
- Added comment to AsyncResponse.java
###### v0.5
- Added AsyncResponse.java
- Removed WSTestWebServices.java
- Renamed WSTestDatabase.java to WSTestServices.java
- Modified WSTestServices to co-function with AsyncResponse (lines 19, and 63-66)
- Modified MainActivity (47-77), LoginActivity (41-78), MDActivity (45-82), and SectionsActivity (47-87) to co-function with AsyncResponse
- Modified WSLogin (20, 83-86), WSRetrieveInfo (20, 73-76), and WSRetrieveSection (20, 81-84) to co-function with AsyncResponse
- Bug-Fixed #1: Retrieving WS variable from a WS class only returns unchanged value. This is due to AsyncTask being slow af for changing the variable before being retrieved.
###### v0.4
- Added WSTestDatabase.java and WSTestWebService.java
- Renamed AttendanceMode.java to AttendanceActivity.java
- Modified AttendanceActivity.java (lines 53-66)
- Modified MainActivity.java to co-function with other classes (lines 19-20, 85-94, and 98-107)
- Added comments on WS activities
###### v0.3
- Initial SectionsActivity UI prototype released
- Initial ClassActivity UI prototype released
- Initial AttendanceMode UI prototype released (Note: Class name will be refactored on next patch)
- Added WSRetrieveSection.java and WSSetAttendance.java
- Modified MDActivity to co-function with added activities (MDActivity.java, lines 69-76)
###### v0.2
- Initial LoginActivity UI prototype released
- Initial MDActivity UI prototype released
- Added WSLogin.java and WSRetrieveInfo.java 
###### v0.1
- Initial MainActivity UI prototype released
## Checklist
- [ ] MainActivity UI
- [ ] MainActivity Functionality
- [x] LoginActivity UI
- [ ] LoginActivity Functionality
- [ ] CoursesActivity UI
- [ ] CoursesActivity Functionality
- [ ] SectionsActivity UI
- [ ] SectionsActivity Functionality
- [ ] ClassActivity UI
- [ ] ClassActivity Functionality
## Versioning
Guide when committing the repository:
1. Added and Changed codes/variables inside an existing method, (v0.1 -> v0.1a -> v0.1b ...)
2. Added new method, (v0.1 -> v0.11 -> v0.12 ...)
3. Added new class, (v0.1 -> v0.2 -> v0.3 ...)
4. Final Release or Re-release, (v1.0 -> v2.0 -> v3.0 ...)
