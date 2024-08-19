Feature: Split and Cancel Order Line Quantity

  Scenario: Splitting and canceling quantities from an order line
    Given an order line with prime line number 4, quantity 20
    When the order line with prime line number 4 is split with quantity 10
    Then the remaining order line quantity should be 10 and canceled quantity should be 5
