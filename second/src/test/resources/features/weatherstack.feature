Feature: "Get weather"

  Scenario Outline: "Get weather"

    Given Get requestSpec
    When Get call a current "<query>"
    Then Body is "<language>","<name>","<country>","<utcOffset>"
    Examples:
      | query  | language | name   | country        | utcOffset |
      | Paris  | en       | Paris  | France         | 2.0       |
      | Moscow | en       | Moscow | Russia         | 3.0       |
      | London | en       | London | United Kingdom | 1.0       |
      | Madrid | en       | Madrid | Spain          | 2.0       |

  Scenario Outline: "Get error"

    Given Get requestSpec
    When Get call a current "<query>"
    Then Body is <code>,"<type>","<info>"
    Examples:
      | query   | code           | type           | info                                                                  |
      |         | missing_query  | missing_query  | Please specify a valid location identifier using the query parameter. |
      | Moscows | request_failed | request_failed | Your API request failed. Please try again or contact support.         |



