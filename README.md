# 72Reviews
* A Restaurant Review App by Group 72

## Introduction:
72Reviews is an application that provides services including:
  * Search restaurant based on your preference  
  * Register your user account as a Customer or a Restaurant Owner
  * Manage your restaurants as a Restaurant Owner
  * Give feedback to restaurants in the form of reviews
  * Report any inappropriate reviews 


  ## Features:
* ***User Login in/Register Service***
* ***User Profile Service***
  *  User is able to upgrade their User to an OwnerUser
  *  User is able to view their previous reviews and the average stars of those reviews
* ***Restaurant Creation & Management***
  * User is able to create a new Restaurant profile to promote their Restaurant and its:
    * Name
    * Location
    * Cuisine
    * Price Range
    * Average Stars
  * Edit your Restaurant as you improve and change your style
  * Delete your Restaurant if you are moving
* ***Sort/Search System***
  * User is able to search, filter and sort (by attribute and direction) for their personalized list. 
  * They can filter by:
    * Name (Search Input)
    * Location (Search Input)
    * Cuisine (Drop-Down Menu)
    * Price Range (Drop-Down Menu)
    * Rating/AvgStars (Drop-Down Menu)
    * Sort by:
     * Name (Radio Button)
     * Price (Radio Button)
     * Rating (Radio Button)
    * Sort Direction:
     * Ascending (Radio Button)
     * Descending (Radio Button)
  * User can submit their selections by clicking the search button.
 

* ***Review System***
  * Users can write reviews, selecting 1-5 stars for the restaurant and giving some information about their visit
  * Users edit reviews they have written, changing the number of stars and text
  * Users can like reviews, unless they own the restaurant the review is for
  * Users can delete their own reviews
  * OwnerUsers who own a restaurant may reply to reviews on that resataurant
  * Reviews are stored in a csv file and each review's id is added to the appropriate section in the user and restaurant databases

* ***Report System***
  * User is able to report a review by clicking the button beside the review
  * User can add reason when doing report
  * All Users are able to send a report except ones being banned
  * Same user cannot report one review twice
  * Review receiving a certain amount of reports will be turned to invisible
  * User receiving a certain amount of reports will be banned
  * All reports will be saved in a database

## Instruction:
1. Run app.java under /main/java
2. Register an account
NOTE: You now have access to the menu bar for Back and Home buttons
3. Search and filter for your desired restaurants
4. Click on restaurants that interest you to view their page
5. Add/Delete/Edit a review if you wish, or like or report published reviews
6. Click on Profile Screen to Access Restaurant Owner Tools (Create and view restaurants)


## Demo (ScreenShots):
1. **Welcome Page**:
![welcomeScreen](https://user-images.githubusercontent.com/70244801/206623304-8bdcc6f8-9196-4fd9-ba72-a79a8108cc17.png)

2. **Home Page**:

<img width="953" alt="Screenshot 2022-12-07 at 3 56 12 PM" src="https://user-images.githubusercontent.com/58833849/206293913-22e21257-5363-460d-8ee8-292f71875f23.png">

3. **Sorted Restaurants**:

<img width="623" alt="Screenshot 2022-12-07 at 3 57 17 PM" src="https://user-images.githubusercontent.com/58833849/206294057-8356b948-44a3-412d-a3a4-34c9ed48189c.png">

4. **Review Page**:
![image](https://user-images.githubusercontent.com/113073294/206622250-c4fd986c-032e-4a2d-94d2-81cc73a113f0.png)

![image](https://user-images.githubusercontent.com/113073294/206622287-a84bfd03-2241-4ed2-bd98-20a84c29bc62.png)

![image](https://user-images.githubusercontent.com/113073294/206621888-bebc9ed5-ffef-40e4-899b-25de553b558e.png)

![image](https://user-images.githubusercontent.com/113073294/206621974-e5adf4c9-afe2-42a4-a5c4-77f048449cad.png)

![image](https://user-images.githubusercontent.com/113073294/206622166-9db4da91-0d15-43cd-ac3d-6de29c660d29.png)

![image](https://user-images.githubusercontent.com/113073294/206622363-253ba8f1-e9e5-4808-a05a-142aedd66983.png)

![image](https://user-images.githubusercontent.com/113073294/206622376-122d1fc5-ca99-4549-9f7f-5caa97027410.png)

![image](https://user-images.githubusercontent.com/113073294/206622391-64728b2f-4bf3-44e7-99ce-2e58e110cd5f.png)

![image](https://user-images.githubusercontent.com/113073294/206622443-4a7e3393-bf53-47bc-b480-a3318a70cbe1.png)

![image](https://user-images.githubusercontent.com/113073294/206622460-5d822639-d40b-4d61-8cb2-48d7447a5b2a.png)


5. **Report Page**:

<img width="543" alt="Screen Shot 2022-12-07 at 3 52 28 PM" src="https://user-images.githubusercontent.com/70244801/206293560-aa731992-4133-4911-8597-f5d7535158da.png"> 

6. **Restaurant Modification Page**:

![image](https://user-images.githubusercontent.com/113074010/206616424-80bf4b31-fe4d-444b-a3d3-28d733f3ea5d.png)
![image](https://user-images.githubusercontent.com/113074010/206293041-8f32ba6d-209f-4ad8-922d-8d95262570a9.png)
![image](https://user-images.githubusercontent.com/113074010/206293157-502fab53-87f4-42d9-bf1a-27a76a0cb494.png)


7. **Profile Page**:
![image](https://user-images.githubusercontent.com/113149401/206623868-1710f23e-3bad-44ab-b9cb-8dded65adba1.png)
![image](https://user-images.githubusercontent.com/113149401/206623902-d7e31963-9888-4b1f-8476-0ff90b0c474c.png)
![image](https://user-images.githubusercontent.com/113149401/206623923-4e3ff790-608e-4491-9195-3481a6b51c43.png)


## Issues accomplished:
* [Completed Issues](https://github.com/CSC207-2022F-UofT/course-project-group-72/issues?q=is%3Aissue+is%3Aclosed)

[Back to top](#readme)
## Test Coverage:
* Register and Login System:
* Search Restaurant System:
* Restaurant Creation and Management: 100% class coverage, 88% method coverage, 81% line coverage (Not including non-automated view tests)
* Review System: 23% class coverage, 37% method coverage, 20% line coverage
* Report System: 100% class coverage, 100% method coverage, 75% line coverage (Not including non-automated view tests, rest of codes were 95% covered)
* User Profile: 

## Libraries Used:
* `Java swing`
* `JUnit`


## Checklist
- [x] Verify the correct settings for your project repository
- [x] Set up Github Projects
- [x] Create the implementation plan using issues and Github Projects
- [x] Create development branches for your features
- [x] Use pull requests to merge finished features into main branch
- [x] Conduct code reviews

  
[Back to top](#readme)
