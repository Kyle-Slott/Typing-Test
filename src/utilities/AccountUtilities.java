package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class AccountUtilities {
	private static String accountStoreLoc = "resources/AccountStore.txt";
	private static TreeMap<String, String> accounts;
	
	public static boolean loginQuery(String username, String password) {
		String name = accounts.get(username);
		if (name==null) return false;
		return (accounts.get(username).equals(password));
	}
	
	public static boolean passwordRequirementTest(String password) {
		String tempPassword=password;
		
		if (password.length()<6) return false;
		if (tempPassword.replaceAll("[^0-9]", "")=="") return false;
		tempPassword = password;
		if (tempPassword.replaceAll("[^a-z]", "")=="") return false;
		if (password.replaceAll("[^A-Z]", "")=="") return false;
		return true;
	}
	
	public static int getNumberOfAccounts() throws IOException {
		int accountNumber=0;
		BufferedReader accountBR = new BufferedReader(new FileReader(accountStoreLoc));
		while (accountBR.readLine()!=null)  {
			accountNumber++;
		}
		accountBR.close();
		return accountNumber/2;
	}
	
	public static void loadAccountStore() throws IOException {
		int count =0;
		String tempUsername = "";
		String tempPassword = "";
		String line;
		File accountFile = new File(accountStoreLoc);
		accountFile.createNewFile();
		BufferedReader accountBR = new BufferedReader(new FileReader(accountFile));
		accounts = new TreeMap<String, String>();
		while ((line = accountBR.readLine())!=null) {
			if (count%2==0) {
				tempUsername = line;
			} else {
				tempPassword = line;
				accounts.put(tempUsername, tempPassword);
			}
			count++;
		}
		accountBR.close();
	}

	public static TreeMap<String, String> getAccounts() {
		return accounts;
	}

	public static void addAccount(String username, String password) throws IOException {
		accounts.put(username, password);
		BufferedWriter accountBW = new BufferedWriter(new FileWriter(accountStoreLoc, true));
		accountBW.append(username + "\n" + password + "\n");
		accountBW.close();
	}
}
