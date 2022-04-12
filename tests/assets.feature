Background:
Given the following asset types:
    | asset_type    | 
    |Laptop         | 
    |Monitor        |
And the following assets:
    |asset          | asset_type    |
    |Laptop 1       | Laptop        |
    |Laptop 2       | Laptop        |
    |Monitor 1      | Monitor       |
    |Monitor 2      | Monitor       |
    |Monitor 3      | Monitor       |

Scenario: Request all assets 
When Request all assets 
Then The following list should be returned:
    |asset          | asset_type    |
    |Laptop 1       | Laptop        |
    |Laptop 2       | Laptop        |
    |Monitor 1      | Monitor       |
    |Monitor 2      | Monitor       |
    |Monitor 3      | Monitor       |

Scenario: Request asset of specific type
When Request "Laptop" assets 
Then The following list should be returned:
    |asset          | asset_type    |
    |Laptop 1       | Laptop        |
    |Laptop 2       | Laptop        |
