Feature: Difference after cancellation of multi quantity

  Scenario: Recalculate the ordertotal results to reduced price
    Given Create order with multi line and multi quantitys
    When Cancel single quantitys
    Then Compare the price before and after ordertotal price should be less the previous
