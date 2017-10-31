Feature: Incoming instruction for entities
    Scenario: Calculate total traded amount per day
    Given the incoming instruction for trade initialized with the following data
      | Entity | Indicator| AgreedFx |Currency | InstructionDate | Units | PricePerUnit |
      | foo    | B        | 0.5      | SGP     | 01 Jan 2016     | 200   | 100.25       |
      | bar    | S        | 0.22     | AED     | 01 Jan 2016     | 450   | 150.5        |
      | sbi    | B        | 0.40     | INR     | 01 Jan 2016     | 500   | 252.5        |
      | jpmc   | S        | 1.0      | USD     | 01 Jan 2016     | 550   | 82.5         |
      | socgen | B        | 0.50     | EUR     | 01 Jan 2016     | 300   | 200.25       |
      | citi   | S        | 0.5      | EUR     | 01 Jan 2016     | 45    | 1500.5       |
    When calculate total trade for type 'S' for date '04 Jan 2016'
    Then total incoming trade for date '04 Jan 2016' should 79136.25
    When calculate total trade for type 'B' for date '04 Jan 2016'
    Then total incoming trade for date '04 Jan 2016' should 90562.5

    Scenario: Calculate first rank trade from all instruction
    Given the incoming instruction for trade initialized with the following data
      | Entity | Indicator| AgreedFx |Currency | InstructionDate | Units | PricePerUnit |
      | foo    | B        | 0.5      | SGP     | 01 Jan 2016     | 200   | 100.25       |
      | bar    | S        | 0.22     | AED     | 01 Jan 2016     | 450   | 150.5        |
      | sbi    | B        | 0.40     | INR     | 01 Jan 2016     | 500   | 252.5        |
      | jpmc   | S        | 1.0      | USD     | 01 Jan 2016     | 550   | 82.5         |
      | socgen | B        | 0.50     | EUR     | 01 Jan 2016     | 300   | 200.25       |
      | citi   | S        | 0.5      | EUR     | 01 Jan 2016     | 45    | 1500.5       |
    When calculate first rank trade for type 'S'
    Then first rank trade should be 'jpmc' and all trade size should be 3
    When calculate first rank trade for type 'B'
    Then first rank trade should be 'sbi' and all trade size should be 3

