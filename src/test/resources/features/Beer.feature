Feature: Beer endpoints
    Scenario: Post Beer
        Given the user wants to post a new Beer
        And the request has the field name with value "Chocolate Vanilla Porter"
        And the request has the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the request has the field alcohol content with value "5.77%"
        And the request has the field price with value 19.99
        And the request has the field category with value "Robust Porter"
        When the user posts that Beer
        Then the response should contain status code 201
        And the response should contain a beer id
        And the response should contain the field name with value "Chocolate Vanilla Porter"
        And the response should contain the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the response should contain the field alcohol content with value "5.77%"
        And the response should contain the field price with value 19.99
        And the response should contain the field category with value "Robust Porter"

    Scenario: Get Beer
        Given the user wants to post a new Beer
        And the request has the field name with value "Chocolate Vanilla Porter"
        And the request has the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the request has the field alcohol content with value "5.77%"
        And the request has the field price with value 19.99
        And the request has the field category with value "Robust Porter"
        Then the user posts that Beer
        When the user wants to fetch that Beer
        Then the response should contain status code 200
        And the response should contain a beer id
        And the response should contain the field name with value "Chocolate Vanilla Porter"
        And the response should contain the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the response should contain the field alcohol content with value "5.77%"
        And the response should contain the field price with value 19.99
        And the response should contain the field category with value "Robust Porter"

    Scenario: Delete Beer
        Given the user wants to post a new Beer
        And the request has the field name with value "Chocolate Vanilla Porter"
        And the request has the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the request has the field alcohol content with value "5.77%"
        And the request has the field price with value 19.99
        And the request has the field category with value "Robust Porter"
        Then the user posts that Beer
        And the user wants to post a new Beer
        And the request has the field name with value "Brahma"
        And the request has the field ingredients with value "Corn, Water"
        And the request has the field alcohol content with value "1%"
        And the request has the field price with value 1.99
        And the request has the field category with value "Corn Juice"
        Then the user posts that Beer
        Then the user deletes the first one
        When the user wants to fetch all Beers
        Then the response should contain status code 200
        And the response should contain only one Beer with name "Brahma"

    Scenario: Get All Beers
        Given the user wants to post a new Beer
        And the request has the field name with value "Chocolate Vanilla Porter"
        And the request has the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the request has the field alcohol content with value "5.77%"
        And the request has the field price with value 19.99
        And the request has the field category with value "Robust Porter"
        Then the user posts that Beer
        And the user wants to post a new Beer
        And the request has the field name with value "Brahma"
        And the request has the field ingredients with value "Corn, Water"
        And the request has the field alcohol content with value "1%"
        And the request has the field price with value 1.99
        And the request has the field category with value "Corn Juice"
        Then the user posts that Beer
        When the user wants to fetch all Beers
        Then the response should contain status code 200
        And the response should contain a beer id on index 0
        And the response should contain the field name with value "Chocolate Vanilla Porter" on index 0
        And the response should contain the field ingredients with value "Cocoa, Vanilla, Yeast" on index 0
        And the response should contain the field alcohol content with value "5.77%" on index 0
        And the response should contain the field price with value 19.99 on index 0
        And the response should contain the field category with value "Robust Porter" on index 0
        And the response should contain a beer id on index 1
        And the response should contain the field name with value "Brahma" on index 1
        And the response should contain the field ingredients with value "Corn, Water" on index 1
        And the response should contain the field alcohol content with value "1%" on index 1
        And the response should contain the field price with value 1.99 on index 1
        And the response should contain the field category with value "Corn Juice" on index 1

    Scenario: Patch Beer
        Given the user wants to post a new Beer
        And the request has the field name with value "Chocolate Vanilla Porter"
        And the request has the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the request has the field alcohol content with value "5.77%"
        And the request has the field price with value 19.99
        And the request has the field category with value "Robust Porter"
        And the user posts that Beer
        Then the user patches that Beer with a new price 29.99
        When the user wants to fetch that Beer
        And the response should contain a beer id
        And the response should contain the field name with value "Chocolate Vanilla Porter"
        And the response should contain the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the response should contain the field alcohol content with value "5.77%"
        And the response should contain the field price with value 29.99
        And the response should contain the field category with value "Robust Porter"

    Scenario: Put Beer
        Given the user wants to post a new Beer
        And the request has the field name with value "Chocolate Vanilla Porter"
        And the request has the field ingredients with value "Cocoa, Vanilla, Yeast"
        And the request has the field alcohol content with value "5.77%"
        And the request has the field price with value 19.99
        And the request has the field category with value "Robust Porter"
        And the user posts that Beer
        Then the user wants to put a new Beer on that id
        And the request has the field name with value "Brahma"
        And the request has the field ingredients with value "Corn, Water"
        And the request has the field alcohol content with value "1%"
        And the request has the field price with value 1.99
        And the request has the field category with value "Corn Juice"
        And the user puts that Beer
        When the user wants to fetch that Beer
        And the response should contain a beer id
        And the response should contain the field name with value "Brahma"
        And the response should contain the field ingredients with value "Corn, Water"
        And the response should contain the field alcohol content with value "1%"
        And the response should contain the field price with value 1.99
        And the response should contain the field category with value "Corn Juice"