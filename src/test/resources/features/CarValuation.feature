Feature: Evaluate car registration details

  Scenario Outline: Current valuation check for cars
    Given I have list of car registration numbers in "<input_file>"
    And the expected car details are available in "<output_file>"
    When I retrieve the car details from the carzoo website
    Then Actual vehicle details should match the expected details
    Examples:
      | input_file                                | output_file                                |
      | src/test/resources/input/car_input_v2.txt | src/test/resources/output/car_output_v2.txt |
