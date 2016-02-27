import java.io.*;
import java.util.*;

public class Blackjack {
	public static void main(String[] args) throws IOException { 
		Scanner usersName = new Scanner(System.in); //what is meant by how random generated cards are stored?
		Scanner yn = new Scanner(System.in); //answer yes or no
		Scanner betamt = new Scanner(System.in); //input bet amount
		Scanner hs = new Scanner(System.in); //input hit or stay
		String responsePlay = "";
		String responseBet = "";
		String hsResponse = "";
		double responseBetConverted; 
		boolean newP = true; //new or old player determination
		boolean r2p = true; //ready 2 play
		boolean bet = true; //betting sequence
		boolean hsq = true; //loop for hit or stay
		boolean dQ = true; //dealer question loop, what is he going to do
		int pCardTotal; //count the players card total value when he stayed, this and pAces are used to dump the Card's values into them
		int pAces; //count the players aces in total when he stayed        |   ^ and then reuse Card.cardTotal etc for the house.
		int i; //incrementing my aces
		int i2; //same ^
		int i3; // ^
		Random card = new Random();
		
		
		System.out.println((char)27 + "[32m W E L C O M E - T O - I N F I N I T E - B L A C K J A C K" + (char)27 + "[37m"); //found this online, changes cmd text color
		
		while(newP){
			System.out.println("Enter your name to begin!");
			Player.name = usersName.nextLine();
			Player user = new Player(); //create object
			Player.setName(Player.name);
			Player.existingPlayer(user); //find if this player exists first
			Player.setMoney(Player.money);
			Player.setPlayed(Player.handsPlayed);
			Player.setWon(Player.handsWon);
			
			if(isAlphabet(Player.name)){
				newP = false; //after this line I probably want another if/try where it checks for an old user
				System.out.println("Thanks!");
				 
				Player.writePlayer(user); //overwrites old data and creates new player if need be
				if(Player.notNew){
					Player.printPlayer(user); //prints out player info only if it wasnt printed out already
				}
				
				while(r2p){
					bet = true;
					System.out.println("Ready to play? Enter Y/N");
					responsePlay = yn.nextLine();
					if(responsePlay.equalsIgnoreCase("Y") || responsePlay.equalsIgnoreCase("YES")){
						r2p = false;
						while(bet){
							System.out.println("How much will you be betting?");
							responseBet = betamt.nextLine();
							if(isNumber(responseBet)){
								if(!(Player.money <= 0.00)){
									responseBetConverted = Double.parseDouble(responseBet);
									if((Player.money - responseBetConverted) >= 0.00){
										bet = false;
										hsq = true;
										Player.money = (Player.money - responseBetConverted); //take away money because he spent it on a bet
										Player.writePlayer(user);
										Player.printPlayer(user);

										Card.cardTotal = 0;
										Card.acesCounter = 0;

										System.out.println("YOUR CARDS: ");
										Card newCard = new Card();
										Card.dealtCards = card.nextInt(51) + 1;
										Card.assignRNG(newCard);
										Card.setCard(Card.card);
										Card.setValue(Card.value);
										Card.printCards(newCard);

										Card.dealtCards = card.nextInt(51) + 1;
										Card.assignRNG(newCard);
										Card.setCard(Card.card);
										Card.setValue(Card.value);
										Card.printCards(newCard);

										while(hsq){
											if(Card.checkBust()){
												r2p = true;
												hsq = false;
												Player.handsPlayed++;
												Card.acesCounter = 0;
												break;
											}
											System.out.println("[H]it or [S]tay?");
											hsResponse = hs.nextLine();

											if(hsResponse.equalsIgnoreCase("h") || hsResponse.equalsIgnoreCase("hit")){

												Card.dealtCards = card.nextInt(51) + 1;
												Card.assignRNG(newCard);
												Card.setCard(Card.card);
												Card.setValue(Card.value);
												Card.printCards(newCard);

											}
											else if(hsResponse.equalsIgnoreCase("s") || hsResponse.equalsIgnoreCase("stay")){
												hsq = false;
												dQ = true;

												pCardTotal = Card.cardTotal;
												pAces = Card.acesCounter;
												Card.cardTotal = 0;
												Card.acesCounter = 0;

												System.out.println("THE HOUSE'S CARDS: ");

												Card.dealtCards = card.nextInt(51) + 1;
												Card.assignRNG(newCard);
												Card.setCard(Card.card);
												Card.setValue(Card.value);
												Card.printCards(newCard);

												Card.dealtCards = card.nextInt(51) + 1;
												Card.assignRNG(newCard);
												Card.setCard(Card.card);
												Card.setValue(Card.value);
												Card.printCards(newCard);

												while(dQ){
													if((Card.cardTotal > 17 && Card.cardTotal <= 21) || (Card.cardTotal == 17 && Card.acesCounter == 0)){
														dQ = false;
														System.out.println("The house stays.");
														if((pCardTotal - pAces) + (11 * pAces) > 21){
															for(i = 1; i <= pAces;){ //aces incrementer, used to test aces one by one
																if((pCardTotal - (pAces - i)) + (11 * (pAces - i)) > 21){
																	i++;
																}
																else{
																	pCardTotal = (pCardTotal - (pAces - i)) + (11 * (pAces - i));

																	if((Card.cardTotal - Card.acesCounter) + (11 * Card.acesCounter) > 21){
																		for(i2 = 1; i2 <= Card.acesCounter;){
																			if((Card.cardTotal - (Card.acesCounter - i2)) + (11 * (Card.acesCounter - i2)) > 21){
																				i2++;
																			}
																			else{
																				Card.cardTotal = (Card.cardTotal - (Card.acesCounter - i2)) + (11 * (Card.acesCounter - i2));
																				if(pCardTotal > Card.cardTotal){
																					if(pCardTotal == 21){
																						System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.5));
																						System.out.println((char)27 + "[33mBlackjack! Bonus earnings!" + (char)27 + "[37m");
																						Player.money = (Player.money + (responseBetConverted * 2.5));
																						Player.handsPlayed++;
																						Player.handsWon++;
																						Player.writePlayer(user);
																						r2p = true;
																						i2 = 999999; //don't want an infinite for loop
																					}
																					else{
																						System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.0));
																						Player.money = (Player.money + (responseBetConverted * 2.0));
																						Player.handsPlayed++;
																						Player.handsWon++;
																						Player.writePlayer(user);
																						r2p = true;
																						i2 = 999999;
																					}																		}
																				else if(pCardTotal < Card.cardTotal){
																					System.out.println("The house wins the round.");
																					Player.handsPlayed++;
																					Player.writePlayer(user);
																					r2p = true;
																					i2 = 999999;
																				}
																				else{
																					System.out.println("PUSH! Player and house have the same card totals.");
																					Player.money = (Player.money + responseBetConverted);
																					Player.handsPlayed++;
																					Player.writePlayer(user);
																					r2p = true;
																					i2 = 999999;
																				}
																			}
																		}


																	}
																	else{
																		if(pCardTotal > Card.cardTotal){
																			if(pCardTotal == 21){
																				System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.5));
																				System.out.println((char)27 + "[33mBlackjack! Bonus earnings!" + (char)27 + "[37m");
																				Player.money = (Player.money + (responseBetConverted * 2.5));
																				Player.handsPlayed++;
																				Player.handsWon++;
																				Player.writePlayer(user);
																				r2p = true;
																				i2 = 999999; //don't want an infinite for loop
																			}
																			else{
																				System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.0));
																				Player.money = (Player.money + (responseBetConverted * 2.0));
																				Player.handsPlayed++;
																				Player.handsWon++;
																				Player.writePlayer(user);
																				r2p = true;
																				i2 = 999999;
																			}																		}
																		else if(pCardTotal < Card.cardTotal){
																			System.out.println("The house wins the round.");
																			Player.handsPlayed++;
																			Player.writePlayer(user);
																			r2p = true;
																			i2 = 999999;
																		}
																		else{
																			System.out.println("PUSH! Player and house have the same card totals.");
																			Player.money = (Player.money + responseBetConverted);
																			Player.handsPlayed++;
																			Player.writePlayer(user);
																			r2p = true;
																			i2 = 999999;
																		}
																	}
																}
															}


														}
														else{
															pCardTotal = (pCardTotal - pAces) + (11 * pAces);
															if((Card.cardTotal - Card.acesCounter) + (11 * Card.acesCounter) > 21){
																for(i3 = 1; i3 <= Card.acesCounter;){
																	if((Card.cardTotal - (Card.acesCounter - i3)) + (11 * (Card.acesCounter - i3)) > 21){
																		i3++;
																	}
																	else{
																		Card.cardTotal = (Card.cardTotal - (Card.acesCounter - i3)) + (11 * (Card.acesCounter - i3));
																		if(pCardTotal > Card.cardTotal){
																			if(pCardTotal == 21){
																				System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.5));
																				System.out.println((char)27 + "[33mBlackjack! Bonus earnings!" + (char)27 + "[37m");
																				Player.money = (Player.money + (responseBetConverted * 2.5));
																				Player.handsPlayed++;
																				Player.handsWon++;
																				Player.writePlayer(user);
																				r2p = true;
																				i3 = 99999999;
																			}
																			else{
																				System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.0));
																				Player.money = (Player.money + (responseBetConverted * 2.0));
																				Player.handsPlayed++;
																				Player.handsWon++;
																				Player.writePlayer(user);
																				r2p = true;
																				i3 = 99999999;
																			}
																		}
																		else if(pCardTotal < Card.cardTotal){
																			System.out.println("The house wins the round.");
																			Player.handsPlayed++;
																			Player.writePlayer(user);
																			r2p = true;
																			i3 = 99999999;
																		}
																		else{
																			System.out.println("PUSH! Player and house have the same card totals.");
																			Player.money = (Player.money + responseBetConverted);
																			Player.handsPlayed++;
																			Player.writePlayer(user);
																			r2p = true;
																			i3 = 99999999;
																		}
																	}
																}
															}
															else{
																Card.cardTotal = (Card.cardTotal - Card.acesCounter) + (11 * Card.acesCounter);
																if(pCardTotal > Card.cardTotal){
																	if(pCardTotal == 21){
																		System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.5));
																		System.out.println((char)27 + "[33mBlackjack! Bonus earnings!" + (char)27 + "[37m");
																		Player.money = (Player.money + (responseBetConverted * 2.5));
																		Player.handsPlayed++;
																		Player.handsWon++;
																		Player.writePlayer(user);
																		r2p = true;
																	}
																	else{
																		System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.0));
																		Player.money = (Player.money + (responseBetConverted * 2.0));
																		Player.handsPlayed++;
																		Player.handsWon++;
																		Player.writePlayer(user);
																		r2p = true;
																	}
																}
																else if(pCardTotal < Card.cardTotal){
																	System.out.println("The house wins the round.");
																	Player.handsPlayed++;
																	Player.writePlayer(user);
																	r2p = true;
																}
																else{
																	System.out.println("PUSH! Player and house have the same card totals.");
																	Player.money = (Player.money + responseBetConverted);
																	Player.handsPlayed++;
																	Player.writePlayer(user);
																	r2p = true;
																}
															}	


														}

													}
													else{
														hsq = false;
														dQ = false;
														if(Card.checkBustHouse()){
															Player.handsPlayed++;
															Player.handsWon++;
															if((pCardTotal - pAces) + (11 * pAces) > 21){
																for(i = 1; i <= pAces;){ //aces incrementer, used to test aces one by one
																	if((pCardTotal - (pAces - i)) + (11 * (pAces - i)) > 21){
																		i++;
																	}
																	else{
																		pCardTotal = (pCardTotal - (pAces - i)) + (11 * (pAces - i));
																		if(pCardTotal == 21){
																			System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.5));
																			System.out.println((char)27 + "[33mBlackjack! Bonus earnings!" + (char)27 + "[37m");
																			Player.money = (Player.money + (responseBetConverted * 2.5));
																			Player.handsPlayed++;
																			Player.handsWon++;
																			Player.writePlayer(user);
																			r2p = true;
																			i2 = 999999; //don't want an infinite for loop
																			break;
																		}
																		else{
																			System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.0));
																			Player.money = (Player.money + (responseBetConverted * 2.0));
																			Player.handsPlayed++;
																			Player.handsWon++;
																			Player.writePlayer(user);
																			r2p = true;
																			i2 = 999999;
																			break;
																		}
																	}
																}


															}
															else if(pCardTotal == 21){
																System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.5));
																System.out.println((char)27 + "[33mBlackjack! Bonus earnings!" + (char)27 + "[37m");
																Player.money = (Player.money + (responseBetConverted * 2.5));
																Player.handsPlayed++;
																Player.handsWon++;
																Player.writePlayer(user);
																r2p = true;
																i2 = 999999; //don't want an infinite for loop
																break;
															}
															else{
																System.out.printf("The player wins the round! Winnings: %.2f\n", (responseBetConverted * 2.0));
																Player.money = (Player.money + (responseBetConverted * 2.0));
																Player.handsPlayed++;
																Player.handsWon++;
																Player.writePlayer(user);
																r2p = true;
																i2 = 999999;
																break;
															}

														}
														System.out.println("The house hits.");
														Card.dealtCards = card.nextInt(51) + 1;
														Card.assignRNG(newCard);
														Card.setCard(Card.card);
														Card.setValue(Card.value);
														Card.printCards(newCard);
														dQ = true;

													}
												}
											}

											else{
												System.out.println("Invalid input!");
											}

										}


									}
								else{
									System.out.println("You do not have the sufficient funds to make that bet.");
								}
							} 
							else{
								System.out.println("You do not have any money to bet with!");
								System.exit(0);
							}
							}	
							else{
								System.out.printf("That's not an appropriate monetary amount! Enter from $0.01 up to $%.2f\n", Player.money);
							}
						
							
						}
							
					}
					else if(responsePlay.equalsIgnoreCase("N") || responsePlay.equalsIgnoreCase("NO")){
						System.out.println("See you next time!");
						System.exit(0);
					}
					else{
						System.out.println("Invalid input, please respond with yes or no.");
					}
				
				}
			}
			else{
				System.out.println("Hey, enter an actual name please...");
			}
			
		}
	}
	
	public static boolean isAlphabet(String name) { //make sure a string is only letters, borrowed this method from stack overflow and made changes to it
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c) && !Character.isWhitespace(c)) { //modified to also allow spaces
	            return false;
	        }
	    }

	    return true;
	}
	
	public static boolean isNumber(String name) { //make sure a string is only numbers
		try{
	    	Double test;
	    	test = Double.parseDouble(name);
	    	if(test > 0){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}    	
	    }
	    catch (NumberFormatException e){ //try catch will toss any garbage input out and only accept above 0 with or without decimals
	    	return false;
	    }
	}
}
