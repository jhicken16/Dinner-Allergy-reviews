# Dinning Review Api

> Version Java 17. Spring frame work 3.2.5<br>
> To Run ./mvnw sping-boot:run.

#### OverView
>This is a Rest API using CRUD. It has three controllers AppUser, Restaurant and Reviews. Allowing a user to Create, Read, Update and Delete Respectively. 

## Restaurant Controller

Uses the Restaurant Entity. To access User created restaurants in the database
###### Schema
> | Id = Long | <br>
> | reviews = Review.class (OneToMany) | <br>
> | name = String | <br>
> | PeanutScore = Float | <br>
> | eggScore = Float | <br>
> | dairyScore = Float |

>## Create Routes
>>**POST**  
>>URL = localhost:8080/Restaurants/create
>>Request Body
>>```JSON
>>      {
>>        "name": "{Restaurant Name}"  
>>      }
>>```
>>##### Params
>>Name = String 
>
>## Read Routes
>>**GET**  
>>URL = localhost:8080/Restaurants <br>
>>>Request Body <br>
>>>```
>>>      Null
>>>``` 
>>**Returns** all Restaurant entities in database.
>>```JSON
>>     [
>>          {
>>              "id": 1,
>>              "reviews": [
>>               {
>>                "id": 1,
>>                "restaurant_id": 1,
>>                "comments": "Review from user 1.",
>>                "scores": {
>>                    "eggScore": 3,
>>                    "peanutScore": 2,
>>                    "dairyScore": 5
>>                }
>>            },
>>            {
>>                "id": 3,
>>                "restaurant_id": 1,
>>                "comments": "review from user 2",
>>                "scores": {
>>                    "eggScore": 2,
>>                    "peanutScore": 2,
>>                    "dairyScore": 2
>>                }
>>            }
>>        ],
>>        "name": "res 1",
>>        "scores": {}
>>        }
>>      ]
>>```
>>**GET**  
>>URL = localhost:8080/Restaurants/{id}  
>>>Request Body
>>>```
>>> NULL  
>>>```
>>Gets User by Id Of type LONG. **Returns**<br>>
>>```JSON
>>        {    
>>            "id": 2,  
>>            "reviews": [  
>>                {  
>>                    "id": 2,  
>>                    "restaurant_id": 2,  
>>                    "comments": "review from user 1.",  
>>                    "scores": {  
>>                        "eggScore": 4,  
>>                        "peanutScore": 1,  
>>                        "dairyScore": 1  
>>                    }  
>>                },  
>>                {  
>>                    "id": 4,  
>>                    "restaurant_id": 2,  
>>                    "comments": "review from user 2",  
>>                    "scores": {  
>>                        "eggScore": 1,  
>>                        "peanutScore": 5  
>>                    }  
>>                }  
>>            ],  
>>            "name": "res 2",  
>>            "scores": {}  
>>        }
>>```  
>
>## Update Routes
>**Put**  
>URL = localhost:8080/Restaurants/{Restaurant_Id}
>>Request Body
>>```JSON  
>>    {
>>       "name": "{New Name}"
>>    }
>>```
>>#### Params  
>>Name = String
>
>**Returns**  
>The new Restaurant entity.
>```JSON  
>        {    
>            "id": 2,  
>            "reviews": [  
>                {  
>                    "id": 2,  
>                    "restaurant_id": 2,  
>                    "comments": "review from user 1.",  
>                    "scores": {  
>                        "eggScore": 4,  
>                        "peanutScore": 1,  
>                        "dairyScore": 1  
>                    }  
>                },  
>                {  
>                    "id": 4,  
>                    "restaurant_id": 2,  
>                    "comments": "review from user 2",  
>                    "scores": {  
>                        "eggScore": 1,  
>                        "peanutScore": 5  
>                    }  
>                }  
>            ],  
>            "name": "{New Name}",  
>            "scores": {}  
>        } 
>```
>## Delete  Routes
>**Delete**  
>URL = localhost:8080/Restaurants/Delete
>>Request Body
>>```JSON  
>>    {
>>       "id": "{Restaurant_id}"
>>    }
>>```
>>#### Params  
>>id = Long
>
>**Returns**  
>The Deleted Restaurant entity. Deletes from Restaurant entity will cascade to Review Entity
>```JSON  
>        {    
>            "id": 2,  
>            "reviews": [  
>                {  
>                    "id": 2,  
>                    "restaurant_id": 2,  
>                    "comments": "review from user 1.",  
>                    "scores": {  
>                        "eggScore": 4,  
>                        "peanutScore": 1,  
>                        "dairyScore": 1  
>                    }  
>                },  
>                {  
>                    "id": 4,  
>                    "restaurant_id": 2,  
>                    "comments": "review from user 2",  
>                    "scores": {  
>                        "eggScore": 1,  
>                        "peanutScore": 5  
>                    }  
>                }  
>            ],  
>            "name": "{New Name}",  
>            "scores": {}  
>        } 
>```



