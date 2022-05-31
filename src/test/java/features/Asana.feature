Feature: Asana Test

  @sanity
  Scenario:  Creating a new Asana project
     Given   Preparing data for creating a new post
      When   Creating new post
      Then   User can see the response with the project details

  @sanity
  Scenario:  Getting a new created project
     Given   Preparing data for a GET request
      When   Trying to get new created post
      Then   User successfully got the right project

  @sanity
  Scenario: Updating already existing post
     Given  Preparing data for a PUT request
      When  Updating a post on a name value
      Then  User see that the "/name" on the post is changed
            |name       |
            |Bruce Wayne|
  @sanity
  Scenario:  Deleting previously created post
     Given   Preparing data for a DELETE request
      When   Deleting previously created post
      Then   Post is correctly deleted

  @sanity
  Scenario:  Verification of deleting post
     Given   Preparing data for a GET request
      When   Trying to get new created post
      Then   Can not get a post because it's already deleted
