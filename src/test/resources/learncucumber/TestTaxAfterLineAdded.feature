Feature: Add a new line to an existing order and recalculate tax

  Scenario: Add a new line to an existing order with multiple lines and quantities
    Given An existing order with multilines and quantities
    When A new line is already added to the order
    Then The order should have one more line added and the tax should be recalculated
