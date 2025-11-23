
It contains total 8 REST APIs. Adding the details below - 


Get Users 
``URL : http://localhost:8080/user``

Create User
``URL : http://localhost:8080/user ``
<br>
Sample Requst Payload
```
{
    "username":"sreetama1230",
    "displayName":"sreetama"
}
```
Get Habits of a User
``URL : http://localhost:8080/habits?user={userId}``

Create Habit
``URL : http://localhost:8080/habits/user/{userId}``
<br>
Sample Requst Payload
```
{
    "title":"tv",
    "description":"daily 1hr",
    "category":{
        "name":"entertainment"
    },
    "tags":[
        {
            "name":"relax"
        }
    ]
}

```
Mark Habit
``URL : http://localhost:8080/habits/mark?user={userId}&habit={habitId}``
<br>
Sample Requst Payload 
```
{
    "note":"done"
}
```
Update Habit (PUT) - Add new tags and remove the exisiting one
``URL : http://localhost:8080/habits/{habitId}?user={userId}``
<br>
Sample Requst Payload
```
{
    "title":"tv/gaming time",
    "description":"daily 1hr",
    "category":{
        "name":"entertainment"
    },
    "tags":[
        {
            "name":"mytime"
        }
    ]
}
```
Update Habit (PATCH) -  Add tags with existing ones
``URL : http://localhost:8080/habits/{habitId}?user={userId}``
<br>
Sample Requst Payload
```
{

    "category":{
        "name":"entertainment"
    },
    "tags":[
        {
            "name":"rest"
        }
    ]
}

```
Delete Habit : 
``URL : http://localhost:8080/habits/{habitId}/users/{userId}``

