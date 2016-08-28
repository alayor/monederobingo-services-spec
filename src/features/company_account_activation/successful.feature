Feature: Successful Company Account Activation

  Scenario: User provides sends correct activation request
    Given User sends company registration request
    And User sends activation request
    Then The user should receive the following messages
      | ENGLISH | Your user has been activated. |
      | SPANISH | Su usuario se ha activado. |