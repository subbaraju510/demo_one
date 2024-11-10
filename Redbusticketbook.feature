Feature: redbus ticket booking

 #Scenario : Searching for buses boking between two cities
 #comment test
 @smoketest
  Scenario: User searches for buses between two cities
    Given user is on the bus booking homepage
    When user enters the origin city as "Hyderabad"
    And user enters the destination city as "Vijayawada"
    And user selects the travel date as
    And user clicks on the Search button
    Then available buses should be displayed
    When Filtering results based on time, boarding points
    And Seat selection process
    When user enters passenger details <passengername> and <age> and <Stateofresidance> and <Email>	 and <Phonenumber>
    Then Handling payment without completing the transaction

    
    
    Examples: 
      | passengername   | age |Stateofresidance |Email        |Phonenumber|
      | subbaraju       | 30  |Telangana        |abd@gmail.com|9999999999|
    
    
    
    
   
    
   
