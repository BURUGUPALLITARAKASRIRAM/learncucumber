Feature: Test Input Validation of An Order

  Scenario: Validate Lines Have Positive Price
    Given Input the order
    When Create order
    Then No Negative Sum In The Lines
