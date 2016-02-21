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
		int pCardTotal; //count the players card total value when he stayed
		int pAces; //count the players aces in total when he stayed
		Random card = new Random();
		
		
		System.out.println("I N F I N I T E - B L A C K J A C K  ©2016");
		
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
					if(responsePlay.equals("Y") || responsePlay.equals("y")){
						r2p = false;
						while(bet){
							System.out.println("How much will you be betting?");
							responseBet = betamt.nextLine();
							if(isNumber(responseBet)){
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

										if(hsResponse.equalsIgnoreCase("h") || hsResponse.equals("hit")){

											Card.dealtCards = card.nextInt(51) + 1;
											Card.assignRNG(newCard);
											Card.setCard(Card.card);
											Card.setValue(Card.value);
											Card.printCards(newCard);

										}
										else if(hsResponse.equalsIgnoreCase("s") || hsResponse.equalsIgnoreCase("stay")){
											hsq = false;

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
													System.out.println("The dealer stays.");
													if((pCardTotal - pAces) + (11 * pAces) > 21){
														for(int i = 1; i <= pAces; i++){ //aces incrementer, used to test aces one by one
															if((pCardTotal - (pAces - i)) + (11 * (pAces - i)) > 21){
																//just loop back
															}
															else{
																pCardTotal = (pCardTotal - (pAces - i)) + (11 * (pAces - i));
																if((Card.cardTotal - Card.acesCounter) + (11 * Card.acesCounter) > 21){
																	for(int i2 = 1; i2 <= Card.acesCounter; i2++){
																		if((Card.cardTotal - (Card.acesCounter - i2)) + (11 * (Card.acesCounter - i2)) > 21){

																		}
																		else{
																			Card.cardTotal = (Card.cardTotal - (Card.acesCounter - i2)) + (11 * (Card.acesCounter - i2));
																			if(pCardTotal > Card.cardTotal){
																				System.out.println("The player wins the round!");
																				if(pCardTotal == 21){
																					Player.money = (Player.money + (responseBetConverted * 2.5));
																					Player.handsPlayed++;
																					Player.handsWon++;
																					Player.writePlayer(user);
																					r2p = true;
																				}
																				else{
																					Player.money = (Player.money + (responseBetConverted * 2));
																					Player.handsPlayed++;
																					Player.handsWon++;
																					Player.writePlayer(user);
																					r2p = true;
																				}
																			}
																			else if(pCardTotal < Card.cardTotal){
																				System.out.println("The dealer wins the round!");
																				Player.handsPlayed++;
																				Player.writePlayer(user);
																				r2p = true;
																			}
																			else{
																				System.out.println("PUSH! Player and dealer have the same card totals.");
																				Player.money = (Player.money + responseBetConverted);
																				Player.handsPlayed++;
																				Player.writePlayer(user);
																				r2p = true;
																			}
																		}
																	}

																}
															}
														}

													}
													else{
														pCardTotal = (pCardTotal - pAces) + (11 * pAces);
														if((Card.cardTotal - Card.acesCounter) + (11 * Card.acesCounter) > 21){
															for(int i2 = 1; i2 <= Card.acesCounter; i2++){
																if((Card.cardTotal - (Card.acesCounter - i2)) + (11 * (Card.acesCounter - i2)) > 21){

																}
																else{
																	Card.cardTotal = (Card.cardTotal - (Card.acesCounter - i2)) + (11 * (Card.acesCounter - i2));
																	if(pCardTotal > Card.cardTotal){
																		System.out.println("The player wins the round!");
																		if(pCardTotal == 21){
																			System.out.println("Blackjack! Bonus earnings!");
																			Player.money = (Player.money + (responseBetConverted * 2.5));
																			Player.handsPlayed++;
																			Player.handsWon++;
																			Player.writePlayer(user);
																			r2p = true;
																		}
																		else{
																			Player.money = (Player.money + (responseBetConverted * 2));
																			Player.handsPlayed++;
																			Player.handsWon++;
																			Player.writePlayer(user);
																			r2p = true;
																		}
																	}
																	else if(pCardTotal < Card.cardTotal){
																		System.out.println("The dealer wins the round!");
																		Player.handsPlayed++;
																		Player.writePlayer(user);
																		r2p = true;
																	}
																	else{
																		System.out.println("PUSH! Player and dealer have the same card totals.");
																		Player.money = (Player.money + responseBetConverted);
																		Player.handsPlayed++;
																		Player.writePlayer(user);
																		r2p = true;
																	}
																}
															}

														}
														else{
															Card.cardTotal = (Card.cardTotal - Card.acesCounter) + (11 * Card.acesCounter);
															if(pCardTotal > Card.cardTotal){
																System.out.println("The player wins the round!");
																if(pCardTotal == 21){
																	System.out.println("Blackjack! Bonus earnings!");
																	Player.money = (Player.money + (responseBetConverted * 2.5));
																	Player.handsPlayed++;
																	Player.handsWon++;
																	Player.writePlayer(user);
																	r2p = true;
																}
																else{
																	Player.money = (Player.money + (responseBetConverted * 2));
																	Player.handsPlayed++;
																	Player.handsWon++;
																	Player.writePlayer(user);
																	r2p = true;
																}
															}
															else if(pCardTotal < Card.cardTotal){
																System.out.println("The dealer wins the round!");
																Player.handsPlayed++;
																Player.writePlayer(user);
																r2p = true;
															}
															else{
																System.out.println("PUSH! Player and dealer have the same card totals.");
																Player.money = (Player.money + responseBetConverted);
																Player.handsPlayed++;
																Player.writePlayer(user);
																r2p = true;
															}
														}
													}

												}
												else if(Card.cardTotal == 17 && Card.acesCounter > 0){
													hsq = false;
													dQ = false;
													if(Card.checkBustHouse()){
														Player.handsPlayed++;
														Player.handsWon++;
														System.out.println("The player wins the round!");
														if(pCardTotal == 21){
															System.out.println("Blackjack! Bonus earnings!");
															Player.money = (Player.money + (responseBetConverted * 2.5));
															Player.writePlayer(user);
															r2p = true;
														}
														else{
															Player.money = (Player.money + (responseBetConverted * 2));
															Player.writePlayer(user);
															r2p = true;
														}
														break;
													}
													System.out.println("The house hits.");
													Card.dealtCards = card.nextInt(51) + 1;
													Card.assignRNG(newCard);
													Card.setCard(Card.card);
													Card.setValue(Card.value);
													Card.printCards(newCard);
													dQ = true;
												}
												else{
													hsq = false;
													dQ = false;
													if(Card.checkBustHouse()){
														Player.handsPlayed++;
														Player.handsWon++;
														System.out.println("The player wins the round!");
														if(pCardTotal == 21){
															System.out.println("Blackjack! Bonus earnings!");
															Player.money = (Player.money + (responseBetConverted * 2.5));
															Player.writePlayer(user);
															r2p = true;
														}
														else{
															Player.money = (Player.money + (responseBetConverted * 2));
															Player.writePlayer(user);
															r2p = true;
														}
														break;
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
								System.out.println("That's not an appropriate monetary amount!");
							}
								
							
						}
							
					}
					else if(responsePlay.equals("N") || responsePlay.equals("n")){
						System.out.println("See you next time!");
						System.exit(0);
					}
				}
			}
			else{
				System.out.println("Enter an actual name please...");
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
