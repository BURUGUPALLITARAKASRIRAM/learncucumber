Feature: Test Input Validation Of An Order

  Scenario: Validate Lines Have Tax Greater Than 20
    Given Input The Order
    When Create Order For Tax
    Then No Less Than In The Tax
    
