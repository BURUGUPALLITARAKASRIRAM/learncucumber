Feature: Adding a new line to the order

  Scenario: Add a new line to an order with multilines and qtys
    Given An existing order with multilines and qtys
    Then A new line is added to the order
    When The order should have one more line added