## AppUser Controller

Uses the AppUser Entity. To access User created users in the database
###### Schema
> | Id = Long | <br>
> | userName = String| <br>
> | city = String| <br>
> | county = String| <br>
> | postCode = String| <br>
> | PeanutScore = Boolean | <br>
> | eggScore = Boolean | <br>
> | dairyScore = Boolean |

>## Create Routes
>### **Post**  
>URL = localhost:8080/user/Create
>>Request Body
>>```JSON  
>>    {
>>       "userName": "User_1",
>>       "city": "Your_city",
>>       "county": "Your_County",
>>       "postCode": "TY20 2QA",
>>       "peanutAllergy": false,
>>       "eggAllergy": false,
>>       "dairyAllergy": false
>>    }
>>```
>>#### Params  
>>userName = String  
>>city = String  
>>county = String  
>>postCode = String (UK post codes only)  
>>peanutAllergy = Boolean 
>>eggAllergy = Boolean  
>>dairyAllergy = Boolean 
>
>**Returns**  
>The new AppUser entity
>```JSON  
>   {
>    "id": 1,
>    "userName": "User_1",
>    "city": "London",
>    "county": "London",
>    "postCode": "LD90 1QD",
>    "peanutAllergy": true,
>    "eggAllergy": false,
>    "dairyAllergy": false
>   }
>```
>
>## Read Routes
>### **GET** 
>URL = localhost:8080/user?name={user-name}
>>Request Body
>>```JSON  
>>    Null
>>```
>>#### Params  
>>userName = String  
>
>**Returns**  
>The AppUser entity with matching user name.
>```JSON  
>   {
>    "id": 1,
>    "userName": "User_1",
>    "city": "London",
>    "county": "London",
>    "postCode": "LD90 1QD",
>    "peanutAllergy": true,
>    "eggAllergy": false,
>    "dairyAllergy": false
>   }
>```
>## Update Routes
>### **PUT** 
>URL = localhost:8080/user/{user_id}
>>Request Body
>>
>>At least one Body parameter needs to be provided
>>```JSON  
>>   {
>>    "userName": "User_1", //<OPTIONAL>
>>    "city": "London", //<OPTIONAL>
>>    "county": "London", //<OPTIONAL>
>>    "postCode": "LD90 1QD", //<OPTIONAL>
>>    "peanutAllergy": true, //<OPTIONAL>
>>    "eggAllergy": false, //<OPTIONAL>
>>    "dairyAllergy": false //<OPTIONAL>
>>   }
>>```
>>### Params 
>>>#### Path Params
>>>id = Long (Must be Provided) 
>>
>>>#### Body Params 
>>>userName = String (OPTIONAL) 
>>>city = String  (OPTIONAL)
>>>county = String  (OPTIONAL)
>>>postCode = String (UK post codes only) (OPTIONAL)  
>>>peanutAllergy = Boolean (OPTIONAL)
>>>eggAllergy = Boolean  (OPTIONAL)
>>>dairyAllergy = Boolean  (OPTIONAL)
>
>**Returns**  
>The updated AppUser entity.
>```JSON  
>   {
>    "id": 1,
>    "userName": "User_1",
>    "city": "London",
>    "county": "London",
>    "postCode": "LD90 1QD",
>    "peanutAllergy": true,
>    "eggAllergy": false,
>    "dairyAllergy": false
>   }
>```
>## Delete Routes
>### **Delete** 
>URL = localhost:8080/user/{user_id}
>>Request Body
>>
>>At least one Body parameter needs to be provided
>>```JSON  
>>   {
>>    "userName": "User_1", //<OPTIONAL>
>>    "city": "London", //<OPTIONAL>
>>    "county": "London", //<OPTIONAL>
>>    "postCode": "LD90 1QD", //<OPTIONAL>
>>    "peanutAllergy": true, //<OPTIONAL>
>>    "eggAllergy": false, //<OPTIONAL>
>>    "dairyAllergy": false //<OPTIONAL>
>>   }
>>```
>>### Params 
>>>#### Path Params
>>>id = Long (Must be Provided) 
>>
>>>#### Body Params 
>>>userName = String (OPTIONAL) 
>>>city = String  (OPTIONAL)
>>>county = String  (OPTIONAL)
>>>postCode = String (UK post codes only) (OPTIONAL)  
>>>peanutAllergy = Boolean (OPTIONAL)
>>>eggAllergy = Boolean  (OPTIONAL)
>>>dairyAllergy = Boolean  (OPTIONAL)
>
>**Returns**  
>The updated AppUser entity.
>```JSON  
>   {
>    "id": 1,
>    "userName": "User_1",
>    "city": "London",
>    "county": "London",
>    "postCode": "LD90 1QD",
>    "peanutAllergy": true,
>    "eggAllergy": false,
>    "dairyAllergy": false
>   }
>```


