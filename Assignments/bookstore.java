import java.util.*;

public class bookstore {
	
	

	public static void main(String args[]){
		
	
		
		int spareAMT;
		double packsAMT;
		int cardResponse; //do they have a card for an extra discount?
		double userAMT; //the amount they are paying with
		boolean isCustomerQuestion = true; //this is for the while loop
		boolean isOrder = true;
		boolean badAMT = true; //while loop for purchasing
		boolean userEdit = true; //while loop for editing
		String responseCustomer; //is there a customer?
		String choice;  //menu choice
		String userRemove; //removal choice
		int customerNumber = 0; //track how many customers have gone thru
		double bookTotal; //cost of the books alone, used for determining a book discount
		double purchaseTotal; //total amount customer is being charged with before discounts etc
		double finalTotal; //total amount after discounts etc
		double actualMarksPrice; //determine actual bookmark total
		double userChange; //determine their change
		Scanner customer = new Scanner(System.in); //user input on customer
		Scanner menu = new Scanner(System.in); //user input in the menu
		Scanner purchase = new Scanner(System.in); //user input on their purchase
		Scanner remove = new Scanner(System.in); //user input on what they want to remove
		
		
		while(isCustomerQuestion){ //while loop makes this go on forever until they pick a valid option
		
		System.out.println("Is there a customer in line? Reply with 1 for yes, and 2 for no.");
		
		responseCustomer = customer.nextLine();

				if (responseCustomer.equals("1") || responseCustomer.equals("2")){
					if (responseCustomer.equals("2")){
						isCustomerQuestion = false;
						System.exit(0); //close out if they type 2
					}
					else if (responseCustomer.equals("1")){ //roll out if they type 1
						isCustomerQuestion = false;
						isOrder = true;
						int nBooks = 0; //number of books
						int nMarks = 0; //number of book marks
						int nPings = 0; //number of paintings
						customerNumber++;
						while(isOrder){
							System.out.println("~~~~~~~~~~~~~~~~~~~CHECK OUT WHAT WE GOT~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("|                   Books @ $5.00 Each                      |");
							System.out.println("| Bookmarks @ $1.00 Each, Or Purchase A Pack Of 6 For $5.00 |");
							System.out.println("|            Paintings Of Books @ $100.00 Each              |");
							System.out.println("|      Every Third Customer Gets A 10% Off Discount!!!      |");
							System.out.println("|  1 Adds A Book To Your Order, 2 A Bookmark, 3 A Painting  |");
							System.out.println("|       4 Will Show Your Current Order, 5 To Checkout       |");
							System.out.println("|              Want To Edit Your Order? Press 6             |");
							System.out.println("|___________________________________________________________|");

							choice = menu.nextLine();
							
								if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6")){

									if (choice.equals("1")){
										nBooks++; //increment number of books
										System.out.println("Added a book");
									}
									else if(choice.equals("2")){
										nMarks++; //increment number of marks
										System.out.println("Added a bookmark");
									}
									else if(choice.equals("3")){
										nPings++; //increment number of paintings
										System.out.println("Added a painting");
									}
									else if(choice.equals("4")){
										if(nBooks == 0 && nMarks == 0 && nPings == 0){
											System.out.println("You didn't order anything yet!");
										}
										else if(nBooks == 0 && nMarks == 0 && nPings > 0){
											System.out.println("Your current order: " + nPings + " paintings of books.");
										}
										else if(nBooks == 0 && nMarks > 0 && nPings > 0){
											System.out.println("Your current order: " + nMarks + " bookmarks, " + nPings + " paintings of books.");
										}
										else if(nBooks == 0 && nPings == 0){
											System.out.println("Your current order: " + nMarks + " bookmarks.");
										}
										else if(nMarks == 0 && nBooks > 0 && nPings > 0){
											System.out.println("Your current order: " + nBooks + " books, " + nPings + " paintings of books.");
										}
										else if(nMarks == 0 && nPings == 0){
											System.out.println("Your current order: " + nBooks + " books.");
										}
										else if(nPings == 0){
											System.out.println("Your current order: " + nBooks + " books, " + nMarks + " bookmarks.");
										}
										else if(nBooks > 0 && nPings > 0 && nMarks > 0){
											System.out.println("Your current order: " + nBooks + " books, " + nMarks + " bookmarks, and " + nPings + " paintings of books.");
										}
										
									
										
									}
									else if(choice.equals("5")){
										isOrder = false; //we're done with the menu
										badAMT = true; //restart our while loop for the amount entered
										actualMarksPrice = 0;
										packsAMT = nMarks / 6;
										spareAMT = nMarks % 6;
										
										actualMarksPrice = (packsAMT * 5) + (spareAMT * 1);
										
										bookTotal = (nBooks * 5);
										cardResponse = bookWorm(); //determine their card status
										if (cardResponse == 1){
											bookTotal = bookTotal * .75;
										}
										
										purchaseTotal = bookTotal + actualMarksPrice + (nPings * 100);
										if (customerNumber % 3 == 0){
											purchaseTotal = purchaseTotal * .9;
											System.out.println("Congratulations, you're one of the lucky discount customers!");
										}
										
										
										finalTotal = (purchaseTotal * .07);
										finalTotal += purchaseTotal;
										
										
										if(nBooks == 0 && nMarks == 0 && nPings > 0){ //in the following if statements I cover every possible combination of purchases
											System.out.println("Your final order: ");
											System.out.println(nPings + " paintings of books @ $" + (nPings * 100));
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);											
											System.out.println("Please enter the amount you will pay: ");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}
											
										}
										else if(nBooks == 0 && nPings == 0 && nMarks > 0){
											System.out.println("Your final order: ");
											System.out.printf(nMarks + " bookmarks @ $%.2f%n", actualMarksPrice);
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);
											System.out.println("Please enter the amount you will pay:");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
													
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}
										}
										else if(nMarks == 0 && nPings == 0 && nBooks > 0){
											System.out.println("Your final order: ");
											System.out.printf(nBooks + " books @ $%.2f%n", bookTotal);
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);
											System.out.println("Please enter the amount you will pay:");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}}
										else if(nMarks == 0 && nPings > 0 && nBooks > 0){
											System.out.println("Your final order: ");
											System.out.printf(nBooks + " books @ $%.2f%n", bookTotal);
											System.out.println(nPings + " paintings of books @ $" + (nPings * 100));
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);
											System.out.println("Please enter the amount you will pay:");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}}
										else if(nMarks > 0 && nPings == 0 && nBooks > 0){
											System.out.println("Your final order: ");
											System.out.printf(nBooks + " books @ $%.2f%n", bookTotal);
											System.out.printf(nMarks + " bookmarks @ $%.2f%n", actualMarksPrice);
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);
											System.out.println("Please enter the amount you will pay: ");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}}
										else if(nPings > 0 && nBooks == 0 && nMarks > 0){
											System.out.println("Your final order: ");
											System.out.println(nMarks + " bookmarks @ $" + actualMarksPrice);
											System.out.println(nPings + " paintings of books @ $" + (nPings * 100));
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);
											System.out.println("Please enter the amount you will pay: ");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}
										}
										else if(nPings > 0 && nBooks > 0 && nMarks > 0){
											System.out.println("Your final order: ");
											System.out.printf(nBooks + " books @ $%.2f%n", bookTotal);
											System.out.println(nMarks + " bookmarks @ $" + actualMarksPrice);
											System.out.println(nPings + " paintings of books @ $" + (nPings * 100));
											System.out.printf("Your subtotal is: $%.2f%n", purchaseTotal);
											System.out.printf("After adding tax, as well as any possible discounts, your total is: $%.2f%n", finalTotal);
											System.out.println("Please enter the amount you will pay:");
											while(badAMT){
												userAMT = purchase.nextDouble();
												if(userAMT < finalTotal){
													System.out.println("Not enough funds! Please reenter.");
												}
												if(userAMT >= finalTotal){
													badAMT = false;
													userChange = userAMT - finalTotal;
													System.out.format("Thank you! Your change is: $%.2f%n", userChange);
													isCustomerQuestion = true;
												}
											}
										}
									
																		
									}
									else if(choice.equals("6")){
										isOrder = false; //we're temporarily done with the menu
										System.out.println("What changes do you want to make? Enter the same number to remove whichever item you wish. Entering 4 will quit the edit prompt.");
										while(userEdit){
											userRemove = remove.nextLine();
											if(userRemove.equals("1")){
												if(nBooks > 0){
													--nBooks;
													System.out.println("One book removed. Total books ordered: " + nBooks);
												}
												else{
													System.out.println("You don't have any more books!");
												}
											}
											else if (userRemove.equals("2")){
												if(nMarks > 0){
													--nMarks;
													System.out.println("One bookmark removed. Total bookmarks ordered: " + nMarks);
												}
												else{
													System.out.println("You don't have any more bookmarks!");
												}
											}
											else if (userRemove.equals("3")){
												if(nPings > 0){
													--nPings;
													System.out.println("One painting removed. Total paintings ordered: " + nPings);
												}
												else{
													System.out.println("You don't have any more paintings!");
												}
											}
											else if (userRemove.equals("4")){
												userEdit = false;
												isOrder = true;
											}
											else{
												System.out.println("Enter a valid input, please!");
											}
										}
									
									}
								}
								else{
									System.out.println("Enter a valid input please!");
								}
							
							

						
						}

					}
				}
				else{
					System.out.println("Invalid input. Cmon now");
					
				}
					
			
			
		
			
			
		}
	}
	public static int bookWorm(){  //i avoided methods for some reason but I at last found use for one with the bonus
		int cardHolder;
		Scanner worm = new Scanner(System.in);
		System.out.println("Do you have a Bookworm Card? Type 1 for yes and any other key for no.");
		cardHolder = worm.nextInt();
		return cardHolder;
	}
	

}
