package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class VendingMachineCLI {
//	public String currentBalance =
	//main options
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	//purchase options
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	//DEPOSIT_OPTIONS
	private static final String DEPOSIT_MENU_OPTION_ONE = "$1";
	private static final String DEPOSIT_MENU_OPTION_TWO = "$2";
	private static final String DEPOSIT_MENU_OPTION_FIVE = "$5";
	private static final String DEPOSIT_MENU_OPTION_TEN = "$10";
	private static final String[] DEPOSIT_MENU_OPTIONS = { DEPOSIT_MENU_OPTION_ONE,DEPOSIT_MENU_OPTION_TWO,DEPOSIT_MENU_OPTION_FIVE,DEPOSIT_MENU_OPTION_TEN};

//for user putting in product code, for each loop, get keySet turn to array
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {
		VendingMachine snackMaster3000 = new VendingMachine();
		snackMaster3000.getVendingInfo();
		String balanceLine = "";
		//need to show balance line every time anything but main menu is accessed.

		while (true) {

				String userChoice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

				LocalDateTime timeInfo = LocalDateTime.now();

				if (userChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					// display vending machine items
					System.out.println(snackMaster3000.displayItems());
				} else if (userChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					boolean isInPurchase = true;

					while (isInPurchase) {
						String secondUserChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
						if (secondUserChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
							int feedMoneyOption = getFeedMoneyAmount();
							snackMaster3000.takeMoney(feedMoneyOption);
						} else if (secondUserChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
							String slotValue = getUserInput(snackMaster3000.getSlotMap());
							snackMaster3000.displayItems();
							snackMaster3000.selectProduct(slotValue);

						}
						else if (secondUserChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
							String coinsGiven = snackMaster3000.makeChange(snackMaster3000.getBalance());
							isInPurchase = false;
							//print without ln for multiple uses. Put version for each purchase option
							//return to main menu
						}
					}
					// do purchase
				} else if (userChoice.equals(MAIN_MENU_OPTION_EXIT)) {
					snackMaster3000.makeChange(snackMaster3000.getBalance());
					System.out.println(snackMaster3000.getExitDialogue());
					exit(1);
				}

					// transactionLog.flush();
				}

		}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public String getUserInput(TreeMap<String, Slot> slotMap){
		String [] options = slotMap.keySet().toArray(new String[slotMap.size()]);
		System.out.println("Please choose slot number: ");
		String userInput = (String) menu.getChoiceFromOptions(options);
		return userInput;
	}

	public int getFeedMoneyAmount(){
		System.out.println("Please choose an amount: 1, 2, 5, 10: ");
		String userInput = (String) menu.getChoiceFromOptions(DEPOSIT_MENU_OPTIONS);
		if (userInput.equals(DEPOSIT_MENU_OPTION_ONE)){
			return 100;
		} else if (userInput.equals(DEPOSIT_MENU_OPTION_TWO)){
			return 200;
		} else if (userInput.equals(DEPOSIT_MENU_OPTION_FIVE)){
			return 500;
		} else if (userInput.equals(DEPOSIT_MENU_OPTION_TEN)){
			return 1000;
		} return 0;
	}
		}
