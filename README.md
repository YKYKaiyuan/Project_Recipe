# Recipe Manager Application

## An application that tracks saved recipes

**Main functions**
- Let users save their recipes.
- Categorize recipes by different cuisine(Asian, Vegan, American, and etc).
- Categorize recipes by different ingredient(seafood-free, peanut-free, and etc).
- Provide user a way to track their used recipes.

*Potential functions countinue to work on after course*
- Directly pull recipes from websites and automatically transform into a local saved data.
- Make the method above apply for popular recipes websites.
- And maybe more ..........

**Breif introduction of this project**

This project is designed to offer a more covenient way to save recipes and track saved recipe just like how modern 
music streaming app does. For example I can save a lot of recipes into this program, and when I need to use one of 
those. I can do a direct search for that. In the circumustances when I do not know what exactly I need to cook, 
I can go into any interested catogory and just check what recipes I have saved in that catogory, 
and then I (might be able to) make the decision.

##User Stories

- As a user, I want to be able to add a recipe to my collection
- As a user, I want to be able to view the list of recipes in the collection.
- As a user, I want to be able to view the details of a chosen recipe.
- As a user, I want to be able to search recipes in my collection by using their category and returns all 
results that apply.
- As a user, I want to be able to search recipes in my collection by using preferences or allergies and 
returns all results that apply.

- As a user, I want to be able to save the recipes I added.
- As a user, I want to be able to load the recipes I added before.
- As a user, I want the app to ask me if I want to save changes if I was trying to quit but forgot to save the changes.

##Instructions for Grader

- To fulfill the first required events, you need to fill in the blanks. Notice that rate REQUIRES to be an integer, 
for other text fields system would catch errors if you put nothing in those mandatory fields.
- To fulfill the second required event, you need to have recipes already added yourself. Then you can click the view 
recipe button. A new dialog window should shows up, and you would be able to use the delete button to delete the recipe
 on the same row. However, I found a bug afterward that occurs when I am trying to delete a recipe from loaded file and
 I found that something would go wrong if I want to save the list, since I am running out of time, I modified it to be
 only available for deleting recipes that had not been saved to file before, and a warning dialog would shows up when
you when you are tring to delete the recipe loaded from file. I think this modification still fulfill the requirement.
- To fulfill the visual component requirements. You can either leave the first and the third (count from top to bottom) 
textfield empty, or fill in all the blanks and give an integer other than 0-5 to the second textfield. Then the 
exceptions would be caught and a dialog window would show up with a build-in sound effect to tell you what did you do 
wrong, and an arrow image will point towards the textfield where the exception was caught.
Notice that those exceptions are caught by priority, which means if you put nothing in the textfields, 
than the wrong number exception for the second textfield would not be caught.
- To fulfill the save/load requirements. You just need to press the save and load button on the right side of 
the main frame. All data goes into / loads from "recipe" file in data package.

##Phase 4: Task 2
- Option: Test and design a class that is robust. Class involve exception: model.Recipe, methods: setName, giveRate, 
and setCategory.

##Phase 4: Task 3
- The first problem I found is a coupling issue occurs in ui.Main.getRecipeName and ui.Main.getSavedNames. Both methods
 use the same for loop and this is a code duplication which violate the coupling rule. So I improved this by extracting 
 them to make a new helper method.
- The second problem I found is also a coupling issue. I found that in dialog classes(FeedBackDialog and WarningDialog),
 I used method overload a lot so there are many code duplications, fo example, for all dialog they need to be set to 
 visible and for most of them, they are not resizeable. So I made an abstract class and let both dialog classes extends
  this new Dialog class instead of JDialog, such that I can delete a lot of those code duplicates.
- The third problem is a large cohesion issue. The problem is that I put all gui stuff in the MainFrame class, which is 
ok for now, but when the class getting larger, it can be really messy. By this point, I am not able to finish it, I 
tried to fix it, but there are no many things to change that I do not think I can complete it in time, I can still
 write down my thoughts here. My plan is to make a new rendering class where I need to extract all those layout sentences 
 in those methods of MainFrame class, and put them into the new rendering class. So that the rendering class would 
 responsible for the layout of all components in the user interface, and the mainframe will only need to initialize 
 everything, and handle those action events. However, I struggled with those action events because they group with 
 those layout methods, which means if I want to move layout methods into the new class, action event also have to come
 with them and this is the thing I don't want it to happen. 