@SmokeTest
Feature: Check the new Remittance Tab Functionality

  Background: 
    Given User launch url and navigates to login screen

  @DownloadFile
  Scenario: Verify downlaod csv file
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then verify by default new remittance tab is selected or not
    And verify the downloaded file
    And verify the downloaded with file name
    And verify the downloaded with file extension

  @existingPaymentNumber
  Scenario: user can upload the existing payment number and check the validation
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then upload the existing payment number and check the validation
    Then verify the payment amount and net amount should be equal

  @level2
  Scenario: user can upload the existing payment number and check the validation
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then verify the invoice details in remittance tab level2

  @dropdownorder
  Scenario: verify the drop down opions order
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    When verify the drop down opions order in create new remittance popup page

  @headerDetails
  Scenario: verify the new remittancepage right side headers
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then verify the header details in remittance tab

  @L2headerDetails
  Scenario: verify the new remittancepage right side headers
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then verify the l2 header details in remittance tab

  @ItemizationStatus @PhaseII
  Scenario: verify the Itemization status by default it should be Ready To Itemize
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then verify the itemization status and dropdown values

  @uploadFiles @PhaseII
  Scenario Outline: User should able to upload image, .CSV,all files types! System should accept all type of files!
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then create the new remittance file and upload "<FileName>"
    Then try to upload different files in remittance document column
    Then check the uploaded files in remittance document column and level2

    Examples: 
      | FileName          |
      | Itemizationstatus |

  @ItemizationstatusChange @PhaseII
  Scenario: verify the remittance file in invoice summary tab
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then user changed status to itemized that remittance should be available only in Invoice Summary Page
    Then verify the remittance in invoice summary tab


 @ValidateTextIntString @PhaseII
  Scenario Outline: verify the text box accept numbers and string
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then validate the text box accept numbers and string
    |DocNumber|<DocNumber>| 
    |OriginalAmount|<OriginalAmount>|
    |PaymentNumber|<PaymentNumber>| 
    |Invoice|<Invoice>|
    |Deduction|<Deduction>| 
    |ReferenceDoc|<ReferenceDoc>|
    |OriginalAmountExcepted|<OriginalAmountExcepted>| 
    |deductionAmountExcepted|<deductionAmountExcepted>|
   
   Examples: 
      | DocNumber					|OriginalAmount	|PaymentNumber|Invoice						|Deduction	|ReferenceDoc	|OriginalAmountExcepted|deductionAmountExcepted|
      | Auto_Test_1234		|Auto_1000			|Auto_Test_6789|Auto_invoice_123	|Auto_100		|Auto_Ref_102	|1000										|100									|
   

  @ItemizeDebitMemo @PhaseII
  Scenario Outline: verify the itemize debit memo page fields
    When user should enter username
    When user should enter password
    When user click on sign in button to login into application
    Then create the new remittance file and upload "<FileName>"
    Then verify the itemizedebitmemo fields
    Then check the debit memo number in open debit memos

    Examples: 
      | FileName          |
      | Itemizationstatus |
