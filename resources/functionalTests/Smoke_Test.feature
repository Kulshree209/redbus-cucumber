Feature: Red Bus site automatiomn
Description: The purpose of this feature is to implement 2 given automated scenarios

@Test1
Scenario: Search and verify the Bus Ticket
    Given Open the firefox browser
    And enter the URL
    Then Search for Bus Tickets from Mumbai to Nasik
    Then Select Departure time as After 6 PM and Select Bus Type as Non AC
    Then Select the 2 Available Seats,Boarding and Dropping Point
    And Click on Proceed to Book
    Then On Passenger Details, Select I don't want insurance
    Then Verify whether the Total Amount Displayed on Passenger Details is the same as displayed on Select the
    And close the browser
    
 Scenario: Verify hotel location
 Given Open the firefox browser
    And enter the URL
    When Hotels link is visible on the screen
    Then Search for Hotels in Mumbai
    And  Select location as Andheri
    Then Verify whether the results are getting displayed for Andheri, Mumbai Location
    And close the browser

  	

    