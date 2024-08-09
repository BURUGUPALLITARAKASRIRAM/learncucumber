Feature: Shouty Listen

  Scenario: Listener is within the range
    Given Lousy is in 15 m away
    When  Shaun shouts "Free baggales at X coffee shp"
    Then 	Lousy listens the message

    