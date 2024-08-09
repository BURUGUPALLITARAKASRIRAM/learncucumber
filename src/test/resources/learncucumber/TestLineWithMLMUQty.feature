Feature: Create a multi line and multi quantity order

  Scenario: Cancellations happens on a single quantity
    Given Create order with multi line and multi quantity
    When Cancel single quantity
    Then One of the status in the states arraylist moves to cancelled status
