public class CreditCardValidity{

	//------------------------------ data fields ------------------------------
	private long creditCardNumber;	// Stores Credit Card Number
	private String CardType;		// Stores Credit Card Type
	private boolean isCardValid;	// Stores boolean about Card Validity
	
	
	//-------------------------- Setters and Getters --------------------------
	public long getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getCardType() {
		return CardType;
	}
	
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	
	public boolean isCardValid() {
		return isCardValid;
	}
	
	public void setCardValid(boolean isCardValid) {
		this.isCardValid = isCardValid;
	}

	
	//-------------------------- Default constructor --------------------------
	public CreditCardValidity() {
		this.creditCardNumber = 0;
		this.CardType = "Undefined";		
		this.isCardValid = false;
	}
	
	
	//-------------------------- Specific Constructor -------------------------
	public CreditCardValidity(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
		this.CardType = "Undefined";
		this.isCardValid = false;
		
		//Call function to validate credit card number
		//Change isCardValid variable to true if valid
		if (creditCardNumber > 0) {
			this.isCardValid = validateCreditCard(creditCardNumber);
		}
		
		//Call function to find card type and set return value in CardType variable
		if (isCardValid == true) {
			this.CardType = findCardType(creditCardNumber);
		}
	}

	
	/* Name: validateCreditCard() ---------------------------------------------
	 *		 Input 	: long
	 *		 Output : boolean
	 * 		 Function: Last step of validation. Test if the card is valid
	 * 				   by checking if the final sum is divisible by 10.
	 */
	private boolean validateCreditCard(long ccNum) {
		
		long oddSum = oddDigitSum(ccNum);
		long evenSum = evenDigitDoubleAndSum(ccNum);
		
		if ((oddSum + evenSum) % 10 == 0) {
			return true;
		} return false;
	}

	
	/* Name: oddDigitSum() ----------------------------------------------------
	 *		 Input 	: long
	 *		 Output : long
	 * 		 Function: Add up all of the remaining numbers (the odd numbers) in
	 * 				   the credit card number.
	 */
	private long oddDigitSum(long ccNum) {
		
		long tmpCCNum = ccNum;
		long modSum = 0;
		
		while (tmpCCNum > 0) {
			long mod10 = (tmpCCNum % 10);
			tmpCCNum = tmpCCNum / 100;
			modSum = modSum + mod10;
		}
			
		return modSum;
	}

	
	/* Name: evenDigitDoubleAndSum() ------------------------------------------
	 *		 Input 	: long
	 *		 Output : long
	 * 		 Function: Double every second digit from right to left and
	 * 				   add up all the single digit numbers.
	 */
	private long evenDigitDoubleAndSum(long ccNum) {
		
		long tmpCCNum = ccNum;
		long modSum = 0;
		
		while (tmpCCNum > 0) {
			tmpCCNum = tmpCCNum / 10;
			long mod10th = (tmpCCNum % 10);
			long doubleValue = mod10th * 2;
			
			//If doubling results in two digit number, add the digits up to get a single digit.
			if ((doubleValue) > 9) {
				long firstDig = doubleValue / 10;
				long secDig = doubleValue % 10;
				doubleValue = firstDig + secDig;
			}
			
			modSum = modSum + doubleValue;
			tmpCCNum = tmpCCNum / 10;						
		}
			
		return modSum;
	}
	
	
	/* Name: findCardType() ---------------------------------------------------
	 *		 Input 	: long
	 *		 Output : String
	 * 		 Function: Check first digit of card number to identify the card
	 * 				   type. If first digit is 3, check if second digit is a
	 * 				   7 for the American Express card type.
	 */
	private String findCardType(long ccNum) {
		
		int firstDigit = (int)Long.parseLong(Long.toString(ccNum).substring(0, 1));
		
		if(firstDigit == 4) {
			CardType = "Visa Card";
			
		} else if (firstDigit == 5) {
			CardType = "Master Card";
			
		} else if(firstDigit == 6) {
			CardType = "Discover Card";
			
		} else if(firstDigit == 3) {
			int secondDigit = (int)Long.parseLong(Long.toString(ccNum).substring(1, 2));
		
			if(secondDigit == 7) {
				CardType = "American Express Card";
				
			} else { 
				CardType = "Undefined";
				isCardValid = false;
			}
		} else {
			isCardValid = false;
			CardType = "Undefined";
		}
		
		return CardType;
		
	}
	
		
}
