package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
	private Controller ctrl = new Controller();
	public static final String APIKEY = "64a3d2e6811d4505adf39b806396e5dd";

	public void getDataFromCtrl1()  {
		System.out.println("Business, money");
		ctrl.process("money",Endpoint.TOP_HEADLINES,Country.gb,Category.business, Language.en);



	}

	public void getDataFromCtrl2(){
		// TODO implement me
		System.out.println("Sport, football");
		ctrl.process("football",Endpoint.TOP_HEADLINES,Country.gb,Category.sports, Language.en);

	}

	public void getDataFromCtrl3(){
		// TODO implement me
		System.out.println("Health, corona");
		ctrl.process("corona",Endpoint.TOP_HEADLINES,Country.gb,Category.health, Language.en);
		/*NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.gb)
				.setSourceCategory(Category.health)
				.setLanguage(Language.en)
				.createNewsApi();
		ctrl.process(newsApi);*/


	}
	
	public void getDataForCustomInput() {
		// TODO implement me
		System.out.println("User Input");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a topic you want to search for: ");
		String ScanQ = scanner.next();
		System.out.println("Enter Endpoint: TOP_HEADLINES or EVERYTHING");
		Endpoint ScanEndPoint = Endpoint.valueOf(scanner.next());
		System.out.println("Enter country, ex: at for Austria");
		Country ScanCountry = Country.valueOf(scanner.next());
		System.out.println("Enter Category: ");
		Category ScanCategory = Category.valueOf(scanner.next());
		System.out.println("Enter language new should be in..");
		Language ScanLanguage = Language.valueOf(scanner.next());
		ctrl.process(ScanQ,ScanEndPoint, ScanCountry, ScanCategory, ScanLanguage);

		/*
		System.out.println("Enter a keyword: ");
		Scanner in = new Scanner(System.in);
		String keyword = in.nextLine();*/

		//ctrl.process("428ce1e3cf64410ebde5dcd05cd4d3e9",keyword,Endpoint.TOP_HEADLINES,, ScanLanguage );
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Business: money", this::getDataFromCtrl1);
		menu.insert("b", "Sports: Football", this::getDataFromCtrl2);
		menu.insert("c", "Health: corona", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
