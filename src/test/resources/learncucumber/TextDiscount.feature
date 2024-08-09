Feature: Test Discount Validation Of An Order

  Scenario: Validate Lines Have Discount Greater then 50
    Given Input The order
    When Create Order 
    Then No Less then In The Discount
