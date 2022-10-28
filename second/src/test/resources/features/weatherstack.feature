Feature: "Get current weather"

  Scenario Outline: "Get weather"

    Given Get requestSpec
    When Send a request to the endpoint "current" with parameters "<query>"
    Then Verify parameters "<language>","<name>","<country>","<utcOffset>"
    Examples:
      | query  | language | name   | country        | utcOffset |
      | Paris  | en       | Paris  | France         | 2.0       |
      | Moscow | en       | Moscow | Russia         | 3.0       |
      | London | en       | London | United Kingdom | 1.0       |
      | Madrid | en       | Madrid | Spain          | 2.0       |

  Scenario Outline: "Get error 6**"

    Given Get requestSpec
    When Send a request to the endpoint "current" with parameters "<query>" and "<units>"
    Then Check error response body <code>,"<type>","<info>"
    Examples:
      | query   | units | code           | type           | info                                                                                    |
      |         | m     | missing_query  | missing_query  | Please specify a valid location identifier using the query parameter.                   |
      | Moscows | m     | request_failed | request_failed | Your API request failed. Please try again or contact support.                           |
      | Moscow  | qwe   | invalid_unit   | invalid_unit   | You have specified an invalid unit. Please try again or refer to our API documentation. |

  Scenario Outline: "Get error 1**"

    Given Get requestSpec
    When Send a request to the endpoint "current" with parameters "<accessKey>", "<query>" and "<units>"
    Then Check error response body <code>,"<type>","<info>"
    Examples:
      | accessKey                          | query  | units | code                       | type               | info                                                                                    |
      | 5e07e02a21119afasdfa3452345fasdasf | Moscow | m     | invalid_missing_access_key | invalid_access_key | You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com] |
