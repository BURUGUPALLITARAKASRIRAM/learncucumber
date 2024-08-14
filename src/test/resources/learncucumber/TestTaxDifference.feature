Feature: Difference after cancellation of multi qtys

  Scenario: Recalculate the Tax results to reduce Tax
    Given Create order with multi line and multi qtys
    When Cancel single qtys
    Then Compare the tax before and after tax should be less the previous