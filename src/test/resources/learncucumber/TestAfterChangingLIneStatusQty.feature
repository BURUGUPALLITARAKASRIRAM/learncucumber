Feature: Change Order Line Status and Quantity

  Scenario: Updating the quantity of an order line with specific arguments
    Given an order with a line having prime line number 4 and initial status "ORDERED" with quantity 5
    When the quantity of the line with prime line number 4 is updated to 10
    Then the order line status should be updated to "CHANGED" with quantity 10
