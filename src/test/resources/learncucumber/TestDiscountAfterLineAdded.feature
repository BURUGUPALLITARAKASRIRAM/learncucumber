Feature: Add a new line to an existing order and recalculate Discount

  Scenario: Add a new line to an existing order with multiple lines and quantities
    Given An existing order with multiple lines and quantities
    When A new line  already added to the order
    Then The order should have one more line added and the discount should be recalculated
