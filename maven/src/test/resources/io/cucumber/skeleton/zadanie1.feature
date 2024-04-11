Feature: zadanie zaliczeniowe 1

  Background: user is logged in
    Given user is on the login page
    When user enters username "qliooldricbaioejyj@cazlv.com" and password "12345"
    And user clicks on the login button
    Then user is logged in

  Scenario Outline: user adds a new address
    Given user is on Your Account Page
    When user clicks on the Addresses button
    And user clicks on Create New Address button
    And user fills alias "<alias>" address "<address>" city "<city>" zip "<zip>" country "<country>" phone "<phone>"
    And user clicks on Save button
    Then new address is added
    And new address matches "<alias>" address "<address>" city "<city>" zip "<zip>" country "<country>" phone "<phone>"
    When user deletes new address
    Then new address is deleted

    Examples:
      | alias | address | city     | zip   | country | phone     |
      | Ms    | Testowa | Warszawa | 23-87 | Poland  | 888888888 |