Feature: Red Bus site automatiomn
Description: The purpose of this feature is to implement 2 given automated scenarios
    
 Scenario: Verify hotel location
 Given Open the firefox browser
    And Enter the URL Sc2
    When Hotels link is visible on the screen
    Then Search for Hotels in Mumbai
    And  Select location as Andheri
    Then Verify whether the results are getting displayed for Andheri, Mumbai Location
    And close the browser

  	

    