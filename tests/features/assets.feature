Background:
Given the following asset types:
    |asset_type     | 
    |Laptop         | 
    |Monitor        |
And the following locations:
    |location       | 
    |UA             | 
    |CH             |
    |IN             |
And the following assets:
    |asset          |asset_type    | location  |
    |Laptop 1       |Laptop        |UA         |
    |Laptop 2       |Laptop        |CH         |
    |Monitor 1      |Monitor       |UA         |
    |Monitor 2      |Monitor       |UA         |
    |Monitor 3      |Monitor       |UA         | 

Scenario: Request all assets 
When Request all assets 
Then The following list should be returned:
    |asset          |asset_type    |location   |
    |Laptop 1       |Laptop        |UA         |
    |Laptop 2       |Laptop        |CH         |
    |Laptop 3       |Laptop        |UA         |
    |Monitor 1      |Monitor       |UA         |
    |Monitor 2      |Monitor       |UA         |
    |Monitor 3      |Monitor       |CH         | 

Scenario: Request asset of specific type
When Request "Laptop" assets 
Then The following list should be returned:
    |asset          |asset_type    |location   |
    |Laptop 1       |Laptop        |UA         |
    |Laptop 2       |Laptop        |CH         |
    |Laptop 3       |Laptop        |UA         |

Scenario: Request asset of specific type in location
When Request "Laptop" assets 
And Location is "UA"
Then The following list should be returned:
    |asset          |asset_type    |location   |
    |Laptop 1       |Laptop        |UA         |
    |Laptop 3       |Laptop        |UA         |