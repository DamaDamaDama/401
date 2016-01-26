import java.util.*;

public class bookstore {

	public static void main(String args[]){
		
	
		
		int spareAMT;
		int packsAMT;
		double userAMT; //the amount they are paying with
		boolean isCustomerQuestion = true; //this is for the while loop
		boolean isOrder = true;
		boolean badAMT = true; //while loop for purchasing
		String responseCustomer; //is there a customer?
		String choice;  //menu choice
		int customerNumber = 0; //track how many customers have gone thru
		double purchaseTotal; //total amount customer is being charged with before discounts etc
		double finalTotal; //total amount after discounts etc
		double actualMarksPrice; //determine actual bookmark total
		double userChange; //determine their change
		Scanner customer = new Scanner(System.in); //user input on customer
		Scanner menu = new Scanner(System.in); //user input in the menu
		Scanner purchase = new Scanner(System.in); //user input on their purchase
		
		
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
						int nMarks = 0; //number of bookmarks
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
							System.out.println("|___________________________________________________________|");

							choice = menu.nextLine();
							
								if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")){

									if (choice.equals("1")){
										nBooks++; //increment number of books
									}
									else if(choice.equals("2")){
										nMarks++; //increment number of marks
									}
									else if(choice.equals("3")){
										nPings++; //increment number of paintings
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
										
										purchaseTotal = (nBooks * 5) + actualMarksPrice + (nPings * 100);
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
											System.out.println(nMarks + " bookmarks @ $" + actualMarksPrice);
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
											System.out.println(nBooks + " books @ $" + (nBooks * 5));
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
											System.out.println(nBooks + " books @ $" + (nBooks * 5));
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
											System.out.println(nBooks + " books @ $" + (nBooks * 5));
											System.out.println(nMarks + " bookmarks @ $" + actualMarksPrice);
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
											System.out.println(nBooks + " books @ $" + (nBooks * 5));
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

}
