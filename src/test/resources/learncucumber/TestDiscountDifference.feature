Feature: Difference of discounts after cancellation of multi qtys

  Scenario: Recalculate the discount results to reduce qtys
    Given Create order with multi line and multi qty
    When Cancel single qty
    Then Compare the discount before and after discount should be less the previous
